package com.tmobile.dynamicui.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tmobile.dynamicui.data.model.CardTypes.*
import com.tmobile.dynamicui.data.model.Cards
import com.tmobile.dynamicui.databinding.ListItemCardImageTitleDescriptionBinding
import com.tmobile.dynamicui.databinding.ListItemCardTextBinding
import com.tmobile.dynamicui.databinding.ListItemCardTitleDescriptionBinding
import com.tmobile.dynamicui.util.executeAfter

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = emptyList<Cards>()

    fun setData(data: List<Cards>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            IMAGE_TITLE_DESCRIPTION.ordinal -> ImageTitleDescriptionHolder(
                ListItemCardImageTitleDescriptionBinding.inflate(inflater, parent, false)
            )
            TITLE_DESCRIPTION.ordinal -> TitleDescriptionHolder(
                ListItemCardTitleDescriptionBinding.inflate(
                    inflater, parent, false
                )
            )
            else -> TextHolder(ListItemCardTextBinding.inflate(inflater, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val card = data[position]
        return when (card.card_type) {
            "image_title_description" -> IMAGE_TITLE_DESCRIPTION.ordinal
            "title_description" -> TITLE_DESCRIPTION.ordinal
            else -> TEXT.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            IMAGE_TITLE_DESCRIPTION.ordinal -> {
                val imageTitleDescriptionHolder = holder as ImageTitleDescriptionHolder
                imageTitleDescriptionHolder.bindView(data[position])
            }
            TITLE_DESCRIPTION.ordinal -> {
                val titleDescriptionHolder = holder as TitleDescriptionHolder
                titleDescriptionHolder.bindView(data[position])
            }
            else -> {
                val textHolder = holder as TextHolder
                textHolder.bindView(data[position])
            }
        }
    }

    class ImageTitleDescriptionHolder(private val binding: ListItemCardImageTitleDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Cards) {
            binding.executeAfter {
                data = item
            }
        }

    }

    class TitleDescriptionHolder(private val binding: ListItemCardTitleDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Cards) {
            binding.executeAfter {
                data = item
            }
        }
    }

    class TextHolder(private val binding: ListItemCardTextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Cards) {
            binding.executeAfter {
                data = item
            }
        }
    }

}