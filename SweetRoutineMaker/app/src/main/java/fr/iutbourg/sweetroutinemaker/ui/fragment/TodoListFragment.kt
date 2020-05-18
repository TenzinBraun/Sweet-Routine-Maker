package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.model.RESTRICTIONS
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.extension.applyRequire
import fr.iutbourg.sweetroutinemaker.extension.initRecyclerView
import fr.iutbourg.sweetroutinemaker.extension.notifyAllOnDataSetChanged
import fr.iutbourg.sweetroutinemaker.ui.adapter.TodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
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
            optionList.addElement(Options("", "Test", RESTRICTIONS.FULL_CONTROL))
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
        todoListAdapter = TodoListAdapter()
        optionList.initRecyclerView(todoListViewModel, todoListAdapter)
            .observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    todoListAdapter.submitList(it)
                }
            })
        view.recycler_view_todo_list.applyRequire(
            todoListAdapter,
            GridLayoutManager(requireContext(), 2)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        val searchMenuItem = menu.findItem(R.id.showOptions)
        searchMenuItem.setOnMenuItemClickListener{
            true
        }
    }


    override fun updateAllOnValidation(position: Int) {
      if ((optionList.size) - 1 != position) {
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

