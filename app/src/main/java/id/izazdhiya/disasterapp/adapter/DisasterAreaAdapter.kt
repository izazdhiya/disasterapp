package id.izazdhiya.disasterapp.adapter

import android.graphics.Color
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import id.izazdhiya.disasterapp.databinding.ItemAreaBinding
import id.izazdhiya.disasterapp.model.DisasterArea
import java.lang.Character.toLowerCase
import java.util.Locale


class DisasterAreaAdapter(
    private val disasterAreas: ArrayList<DisasterArea>,
    private val navController: NavController,
    private val onClickListener: (id: String, type: DisasterArea) -> Unit) : RecyclerView.Adapter<DisasterAreaAdapter.DisasterAreaViewHolder>() {

    var listDisaster = disasterAreas

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterAreaViewHolder {
        val binding = ItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisasterAreaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisasterAreaViewHolder, position: Int) {
        holder.bind(listDisaster[position])
    }

    override fun getItemCount(): Int = listDisaster.size

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

    fun filter(searchText: String?) {
        val filteredDisasterAreas: ArrayList<DisasterArea> = if (!searchText.isNullOrBlank()) {
            disasterAreas.filter { disasterArea ->
                disasterArea.name.contains(searchText, ignoreCase = true)
            }.toCollection(ArrayList())
        } else {
            disasterAreas
        }

        listDisaster.clear()
        listDisaster.addAll(filteredDisasterAreas)
        notifyDataSetChanged()
    }
}