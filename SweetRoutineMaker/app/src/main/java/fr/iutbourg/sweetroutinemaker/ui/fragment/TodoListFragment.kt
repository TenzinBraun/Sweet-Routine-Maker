package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.model.RESTRICTIONS
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.extension.*
import fr.iutbourg.sweetroutinemaker.ui.adapter.TodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
import kotlinx.android.synthetic.main.todolist_fragment.*
import kotlinx.android.synthetic.main.todolist_fragment.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [TodoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoListFragment : Fragment(), UserActionOnList {

    private lateinit var todoListViewModel: TodoListViewModel
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var optionList: List<Options>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        args?.let {
            optionList = it.getSerializable("options") as ArrayList<Options>
            optionList.addElement(Options(null, "Test", RESTRICTIONS.FULL_CONTROL))
        }
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
        todoListAdapter = TodoListAdapter(this)
        itemList!!.initRecyclerView(todoListViewModel, todoListAdapter)
            .observe(viewLifecycleOwner, Observer {
                if(it.isNotEmpty()){
                    todoListAdapter.submitList(it)
                }
            })
        todoListAdapter = TodoListAdapter(optionList)

        view.recycler_view_todo_list.applyRequire(
            todoListAdapter,
            GridLayoutManager(requireContext(), 2)
        )
    }


    override fun updateAllOnValidation(position: Int) {
        if((itemList?.size)!! - 1 != position) {
//            shoppingRecyclerView.onValidationTodoItem(position) {
                todoListAdapter.notifyAllOnDataSetChanged()
//            }
//        }else {
//            shoppingRecyclerView.onValidationLastTodoItem {
//                todoListAdapter.notifyAllOnDataSetChanged()
//            }
        }
    }
}

interface UserActionOnList {
    fun updateAllOnValidation(position: Int)
}

