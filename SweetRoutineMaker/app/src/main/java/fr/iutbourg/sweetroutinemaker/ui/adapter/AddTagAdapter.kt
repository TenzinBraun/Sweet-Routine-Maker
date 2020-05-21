package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.extension.removeElement
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.AddTagHolder
import kotlinx.android.synthetic.main.tag_item_holder.view.*

class AddTagAdapter : BaseItemAdapter<String, AddTagHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTagHolder =
        AddTagHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: AddTagHolder, position: Int) {
        holder.bind(itemList[position]) { tag ->
            holder.itemView.let {
                it.addedTag.text = tag
                it.deleteAddedTag.setOnClickListener {
                    itemList.removeElement(position)
                    notifyDataSetChanged()
                }
            }
        }
    }
}
