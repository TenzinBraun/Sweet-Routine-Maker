package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityTodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityTodoListViewModel
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel


class ActivityTodoListFragment : Fragment() {

    private lateinit var activityTodoListViewModel: ActivityTodoListViewModel
    private lateinit var activityTodoListAdapter: ActivityTodoListAdapter
//    private var activityTodoList = activityListTodo.todoList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            activityTodoListViewModel = ViewModelProvider(
                this,
                TodoListViewModel
            ).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.activity_todolist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        activityTodoListAdapter = ActivityTodoListAdapter()
//        shoppingRecyclerView.applyRequire(activityTodoListAdapter, LinearLayoutManager(requireContext()))
//        activityTodoList!!.initRecyclerView(activityTodoListViewModel, activityTodoListAdapter)
//            .observe(viewLifecycleOwner, Observer {
//                if (it.isNotEmpty()) {
//                    activityTodoListAdapter.submitList(it)
//                }
//            })
    }
}