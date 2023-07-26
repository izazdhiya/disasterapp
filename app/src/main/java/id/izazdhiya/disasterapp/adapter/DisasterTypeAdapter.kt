package id.izazdhiya.disasterapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import id.izazdhiya.disasterapp.R
import id.izazdhiya.disasterapp.databinding.ItemTypeBinding
import id.izazdhiya.disasterapp.model.DisasterType

class DisasterTypeAdapter(
    private val disasterType: ArrayList<DisasterType>,
    private val onClickListener: (id: String, type: DisasterType) -> Unit) : RecyclerView.Adapter<DisasterTypeAdapter.DisasterTypeViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION

    init {
        val allPosition = findPositionByType("all")
        if (allPosition != RecyclerView.NO_POSITION) {
            selectedPosition = allPosition
        }
    }
    private fun findPositionByType(id: String): Int {
        for ((index, item) in disasterType.withIndex()) {
            if (item.id == id) {
                return index
            }
        }
        return RecyclerView.NO_POSITION
    }

    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterTypeViewHolder {
        val binding = ItemTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisasterTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisasterTypeViewHolder, position: Int) {
        holder.bind(disasterType[position])
    }

    override fun getItemCount(): Int = disasterType.size

    inner class DisasterTypeViewHolder(private val binding: ItemTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DisasterType) {
            binding.apply {
                tvType.text = item.name

                val isSelected = position == selectedPosition
                itemType.setCardBackgroundColor(if (isSelected) Color.parseColor("#3B8628") else Color.WHITE)
                tvType.setTextColor(if (isSelected) Color.WHITE else Color.BLACK)
                ivIcon.setImageDrawable(if (isSelected) ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_plus_white, null) else ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_plus, null))

                itemType.setOnClickListener {
                    setSelectedPosition(position)
                    onClickListener.invoke(item.id, item)
                }
            }
        }
    }

}