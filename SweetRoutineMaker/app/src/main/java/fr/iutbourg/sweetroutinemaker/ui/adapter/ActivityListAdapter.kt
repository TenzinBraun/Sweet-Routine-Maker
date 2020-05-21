package fr.iutbourg.sweetroutinemaker.ui.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.ui.adapter.viewholder.ActivityItemViewHolder
import fr.iutbourg.sweetroutinemaker.ui.fragment.ActivityClickListener
import kotlinx.android.synthetic.main.activity_view_holder.view.*

class ActivityListAdapter(
    override var itemList: List<ActivityTodo>,
    private var clickListener: ActivityClickListener
) :
    BaseItemAdapter<ActivityTodo, ActivityItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityItemViewHolder = ActivityItemViewHolder.create(parent)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ActivityItemViewHolder, position: Int) {
        val activity = itemList[position]
        holder.bind(activity) { act ->
            holder.itemView.apply {
                activity_name.text = act.activityTodoLabel
                Glide.with(this)
                    .load(act.activityTodoBase64)
                    .circleCrop()
                    .into(this.activity_image_view)

                setOnClickListener {
                    clickListener.onActivityClick(it, activity.todoList!!)
                }
            }
        }
    }
}