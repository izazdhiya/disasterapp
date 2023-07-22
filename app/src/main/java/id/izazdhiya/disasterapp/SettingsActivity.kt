package id.izazdhiya.disasterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import id.izazdhiya.disasterapp.databinding.ActivitySettingsBinding
import id.izazdhiya.disasterapp.datastore.SettingsDataStore
import id.izazdhiya.disasterapp.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
//    private lateinit var dataStore: SettingsDataStore
//    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        viewModel = ViewModelProvider(this@SettingsActivity)[SettingsViewModel::class.java]
//        dataStore = SettingsDataStore(this)
//
//        checkThemeMode()
//
//        binding.apply {
//            switchMode.setOnCheckedChangeListener { _, isChecked ->
//                lifecycleScope.launch {
//                    when (isChecked) {
//                        true -> viewModel.setTheme(true)
//                        false -> viewModel.setTheme(false)
//                    }
//                }
//            }
//        }

        binding.switchMode.isChecked = isDarkModeEnabled()
        binding.switchMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setDarkMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                setDarkMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isDarkModeEnabled(): Boolean {
        val currentNightMode = AppCompatDelegate.getDefaultNightMode()
        return currentNightMode == AppCompatDelegate.MODE_NIGHT_YES
    }

    private fun setDarkMode(nightMode: Int) {
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

//    private fun checkThemeMode() {
//        binding.apply {
//            viewModel.getTheme.observe(this@SettingsActivity) { isDarkMode ->
//                when (isDarkMode) {
//                    true -> {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                        switchMode.isChecked = true
////                        modeSwitch.text="Dark Mode"
////                        imgStatus.setAnimation(R.raw.night)
//                    }
//                    false -> {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                        switchMode.isChecked = false
////                        modeSwitch.text="Light Mode"
////                        imgStatus.setAnimation(R.raw.day)
//                    }
//                }
//            }
//        }
//    }
}