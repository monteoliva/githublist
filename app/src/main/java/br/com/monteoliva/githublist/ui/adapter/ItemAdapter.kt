package br.com.monteoliva.githublist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.repository.model.list.Item
import br.com.monteoliva.githublist.ui.components.BoxData

class ItemAdapter(private val list: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(this, view)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = list[position].id!!.toLong()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = getItem(position)

        val stars: Double = item.stargazersCount!!.toDouble() / 1000
        val forks: Double = item.forksCount!!.toDouble() / 1000

        holder.apply {
            ownerName.text = item.owner?.login
            repoName.text  = item.name
            starsNumber.setValue(roundOffDecimal(stars) + "k")
            forksNumber.setValue(roundOffDecimal(forks) + "k")


            //Picasso.get().load(item.owner?.avatarUrl).into(ownerImage)
        }
    }

    private fun getItem(position: Int): Item = list[position]

    class ViewHolder(itemAdapter: ItemAdapter, itemView: View): RecyclerView.ViewHolder(itemView) {
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