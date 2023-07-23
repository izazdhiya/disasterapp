package id.izazdhiya.disasterapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.izazdhiya.disasterapp.databinding.ItemBencanaBinding
import id.izazdhiya.disasterapp.model.network.response.Feature
import id.izazdhiya.disasterapp.model.network.response.Result

class DisasterAdapter(private val onClickListener: (pkey: Int, disaster: Feature) -> Unit) :
    RecyclerView.Adapter<DisasterAdapter.DisasterViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Feature>() {
        override fun areItemsTheSame(
            oldItem: Feature,
            newItem: Feature
        ): Boolean {
            return oldItem.properties.pkey == newItem.properties.pkey
        }

        override fun areContentsTheSame(
            oldItem: Feature,
            newItem: Feature
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffCallback)

    fun updateData(disaster: Result?) = listDiffer.submitList(disaster?.features)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterViewHolder {
        val binding = ItemBencanaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisasterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisasterViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    inner class DisasterViewHolder(private val binding: ItemBencanaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Feature) {
            binding.apply {
                tvTitle.text = item.properties.title.toString()
                tvDeskripsi.text = item.properties.text
                Glide.with(itemView.context)
                    .load(item.properties.imageUrl)
                    .into(ivImage)
//                tvMovieRating.text = item.voteAverage.toString()
//                itemMovie.setOnClickListener {
//                    onClickListener.invoke(item.id, item)
//                }

            }
        }
    }

}