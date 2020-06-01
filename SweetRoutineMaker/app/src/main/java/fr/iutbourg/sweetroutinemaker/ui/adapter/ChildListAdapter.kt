package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.content.Intent
import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ChildItemViewHolder
import fr.iutbourg.sweetroutinemaker.ui.fragment.ChildClickListener
import kotlinx.android.synthetic.main.child_view_holder.view.*

class ChildListAdapter(
    override var itemList: List<ChildProfile>,
    private var clickListener: ChildClickListener
    ) : BaseItemAdapter<ChildProfile, ChildItemViewHolder>() {

    override fun submitList(list: List<ChildProfile>) {
        this.itemList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemViewHolder = ChildItemViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ChildItemViewHolder, position: Int) {
        var child = itemList[position]
        clickListener
        holder.bind(child) { childProfile ->

            //region View Holder - Bind view
            holder.itemView.apply {
                child_name_view_holder.text = childProfile.name
            }
            //endregion


            holder.itemView.setOnClickListener { // not sur about it, does click work on whole item ?
                clickListener.onChildClickListener(childProfile.todoList!!, position)
            }
        }



    }
}



