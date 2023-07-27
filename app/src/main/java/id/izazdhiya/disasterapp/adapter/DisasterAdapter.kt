package id.izazdhiya.disasterapp.adapter

import android.content.ContentValues.TAG
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
            binding.apply {
                tvTitle.text = item.properties.disasterType
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

}