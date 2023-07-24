package id.izazdhiya.disasterapp


import android.content.Intent
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.izazdhiya.disasterapp.adapter.DisasterAdapter
import id.izazdhiya.disasterapp.databinding.ActivityMainBinding
import id.izazdhiya.disasterapp.databinding.BottomSheetBinding
import id.izazdhiya.disasterapp.datastore.SettingsDataStore
import id.izazdhiya.disasterapp.model.network.Resource
import id.izazdhiya.disasterapp.model.network.Status
import id.izazdhiya.disasterapp.model.network.response.DisasterReport
import id.izazdhiya.disasterapp.repository.DisasterRepository
import id.izazdhiya.disasterapp.repository.viewModelsFactory
import id.izazdhiya.disasterapp.service.ApiClient
import id.izazdhiya.disasterapp.service.ApiService
import id.izazdhiya.disasterapp.viewmodel.DisasterViewModel
import id.izazdhiya.disasterapp.viewmodel.MainViewModel
import com.airbnb.lottie.LottieAnimationView


internal class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel.Factory(SettingsDataStore(this))
    }

    private lateinit var disasterAdapter: DisasterAdapter
    private val apiService: ApiService by lazy { ApiClient.instance }

    private val disasterRepository: DisasterRepository by lazy { DisasterRepository(apiService) }
    private val disasterViewModel: DisasterViewModel by viewModelsFactory { DisasterViewModel(disasterRepository) }

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS)
        window.addFlags(FLAG_LAYOUT_NO_LIMITS)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val sheet = findViewById<FrameLayout>(R.id.sheet)
        BottomSheetBehavior.from(sheet).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.buttonSetting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//        observeReports()
        observeArchive()

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED
//            && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//                if (location != null) {
//                    val currentLatLng = LatLng(location.latitude, location.longitude)
//                    mMap.addMarker(MarkerOptions().position(currentLatLng).title("Current Location"))
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15.0f))
//                }
//            }
//        } else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ),
//                REQUEST_LOCATION_PERMISSION
//            )
//        }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    private fun setData(it: Resource<DisasterReport>){
        if (it.data?.statusCode == 200) {
            binding.pbDisaster.isVisible = false
            val disaster = it.data.result?.features
            if (!disaster.isNullOrEmpty()) {
                for (element in disaster) {
                    val latLng = LatLng(element.geometry.coordinates[0], element.geometry.coordinates[1])
                    mMap.addMarker(MarkerOptions().position(latLng).title(element.properties.disasterType))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f))
                }

                disasterAdapter.updateData(it.data.result)
                binding.tvInfo.text = "Informasi Bencana Terkini"

            } else {
                Toast.makeText(this, "Tidak ada data!", Toast.LENGTH_SHORT).show()
                binding.lottieNodata.isVisible = true
            }
        } else {
            Toast.makeText(this, "Bad Request!", Toast.LENGTH_SHORT).show()
            binding.pbDisaster.isVisible = false
            binding.lottieNodata.isVisible = true
        }
    }

    private fun observeReports() {
        disasterViewModel.getReports().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    setData(it)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.pbDisaster.isVisible = true
                }
            }
        }

    }

    private fun observeArchive() {
        disasterViewModel.getArchive("2022-12-04T00:00:00+0700", "2022-12-06T05:00:00+0700").observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    setData(it)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    binding.pbDisaster.isVisible = true
                }
            }
        }
    }
}



