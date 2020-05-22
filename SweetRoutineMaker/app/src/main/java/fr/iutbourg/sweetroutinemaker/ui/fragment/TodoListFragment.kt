package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.model.RESTRICTIONS
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.extension.applyRequire
import fr.iutbourg.sweetroutinemaker.extension.initRecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.SectionAdapter
import fr.iutbourg.sweetroutinemaker.ui.adapter.TodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
import fr.iutbourg.sweetroutinemaker.data.model.SectionItem
import kotlinx.android.synthetic.main.todolist_fragment.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * Use the [TodoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoListFragment : Fragment() {

    private lateinit var todoListViewModel: TodoListViewModel
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var sectionAdapter: SectionAdapter
    private lateinit var optionList: List<Options>
    private var childSelectedIndex: Int = 0
    private var activityIndex: Int = 0
    private var activityTodoSelectedIndex: Int = 0
    private var sections: ArrayList<SectionItem>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        args?.let {
            optionList = it.getSerializable("options") as ArrayList<Options>
            childSelectedIndex = it.getSerializable("childSelectedIndex") as Int
            activityIndex = it.getSerializable("activityIndex") as Int
            activityTodoSelectedIndex = it.getSerializable("activityTodoSelectedIndex") as Int
            sections = it.getSerializable("sections") as ArrayList<SectionItem>?
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
        setHasOptionsMenu(true)

        if (sections.isNullOrEmpty()) {
            todoListAdapter = TodoListAdapter()
            optionList.initRecyclerView(todoListViewModel, todoListAdapter)
                .observe(viewLifecycleOwner, Observer {
                    if (it.isNotEmpty()) {
                        todoListAdapter.submitList(it)
                    }
                })
            recycler_view_todo_list.applyRequire(
                todoListAdapter,
                GridLayoutManager(requireContext(), 2)
            )

            todoListViewModel.getTodos(
                FirebaseManager.firebaseInstance.database.reference
                    .child(PreferencesUtils.getString("userKey", "", requireContext())!!)
                    .child("children")
                    .child(childSelectedIndex.toString())
                    .child("activities")
                    .child(activityIndex.toString())
                    .child("activityTodoList")
                    .child(activityTodoSelectedIndex.toString())
            ).observe(viewLifecycleOwner, Observer {
                todoListAdapter.submitList(it)
            })
        }
        else{
            sectionAdapter = SectionAdapter()
            recycler_view_todo_list.apply {
                adapter = sectionAdapter
                layoutManager = LinearLayoutManager(this@TodoListFragment.requireContext())
                setHasFixedSize(true)
            }
            try {
                sectionAdapter.submitList(sections!!)
            }catch (e: Exception){
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        val searchMenuItem = menu.findItem(R.id.showOptions)
        searchMenuItem.setOnMenuItemClickListener {
            true
        }
    }

}

