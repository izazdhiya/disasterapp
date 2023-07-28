package id.izazdhiya.disasterapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.maps.GoogleMap
import id.izazdhiya.disasterapp.databinding.ActivityMainBinding
import id.izazdhiya.disasterapp.datastore.SettingsDataStore
import id.izazdhiya.disasterapp.model.network.Status
import id.izazdhiya.disasterapp.repository.DisasterRepository
import id.izazdhiya.disasterapp.repository.viewModelsFactory
import id.izazdhiya.disasterapp.service.ApiClient
import id.izazdhiya.disasterapp.service.ApiService
import id.izazdhiya.disasterapp.viewmodel.DisasterViewModel
import id.izazdhiya.disasterapp.viewmodel.MainViewModel


internal class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private val apiService: ApiService by lazy { ApiClient.instance }

    private val disasterRepository: DisasterRepository by lazy { DisasterRepository(apiService) }
    private val disasterViewModel: DisasterViewModel by viewModelsFactory { DisasterViewModel(disasterRepository) }

    private val channelId = "disaster"
    private val notificationId = 1

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel.Factory(SettingsDataStore(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        observeNotification()

    }

    companion object {
        private const val REQUEST_POST_NOTIFICATIONS = 1
    }

    private fun createNotificationChannel() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Waspada area banjir")
            .setContentText("Tinggi banjir antara 71 hingga 150 cm")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED
        ) {
            with(NotificationManagerCompat.from(this@MainActivity)) {
                notify(notificationId, builder.build())
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS,
                    ),
                    REQUEST_POST_NOTIFICATIONS
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun observeNotification() {
        disasterViewModel.getFloods().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data?.statusCode == 200) {
                        val flood = it.data.result.objects.output.geometries
                        if (flood.isNotEmpty()) {
                            createNotificationChannel()
                        }
                    }
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        }
    }
}



