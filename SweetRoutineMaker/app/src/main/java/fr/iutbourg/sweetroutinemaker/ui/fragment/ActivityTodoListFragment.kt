package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.CreationItemHandler
import fr.iutbourg.sweetroutinemaker.callback.EditingModeHandler
import fr.iutbourg.sweetroutinemaker.callback.SectionHandler
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityTodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityTodoListViewModel
import fr.iutbourg.sweetroutinemaker.ui.widget.AddSectionDialog
import fr.iutbourg.sweetroutinemaker.ui.widget.InputDialog
import fr.iutbourg.sweetroutinemaker.data.model.SectionItem
import kotlinx.android.synthetic.main.activity_todolist_fragment.view.*


class ActivityTodoListFragment : Fragment(), ActivityTodoClickListener, CreationItemHandler,
    EditingModeHandler, SectionHandler {

    private lateinit var activityTodoListViewModel: ActivityTodoListViewModel
    private lateinit var activityTodoListAdapter: ActivityTodoListAdapter
    private var todoList = ArrayList<TodoList>()
    private var sections = ArrayList<SectionItem>()
    private var listSectionName = ""
    private var childSelectedIndex: Int = 0
    private var activityIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sections = ArrayList()
        arguments?.let {
            //todoList = it.getSerializable("todoList") as ArrayList<TodoList>
            childSelectedIndex = it.getSerializable("childSelectedIndex") as Int
            activityIndex = it.getSerializable("activityIndex") as Int
            //todoList.addElement(TodoList("smt 1", null, ArrayList()))
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
        setHasOptionsMenu(true)
        activityTodoListAdapter = ActivityTodoListAdapter(todoList, this)

        view.recycler_view_activity_todo.apply {
            adapter = activityTodoListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        activityTodoListViewModel.getActivityTodoList(
            FirebaseManager.firebaseInstance.database.reference
                .child(PreferencesUtils.getString("userKey", "", requireContext())!!)
                .child("children")
                .child(childSelectedIndex.toString())
                .child("activities")
                .child(activityIndex.toString())
                .child("activityTodoList")

        ).observe(viewLifecycleOwner, Observer {
            todoList = it as ArrayList<TodoList>
            activityTodoListAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.child_menu, menu)
    }

    override fun onActivityTodoClickListener(todoList: TodoList, position: Int) {
        if(todoList.sections.isNullOrEmpty()){
            findNavController().navigate(
                R.id.action_activityTodoListFragment_to_todoListFragment,
                bundleOf(
                    "options" to todoList.items,
                    "childSelectedIndex" to childSelectedIndex,
                    "activityIndex" to activityIndex,
                    "activityTodoSelectedIndex" to position,
                    "sections" to null
                )
            )
        }
        else {
            findNavController().navigate(
                R.id.action_activityTodoListFragment_to_todoListFragment,
                bundleOf(
                    "options" to todoList.items,
                    "childSelectedIndex" to childSelectedIndex,
                    "activityIndex" to activityIndex,
                    "activityTodoSelectedIndex" to position,
                    "sections" to todoList.sections
                )
            )
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_menu_btn) {
            InputDialog(this).show() //call CreateItemFromString (callback)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun createItemFromString(name: String) {
        if(sections.isNullOrEmpty()){
            todoList.addElement(TodoList(name, null, ArrayList(), false, null))
        }
        else {
            todoList.addElement(TodoList(name, null, ArrayList(), true, sections))
        }
        val nodes = FirebaseManager.firebaseInstance.database.reference
            .child(PreferencesUtils.getString("userKey", "", requireContext())!!)
            .child("children")
            .child(childSelectedIndex.toString())
            .child("activities")
            .child(activityIndex.toString())
            .child("activityTodoList")
        activityTodoListAdapter.submitList(todoList)

        activityTodoListViewModel.addActivityTodo(todoList, nodes)
    }

    override fun launchEditingListMode(listName: String?) {
        listName?.let {
            listSectionName = it
        }
        AddSectionDialog(requireActivity(), this).show()
    }

    override fun createSection(
        sectionName: String,
        value: Int,
        shouldRecreateItSelf: Boolean
    ) {
        val itemList = List(value) {
            sectionName
        }
        val section = SectionItem("sectionName", itemList)
        sections.add(section)
        if (shouldRecreateItSelf) {
            launchEditingListMode(null)
        }
        else {
            createItemFromString(listSectionName)
        }
    }
}


interface ActivityTodoClickListener {
    fun onActivityTodoClickListener(todoList: TodoList, position: Int)
}

