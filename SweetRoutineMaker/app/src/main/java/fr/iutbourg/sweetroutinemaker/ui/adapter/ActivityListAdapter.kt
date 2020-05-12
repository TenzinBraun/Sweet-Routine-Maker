package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ActivityItemViewHolder

class ActivityListAdapter :
    BaseItemAdapter<ActivityTodo, ActivityItemViewHolder>() {

    override var itemList: List<ActivityTodo> = emptyList()

    override fun submitList(list: List<ActivityTodo>) {
        itemList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}