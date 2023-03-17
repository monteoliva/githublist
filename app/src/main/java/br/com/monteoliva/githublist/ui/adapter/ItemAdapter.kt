package br.com.monteoliva.githublist.ui.adapter

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.repository.core.extensions.loadImage
import br.com.monteoliva.githublist.repository.core.extensions.validation
import br.com.monteoliva.githublist.repository.model.data.Item
import br.com.monteoliva.githublist.ui.components.BoxData

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {
    private var list = emptyList<Item>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(items: MutableList<Item>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
    override fun getItemId(position: Int): Long = list[position].id.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = getItem(position)

        val stars: Double = item.stargazersCount.toDouble() / 1000
        val forks: Double = item.forksCount.toDouble() / 1000

        holder.apply {
            ownerName.text = item.owner.login.validation()
            repoName.text  = item.name.validation()
            starsNumber.setValue(roundOffDecimal(stars) + "k")
            forksNumber.setValue(roundOffDecimal(forks) + "k")

            itemView.apply {
                item.owner?.avatarUrl?.let { ownerImage.loadImage(context, it) }
            }
        }
    }

    private fun getItem(position: Int): Item = list[position]

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ownerName: TextView   = itemView.findViewById(R.id.ownerName)
        val repoName: TextView    = itemView.findViewById(R.id.repoName)
        val ownerImage: ImageView = itemView.findViewById(R.id.ownerImage)
        val starsNumber: BoxData  = itemView.findViewById(R.id.starsNumber)
        val forksNumber: BoxData  = itemView.findViewById(R.id.forksNumber)
    }

    private fun roundOffDecimal(number: Double): String {
        val df = DecimalFormat("##.#", DecimalFormatSymbols(Locale.US))
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number)
    }
}