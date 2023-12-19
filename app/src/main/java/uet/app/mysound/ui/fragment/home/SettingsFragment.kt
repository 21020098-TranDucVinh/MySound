package uet.app.mysound.ui.fragment.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.media3.common.util.UnstableApi
import androidx.navigation.fragment.findNavController
import coil.annotation.ExperimentalCoilApi
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import uet.app.mysound.R
import uet.app.mysound.common.SUPPORTED_LANGUAGE
import uet.app.mysound.common.SUPPORTED_LOCATION
import uet.app.mysound.data.dataStore.DataStoreManager
import uet.app.mysound.databinding.FragmentSettingsBinding
import uet.app.mysound.extension.navigateSafe
import uet.app.mysound.myAPI.User.LoginActivity
import uet.app.mysound.ui.MainActivity
import uet.app.mysound.viewModel.SettingsViewModel
import uet.app.mysound.viewModel.SharedViewModel


@UnstableApi
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SettingsViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    private val backupLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument("application/octet-stream")) { uri ->
        if (uri != null) {
            viewModel.backup(requireContext(), uri)
        }
    }
    private val restoreLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) {
            viewModel.restore(requireContext(), uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.topAppBarLayout.applyInsetter {
            type(statusBars = true){
                margin()
            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLoggedIn()
        viewModel.loggedIn.observe(viewLifecycleOwner) {
            if (MainActivity.loginResponse?.token != null) {
                binding.tvLogInTitle.text = "Log out"
                binding.tvLogIn.text = getString(R.string.logged_in)
            } else if (MainActivity.loginResponse?.token == null) {
                binding.tvLogInTitle.text = getString(R.string.log_in)
                binding.tvLogIn.text = getString(R.string.log_in_to_get_personally_data)
            }
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    @UnstableApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLocation()
        viewModel.getLanguage()
        viewModel.getLoggedIn()


        viewModel.loggedIn.observe(viewLifecycleOwner) {
            if (MainActivity.loginResponse?.token != null) {
                binding.tvLogInTitle.text = "Log out"
                binding.tvLogIn.text = getString(R.string.logged_in)
            } else if (MainActivity.loginResponse?.token == null) {
                binding.tvLogInTitle.text = getString(R.string.log_in)
                binding.tvLogIn.text = getString(R.string.log_in_to_get_personally_data)
            }
        }

        viewModel.location.observe(viewLifecycleOwner) {
            binding.tvContentCountry.text = it
        }
        viewModel.language.observe(viewLifecycleOwner) {
            if (it != null) {
                val temp = SUPPORTED_LANGUAGE.items.getOrNull(SUPPORTED_LANGUAGE.codes.indexOf(it))
                binding.tvLanguage.text = temp
            }
        }

        binding.btVersion.setOnClickListener {
            findNavController().navigateSafe(R.id.action_global_creditFragment)
        }

        binding.btLogin.setOnClickListener {
            if (MainActivity.loginResponse?.token != null) {
//                binding.tvLogInTitle.text = getString(R.string.log_in)
//                binding.tvLogIn.text = getString(R.string.log_in_to_get_personally_data)
                MainActivity.loginResponse = null
                Toast.makeText(requireContext(), getString(R.string.logged_out), Toast.LENGTH_SHORT).show()
            }
            else if (viewModel.loggedIn.value == DataStoreManager.FALSE) {
//                binding.tvLogInTitle.text = getString(R.string.log_out)
//                binding.tvLogIn.text = getString(R.string.logged_in)
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
//                findNavController().navigateSafe(R.id.action_global_logInFragment)
            }
        }

        binding.btGithub.setOnClickListener {
            val urlIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/21020098-TranDucVinh/MySound")
            )
            startActivity(urlIntent)
        }

        binding.btContentCountry.setOnClickListener {
            var checkedIndex = -1
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setSingleChoiceItems(SUPPORTED_LOCATION.items, -1) { _, which ->
                    checkedIndex = which
                }
                .setTitle(getString(R.string.content_country))
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.change)) { dialog, _ ->
                    if (checkedIndex != -1) {
                        viewModel.changeLocation(SUPPORTED_LOCATION.items[checkedIndex].toString())
                        viewModel.location.observe(viewLifecycleOwner) {
                            binding.tvContentCountry.text = it
                        }
                    }
                    dialog.dismiss()
                }
            dialog.show()
        }
        binding.btLanguage.setOnClickListener {
            var checkedIndex = -1
            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setSingleChoiceItems(SUPPORTED_LANGUAGE.items, -1) { _, which ->
                    checkedIndex = which
                }
                .setTitle(getString(R.string.language))
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.change)) { dialog, _ ->
                    if (checkedIndex != -1) {
                        val alertDialog = MaterialAlertDialogBuilder(requireContext())
                            .setTitle(R.string.warning)
                            .setMessage(R.string.change_language_warning)
                            .setNegativeButton(getString(R.string.cancel)) { d, _ ->
                                d.dismiss()
                                dialog.dismiss()
                            }
                            .setPositiveButton(getString(R.string.change)) { d, _ ->
                                viewModel.changeLanguage(SUPPORTED_LANGUAGE.codes[checkedIndex])
                                viewModel.language.observe(viewLifecycleOwner) {
                                    if (it != null) {
                                        val temp = SUPPORTED_LANGUAGE.items[SUPPORTED_LANGUAGE.codes.indexOf(it)]
                                        binding.tvLanguage.text = temp
                                        val localeList = LocaleListCompat.forLanguageTags(it)
                                        sharedViewModel.activityRecreate()
                                        AppCompatDelegate.setApplicationLocales(localeList)
                                    }
                                }
                                d.dismiss()
                                dialog.dismiss()
                            }
                        alertDialog.show()
                    }
                    dialog.dismiss()
                }
            dialog.show()
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}