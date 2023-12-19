package uet.app.mysound.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import uet.app.mysound.R
import uet.app.mysound.common.DB_NAME
import uet.app.mysound.common.SELECTED_LANGUAGE
import uet.app.mysound.common.SETTINGS_FILENAME
import uet.app.mysound.data.dataStore.DataStoreManager
import uet.app.mysound.data.db.DatabaseDao
import uet.app.mysound.data.db.MusicDatabase
import uet.app.mysound.extension.div
import uet.app.mysound.extension.zipInputStream
import uet.app.mysound.extension.zipOutputStream
import uet.app.mysound.service.SimpleMediaService
import uet.app.mysound.ui.MainActivity
import uet.app.youtubeExtractor.YouTube
import uet.app.youtubeExtractor.models.YouTubeLocale
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltViewModel
class SettingsViewModel @Inject constructor(
    application: Application,
    private var database: MusicDatabase,
    private var databaseDao: DatabaseDao,
    ) : AndroidViewModel(application) {

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    private var _location: MutableLiveData<String> = MutableLiveData()
    val location: LiveData<String> = _location
    private var _language: MutableLiveData<String> = MutableLiveData()
    val language: LiveData<String> = _language
    private var _loggedIn: MutableLiveData<String> = MutableLiveData()
    val loggedIn: LiveData<String> = _loggedIn


    fun getLocation() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                dataStoreManager.location.collect { location ->
                    _location.postValue(location)
                }
            }
        }
    }
    fun getLoggedIn() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                dataStoreManager.loggedIn.collect { loggedIn ->
                    _loggedIn.postValue(loggedIn)
                }
            }
        }
    }

    fun changeLocation(location: String) {
        viewModelScope.launch {
            withContext((Dispatchers.Main)) {
                dataStoreManager.setLocation(location)
                YouTube.locale = YouTubeLocale(location, language.value!!)
                getLocation()
            }
        }
    }


    fun backup(context: Context, uri: Uri) {
        runCatching {
            context.applicationContext.contentResolver.openOutputStream(uri)?.use {
                it.buffered().zipOutputStream().use { outputStream ->
                    (context.filesDir/"datastore"/"$SETTINGS_FILENAME.preferences_pb").inputStream().buffered().use { inputStream ->
                        outputStream.putNextEntry(ZipEntry("$SETTINGS_FILENAME.preferences_pb"))
                        inputStream.copyTo(outputStream)
                    }
                    runBlocking(Dispatchers.IO) {
                        databaseDao.checkpoint()
                    }
                    FileInputStream(database.openHelper.writableDatabase.path).use { inputStream ->
                        outputStream.putNextEntry(ZipEntry(DB_NAME))
                        inputStream.copyTo(outputStream)
                    }
                }
            }
        }.onSuccess {
            Toast.makeText(context,
                context.getString(R.string.backup_create_success), Toast.LENGTH_SHORT).show()
        }.onFailure {
            it.printStackTrace()
            Toast.makeText(context,
                context.getString(R.string.backup_create_failed), Toast.LENGTH_SHORT).show()
        }
    }

    @UnstableApi
    fun restore(context: Context, uri: Uri) {
        runCatching {
            runBlocking { dataStoreManager.restore(true)}
            context.applicationContext.contentResolver.openInputStream(uri)?.use {
                it.zipInputStream().use { inputStream ->
                    var entry = inputStream.nextEntry
                    var count = 0
                    while (entry != null && count < 2) {
                        when (entry.name) {
                            "$SETTINGS_FILENAME.preferences_pb" -> {
                                (context.filesDir/"datastore"/"$SETTINGS_FILENAME.preferences_pb").outputStream().use { outputStream ->
                                    inputStream.copyTo(outputStream)
                                }
                            }

                            DB_NAME -> {
                                runBlocking(Dispatchers.IO) {
                                    databaseDao.checkpoint()
                                }
                                database.close()
                                FileOutputStream(database.openHelper.writableDatabase.path).use { outputStream ->
                                    inputStream.copyTo(outputStream)
                                }
                            }
                        }
                        count++
                        entry = inputStream.nextEntry
                    }
                }
            }
            context.stopService(Intent(context, SimpleMediaService::class.java))
            context.startActivity(Intent(context, MainActivity::class.java))
            exitProcess(0)
        }.onFailure {
            it.printStackTrace()
            Toast.makeText(context, context.getString(R.string.restore_failed), Toast.LENGTH_SHORT).show()
        }
    }

    fun getLanguage() {
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                dataStoreManager.getString(SELECTED_LANGUAGE).collect { language ->
                    _language.postValue(language)
                }
            }
        }
    }

    @UnstableApi
    fun changeLanguage(code: String) {
        viewModelScope.launch {
            withContext((Dispatchers.Main)) {
                dataStoreManager.putString(SELECTED_LANGUAGE, code)
                YouTube.locale = YouTubeLocale(location.value!!, code)
                getLanguage()
            }
        }
    }
}