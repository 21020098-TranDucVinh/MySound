package uet.app.mysound.di

import android.content.Context
import android.util.Log
import androidx.core.content.contentValuesOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uet.app.mysound.common.DB_NAME
import uet.app.mysound.data.dataStore.DataStoreManager
import uet.app.mysound.data.db.Converters
import uet.app.mysound.data.db.DatabaseDao
import uet.app.mysound.data.db.MusicDatabase
import uet.app.mysound.data.db.entities.PairSongLocalPlaylist
import uet.app.mysound.extension.dataStore
import uet.app.mysound.extension.toSQLiteQuery
import java.lang.reflect.Type
import java.time.ZoneOffset
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalServiceModule {

    @Provides
    @Singleton
    fun provideMusicDatabase(@ApplicationContext context: Context): MusicDatabase = Room.databaseBuilder(context, MusicDatabase::class.java, DB_NAME)
        .addTypeConverter(Converters())
        .build()

    @Provides
    @Singleton
    fun provideDatabaseDao(musicDatabase: MusicDatabase): DatabaseDao = musicDatabase.getDatabaseDao()

    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideDatastoreManager(settingsDataStore: DataStore<Preferences>): DataStoreManager = DataStoreManager(
        settingsDataStore
    )
}