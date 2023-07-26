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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import id.izazdhiya.disasterapp.adapter.DisasterAdapter
import id.izazdhiya.disasterapp.adapter.DisasterTypeAdapter
import id.izazdhiya.disasterapp.databinding.ActivityMainBinding
import id.izazdhiya.disasterapp.datastore.SettingsDataStore
import id.izazdhiya.disasterapp.fragment.SearchFragment
import id.izazdhiya.disasterapp.model.DisasterType
import id.izazdhiya.disasterapp.model.network.Resource
import id.izazdhiya.disasterapp.model.network.Status
import id.izazdhiya.disasterapp.model.network.response.DisasterReport
import id.izazdhiya.disasterapp.repository.DisasterRepository
import id.izazdhiya.disasterapp.repository.viewModelsFactory
import id.izazdhiya.disasterapp.service.ApiClient
import id.izazdhiya.disasterapp.service.ApiService
import id.izazdhiya.disasterapp.viewmodel.DisasterViewModel
import id.izazdhiya.disasterapp.viewmodel.MainViewModel


internal class MainActivity : AppCompatActivity(){

    val searchFragment : Fragment = SearchFragment()
    val fragmentManager : FragmentManager = supportFragmentManager

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel.Factory(SettingsDataStore(this))
    }

    private lateinit var disasterTypeAdapter: DisasterTypeAdapter
    private lateinit var disasterTypeList: ArrayList<DisasterType>

    private lateinit var disasterAdapter: DisasterAdapter
    private val apiService: ApiService by lazy { ApiClient.instance }

    private val disasterRepository: DisasterRepository by lazy { DisasterRepository(apiService) }
    private val disasterViewModel: DisasterViewModel by viewModelsFactory { DisasterViewModel(disasterRepository) }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(FLAG_LAYOUT_NO_LIMITS)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

//        val sheet = findViewById<FrameLayout>(R.id.sheet)
//        BottomSheetBehavior.from(sheet).apply {
//            peekHeight = 100
//            this.state = BottomSheetBehavior.STATE_COLLAPSED
//        }

//        createDisasterType()

//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)

//        binding.buttonSetting.setOnClickListener {
//            val intent = Intent(this, SettingsActivity::class.java)
//            startActivity(intent)
//        }

    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        observeReports()
//    }

//    private fun createDisasterType() {
//
//        disasterTypeList = ArrayList<DisasterType>().apply {
//            addAll(
//                listOf(
//                    DisasterType("all", "All"),
//                    DisasterType("flood", "Flood"),
//                    DisasterType("earthquake", "Earthquake"),
//                    DisasterType("fire", "Fire"),
//                    DisasterType("haze", "Haze"),
//                    DisasterType("wind", "Wind"),
//                    DisasterType("volcano", "Volcano")
//                )
//            )
//        }
//
//        disasterTypeAdapter = DisasterTypeAdapter(disasterTypeList) { id: String, _ ->
//            binding.map.isVisible = false
//            binding.lottieNodata.isVisible = false
//            binding.lottieSearchlocation.isVisible = true
//            mMap.clear()
//            if (id == "all") {
//                observeReports()
//            } else {
//                observeReportsByDisaster(id)
//            }
//        }
//
//        binding.rvDisasterType.apply {
//            adapter = disasterTypeAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
//        }
//    }
//
//    private fun statusObserve(it: Resource<DisasterReport>){
//        when (it.status) {
//            Status.SUCCESS -> {
//                binding.pbDisaster.isVisible = false
//                binding.lottieSearchlocation.isVisible = false
//                setData(it)
//            }
//            Status.ERROR -> {
//                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                binding.pbDisaster.isVisible = false
//                binding.lottieNodata.isVisible = true
//                binding.lottieSearchlocation.isVisible = false
//            }
//            Status.LOADING -> {
//                binding.pbDisaster.isVisible = true
//            }
//        }
//    }
//
//    private fun setData(it: Resource<DisasterReport>){
//        if (it.data?.statusCode == 200) {
//            val disaster = it.data.result?.features
//            if (!disaster.isNullOrEmpty()) {
//                binding.rvDisasterType.isVisible = true
//                binding.map.isVisible = true
//
//                for (element in disaster) {
//                    val latLng = LatLng(element.geometry.coordinates[1], element.geometry.coordinates[0])
//                    mMap.addMarker(MarkerOptions().position(latLng).title(element.properties.disasterType))
//                }
//
//                val firstElement = disaster[0]
//                val latLng = LatLng(firstElement.geometry.coordinates[1], firstElement.geometry.coordinates[0])
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f))
//
//                binding.tvInfo.text = "Informasi Bencana Terkini"
//
//                disasterAdapter.updateData(disaster)
//
//                initRecyclerView()
//
//            } else {
//                Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show()
//                binding.lottieNodata.isVisible = true
//            }
//        } else {
//            Toast.makeText(this, "Bad Request", Toast.LENGTH_SHORT).show()
//            binding.lottieNodata.isVisible = true
//        }
//    }
//
//    private fun observeReports() {
//        disasterViewModel.getReports().observe(this) {
//            statusObserve(it)
//        }
//    }
//
//    private fun observeReportsByDisaster(disasterType: String) {
//        disasterViewModel.getReportsByDisaster(disasterType).observe(this) {
//            statusObserve(it)
//        }
//    }
//
//    private fun initRecyclerView() {
//        binding.rvItemBencana.apply {
//            adapter = disasterAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//    }
}



