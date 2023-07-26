package id.izazdhiya.disasterapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.izazdhiya.disasterapp.databinding.ItemAreaBinding
import id.izazdhiya.disasterapp.model.DisasterArea


class DisasterAreaAdapter(
    private val disasterAreas: ArrayList<DisasterArea>,
    private val navController: NavController,
    private val onClickListener: (id: String, type: DisasterArea) -> Unit) : RecyclerView.Adapter<DisasterAreaAdapter.DisasterAreaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterAreaViewHolder {
        val binding = ItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisasterAreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisasterAreaViewHolder, position: Int) {
        holder.bind(disasterAreas[position])
    }

    override fun getItemCount(): Int = disasterAreas.size

    inner class DisasterAreaViewHolder(private val binding: ItemAreaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DisasterArea) {
            binding.apply {
                tvArea.text = item.name
                itemArea.setOnClickListener {
                    onClickListener.invoke(item.id, item)
                }
            }
        }
    }

//    fun filter(inputText: String) {
//        var inputText = inputText
//        inputText = inputText.lowercase(Locale.getDefault())
//        disasterAreas.clear()
////        if (inputText.isNotEmpty()) {
////            for (wp in arraylist) {
////                if (wp.getAnimalName().toLowerCase(Locale.getDefault()).contains(inputText)) {
////                    disasterAreas.add(wp)
////                }
////            }
////        }
//        notifyDataSetChanged()
//    }
}