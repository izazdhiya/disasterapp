package id.izazdhiya.disasterapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.izazdhiya.disasterapp.R
import id.izazdhiya.disasterapp.databinding.ItemBencanaBinding
import id.izazdhiya.disasterapp.model.DisasterArea
import id.izazdhiya.disasterapp.model.network.response.Feature
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DisasterAdapter(private val disaster: List<Feature>) : RecyclerView.Adapter<DisasterAdapter.DisasterViewHolder>() {

//    private val diffCallback = object : DiffUtil.ItemCallback<Feature>() {
//        override fun areItemsTheSame(
//            oldItem: Feature,
//            newItem: Feature
//        ): Boolean {
//            return oldItem.properties.pkey == newItem.properties.pkey
//        }
//
//        override fun areContentsTheSame(
//            oldItem: Feature,
//            newItem: Feature
//        ): Boolean {
//            return oldItem.hashCode() == newItem.hashCode()
//        }
//    }

//    private val listDiffer = AsyncListDiffer(this, diffCallback)

//    fun updateData(disaster: List<Feature>?) = listDiffer.submitList(disaster)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterViewHolder {
        val binding = ItemBencanaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisasterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisasterViewHolder, position: Int) {
//        holder.bind(listDiffer.currentList[position])
        holder.bind(disaster[position])
    }

//    override fun getItemCount(): Int = listDiffer.currentList.size
    override fun getItemCount(): Int = disaster.size

    inner class DisasterViewHolder(private val binding: ItemBencanaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Feature) {
            val instanceRegion = convertArea(item.properties.tags.instanceRegionCode)
            val createdAt = convertDate(item.properties.createdAt)
            binding.apply {
                tvTitle.text = item.properties.disasterType.capitalize()
                tvLocation.text = "Location:  $instanceRegion"
                tvDate.text = "Date: $createdAt"
//                tvCoordinate.text = "Coordinate: $coordinate"
                tvStatus.text = item.properties.status
//                tvDeskripsi.text = item.properties.text ?: "No Title Available"
                if (item.properties.imageUrl.isNullOrEmpty()){
                    ivImage.setImageResource(R.drawable.noimage)
                }else{
                    Glide.with(itemView.context)
                        .load(item.properties.imageUrl)
                        .into(ivImage)
                }
            }
        }
    }

    private fun convertArea(code: String): String {
        val dataArea = mapOf(
            "ID-AC" to "Aceh",
            "ID-BA" to "Bali",
            "ID-BB" to "Kep Bangka Belitung",
            "ID-BT" to "Banten",
            "ID-BE" to "Bengkulu",
            "ID-JT" to "Jawa Tengah",
            "ID-KT" to "Kalimantan Tengah",
            "ID-ST" to "Sulawesi Tengah",
            "ID-JI" to "Jawa Timur",
            "ID-KI" to "Kalimantan Timur",
            "ID-NT" to "Nusa Tenggara Timur",
            "ID-GO" to "Gorontalo",
            "ID-JK" to "DKI Jakarta",
            "ID-JA" to "Jambi",
            "ID-LA" to "Lampung",
            "ID-MA" to "Maluku",
            "ID-KU" to "Kalimantan Utara",
            "ID-MU" to "Maluku Utara",
            "ID-SA" to "Sulawesi Utara",
            "ID-SU" to "Sumatera Utara",
            "ID-PA" to "Papua",
            "ID-RI" to "Riau",
            "ID-KR" to "Kepulauan Riau",
            "ID-SG" to "Sulawesi Tenggara",
            "ID-KS" to "Kalimantan Selatan",
            "ID-SN" to "Sulawesi Selatan",
            "ID-SS" to "Sumatera Selatan",
            "ID-YO" to "DI Yogyakarta",
            "ID-JB" to "Jawa Barat",
            "ID-KB" to "Kalimantan Barat",
            "ID-NB" to "Nusa Tenggara Barat",
            "ID-PB" to "Papua Barat",
            "ID-SR" to "Sulawesi Barat",
            "ID-SB" to "Sumatera Barat",
        )

        return if (code in dataArea) {
            dataArea[code]!!
        } else {
            "Daerah tidak ada"
        }
    }

    private fun convertDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val parsedDate = inputFormat.parse(date)
        return outputFormat.format(parsedDate)
    }


}