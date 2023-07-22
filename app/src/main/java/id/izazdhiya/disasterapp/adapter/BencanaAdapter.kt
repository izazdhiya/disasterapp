//package id.izazdhiya.disasterapp.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import id.izazdhiya.disasterapp.databinding.ItemBencanaBinding
//import id.izazdhiya.disasterapp.model.network.response.Reports
//
//class BencanaAdapter :
//    ListAdapter<Reports, BencanaAdapter.BencanaViewHolder>(BencanaComparator()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BencanaViewHolder {
//        val binding =
//            ItemBencanaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return BencanaViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: BencanaViewHolder, position: Int) {
//        val currentItem = getItem(position)
//        if (currentItem != null) {
//            holder.bind(currentItem)
//        }
//    }
//
//    inner class BencanaViewHolder(private val binding: ItemBencanaBinding) :
//        RecyclerView.ViewHolder(binding.root) {

//        fun bind(report: Reports) {
//            binding.apply {
//                if (report.image_url != null) {
//                    Glide.with(itemView.context)
//                        .load(product.image_url)
//                        .into(ivProductImage)
//                } else {
//                    ivProductImage.setImageResource(R.drawable.noimage)
//                }
//
//                tvTitle.text = product.name
//                tvDeskripsi.text = product.categories
//
//            }
//        }
//    }

//    class BencanaComparator : DiffUtil.ItemCallback<Reports>() {
//        override fun areItemsTheSame(oldItem: Reports, newItem: Reports) =
//            oldItem.name == newItem.name
//
//        override fun areContentsTheSame(oldItem: Reports, newItem: Reports) =
//            oldItem == newItem
//    }

//}