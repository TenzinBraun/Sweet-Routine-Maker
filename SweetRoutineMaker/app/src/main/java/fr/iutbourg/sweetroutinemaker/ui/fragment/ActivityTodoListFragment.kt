package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityTodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityTodoListViewModel
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
import kotlinx.android.synthetic.main.activity_todolist_fragment.view.*


class ActivityTodoListFragment : Fragment(), ActivityTodoClickListener {

    private lateinit var activityTodoListViewModel: ActivityTodoListViewModel
    private lateinit var activityTodoListAdapter: ActivityTodoListAdapter
    private lateinit var todoList: List<TodoList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        args?.let {
            todoList = it.getSerializable("todoList") as ArrayList<TodoList>
            todoList.addElement(TodoList("TODO 1", null, ArrayList()))
        }
        activity?.run {
            activityTodoListViewModel = ViewModelProvider(
                this,
                ActivityTodoListViewModel
            ).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.activity_todolist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityTodoListAdapter =  ActivityTodoListAdapter(todoList, this)

        view.recycler_view_activity_todo.apply {
            adapter = activityTodoListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onActivityTodoClickListener(items: List<Options>) {
        findNavController().navigate(R.id.action_activityTodoListFragment_to_todoListFragment,
            bundleOf("options" to items))
    }

}

interface ActivityTodoClickListener {
    fun  onActivityTodoClickListener(items: List<Options>)
}

