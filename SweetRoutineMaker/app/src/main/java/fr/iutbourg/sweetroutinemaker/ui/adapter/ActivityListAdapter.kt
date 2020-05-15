package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ActivityItemViewHolder

class ActivityListAdapter :
    BaseItemAdapter<ActivityTodo, ActivityItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        holder.bind(itemList[position]){

        }
    }
}