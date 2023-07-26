package id.izazdhiya.disasterapp.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.izazdhiya.disasterapp.R
import id.izazdhiya.disasterapp.adapter.DisasterAreaAdapter
import id.izazdhiya.disasterapp.databinding.FragmentSearchBinding
import id.izazdhiya.disasterapp.model.DisasterArea
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var disasterAreaAdapter: DisasterAreaAdapter
    private lateinit var disasterAreaList: ArrayList<DisasterArea>

    private val startDateCalendar = Calendar.getInstance()
    private val endDateCalendar = Calendar.getInstance()

    private var startDate: String? = null
    private var endDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.svSearchArea.requestFocus()

        moveToMaps()
        createDisasterArea()
        searchByDate()
    }

    private fun moveToMaps() {
        binding.ivBackToMaps.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_mapsFragment)
            it.findNavController().popBackStack()
        }
    }

    private fun createDisasterArea() {

        disasterAreaList = ArrayList<DisasterArea>().apply {
            addAll(
                listOf(
                    DisasterArea("ID-AC", "Aceh"),
                    DisasterArea("ID-BA", "Bali"),
                    DisasterArea("ID-BB", "Kep Bangka Belitung"),
                    DisasterArea("ID-BT", "Banten"),
                    DisasterArea("ID-BE", "Bengkulu"),
                    DisasterArea("ID-JT", "Jawa Tengah"),
                    DisasterArea("ID-KT", "Kalimantan Tengah"),
                    DisasterArea("ID-ST", "Sulawesi Tengah"),
                    DisasterArea("ID-JI", "Jawa Timur"),
                    DisasterArea("ID-KI", "Kalimantan Timur"),
                    DisasterArea("ID-NT", "Nusa Tenggara Timur"),
                    DisasterArea("ID-GO", "Gorontalo"),
                    DisasterArea("ID-JK", "DKI Jakarta"),
                    DisasterArea("ID-JA", "Jambi"),
                    DisasterArea("ID-LA", "Lampung"),
                    DisasterArea("ID-MA", "Maluku"),
                    DisasterArea("ID-KU", "Kalimantan Utara"),
                    DisasterArea("ID-MU", "Maluku Utara"),
                    DisasterArea("ID-SA", "Sulawesi Utara"),
                    DisasterArea("ID-SU", "Sumatera Utara"),
                    DisasterArea("ID-PA", "Papua"),
                    DisasterArea("ID-RI", "Riau"),
                    DisasterArea("ID-KR", "Kepulauan Riau"),
                    DisasterArea("ID-SG", "Sulawesi Tenggara"),
                    DisasterArea("ID-KS", "Kalimantan Selatan"),
                    DisasterArea("ID-SN", "Sulawesi Selatan"),
                    DisasterArea("ID-SS", "Sumatera Selatan"),
                    DisasterArea("ID-YO", "DI Yogyakarta"),
                    DisasterArea("ID-JB", "Jawa Barat"),
                    DisasterArea("ID-KB", "Kalimantan Barat"),
                    DisasterArea("ID-NB", "Nusa Tenggara Barat"),
                    DisasterArea("ID-PB", "Papua Barat"),
                    DisasterArea("ID-SR", "Sulawesi Barat"),
                    DisasterArea("ID-SB", "Sumatera Barat"),
                )
            )
        }

        disasterAreaAdapter = DisasterAreaAdapter(disasterAreaList, findNavController()) { id, type ->
            val bundle = Bundle()
            bundle.putString("area", id)

            view?.findNavController()?.navigate(R.id.action_searchFragment_to_mapsFragment, bundle)
//            view?.findNavController()?.popBackStack()

        }

        binding.rvDisasterArea.apply {
            adapter = disasterAreaAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    private fun searchByDate() {
        binding.etStartDate.setOnClickListener { showStartDatePickerDialog() }
        binding.etEndDate.setOnClickListener { showEndDatePickerDialog() }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        startDate = dateFormat.format(startDateCalendar.time)
        endDate = dateFormat.format(endDateCalendar.time)
        binding.btnSubmit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("startDate", startDate)
            bundle.putString("endDate", endDate)
            view?.findNavController()?.navigate(R.id.action_searchFragment_to_mapsFragment, bundle)
//            view?.findNavController()?.popBackStack()
        }
    }

    private fun showStartDatePickerDialog() {
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                startDateCalendar.set(Calendar.YEAR, year)
                startDateCalendar.set(Calendar.MONTH, month)
                startDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateStartDate()

                endDateCalendar.set(Calendar.HOUR_OF_DAY, 0)
                endDateCalendar.set(Calendar.MINUTE, 0)
                endDateCalendar.set(Calendar.SECOND, 0)
                endDateCalendar.set(Calendar.MILLISECOND, 0)
            },
            startDateCalendar.get(Calendar.YEAR),
            startDateCalendar.get(Calendar.MONTH),
            startDateCalendar.get(Calendar.DAY_OF_MONTH)
        )

        datePicker.show()
    }

    private fun showEndDatePickerDialog() {
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                endDateCalendar.set(Calendar.YEAR, year)
                endDateCalendar.set(Calendar.MONTH, month)
                endDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateEndDate()

                endDateCalendar.set(Calendar.HOUR_OF_DAY, 0)
                endDateCalendar.set(Calendar.MINUTE, 0)
                endDateCalendar.set(Calendar.SECOND, 0)
                endDateCalendar.set(Calendar.MILLISECOND, 0)
            },
            endDateCalendar.get(Calendar.YEAR),
            endDateCalendar.get(Calendar.MONTH),
            endDateCalendar.get(Calendar.DAY_OF_MONTH)
        )

        datePicker.show()
    }

    private fun updateStartDate() {
        val dateFormat = "dd-MM-yyyy"
        val formattedDate = SimpleDateFormat(dateFormat, Locale.getDefault()).format(startDateCalendar.time)
        binding.etStartDate.setText(formattedDate)
    }

    private fun updateEndDate() {
        val dateFormat = "dd-MM-yyyy"
        val formattedDate = SimpleDateFormat(dateFormat, Locale.getDefault()).format(endDateCalendar.time)
        binding.etEndDate.setText(formattedDate)
    }

    fun formatDateToDesiredFormat(calendar: Calendar): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}