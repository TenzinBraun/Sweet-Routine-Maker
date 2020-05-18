package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.extension.applyRequire
import fr.iutbourg.sweetroutinemaker.extension.initRecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityListViewModel
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
import kotlinx.android.synthetic.main.activity_list_fragment.view.*


class ActivityListFragment: Fragment(), ActivityClickListener {

    private lateinit var activityListViewModel: ActivityListViewModel
    private lateinit var activityListAdapter: ActivityListAdapter
    private lateinit var activityTodoList: ListActivityTodo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arg = arguments
        arg.let {
            activityTodoList = it?.getSerializable("activities") as ListActivityTodo
            activityTodoList.activities!!.add(ActivityTodo(null, "Courses", ArrayList()))
            activityTodoList.activities!!.add(ActivityTodo(null, "Peinture", ArrayList()))

        }
        activity?.run {
            activityListViewModel = ViewModelProvider(
                this,
                ActivityListViewModel
            ).get()
        } ?: throw IllegalStateException("Invalid Activity")
            return inflater.inflate(R.layout.activity_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityListAdapter = ActivityListAdapter(activityTodoList.activities!!, this)

        view.recycler_view_activity.apply {
            adapter = activityListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onActivityClick(view: View, todoList: ArrayList<TodoList>) {
        findNavController().navigate(
            R.id.action_activityListFragment_to_activityTodoListFragment,
            bundleOf("todoList" to todoList)
        )
    }
}

interface ActivityClickListener {
    fun onActivityClick(view: View, todoList: ArrayList<TodoList>)
}