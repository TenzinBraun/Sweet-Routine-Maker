package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.extension.applyRequire
import fr.iutbourg.sweetroutinemaker.extension.initRecyclerView
import fr.iutbourg.sweetroutinemaker.extension.notifyAllOnDataSetChanged
import fr.iutbourg.sweetroutinemaker.extension.onValidationTodoItem
import fr.iutbourg.sweetroutinemaker.ui.adapter.TodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TodoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoListFragment(private val todoList: TodoList) : Fragment(), UserActionOnList {

    private lateinit var todoListViewModel: TodoListViewModel
    private lateinit var todoListAdapter: TodoListAdapter
    private var itemList = todoList.items
    val db = FirebaseDatabase.getInstance().reference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            todoListViewModel = ViewModelProvider(
                this,
                TodoListViewModel
            ).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.todolist_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        todoListAdapter = TodoListAdapter(this)
//        shoppingRecyclerView.applyRequire(todoListAdapter, LinearLayoutManager(requireContext()))
//        itemList!!.initRecyclerView(todoListViewModel, todoListAdapter)
//            .observe(viewLifecycleOwner, Observer {
//                if(it.isNotEmpty()){
//                    todoListAdapter.submitList(it)
//                }
//            })
    }


    override fun updateAllOnValidation(position: Int) {
//        shoppingRecyclerView.onValidationTodoItem(position) {
//            (adapter as TodoListAdapter).notifyAllOnDataSetChanged()
//        }
    }
}

interface UserActionOnList {
    fun updateAllOnValidation(position: Int)
}
