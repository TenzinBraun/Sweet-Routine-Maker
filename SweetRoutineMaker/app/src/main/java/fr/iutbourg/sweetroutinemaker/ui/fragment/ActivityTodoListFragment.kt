package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.CreationItemHandler
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityTodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityTodoListViewModel
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
import fr.iutbourg.sweetroutinemaker.ui.widget.InputDialog
import kotlinx.android.synthetic.main.activity_todolist_fragment.view.*


class ActivityTodoListFragment : Fragment(), ActivityTodoClickListener, CreationItemHandler {

    private lateinit var activityTodoListViewModel: ActivityTodoListViewModel
    private lateinit var activityTodoListAdapter: ActivityTodoListAdapter
    private lateinit var todoList: List<TodoList>
    private var itemClickedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            todoList = it.getSerializable("todoList") as ArrayList<TodoList>
            itemClickedPosition = it.getSerializable("position") as Int
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
        setHasOptionsMenu(true)
        activityTodoListAdapter =  ActivityTodoListAdapter(todoList, this)

        view.recycler_view_activity_todo.apply {
            adapter = activityTodoListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.child_menu, menu)
    }

    override fun onActivityTodoClickListener(items: List<Options>) {
        findNavController().navigate(R.id.action_activityTodoListFragment_to_todoListFragment,
            bundleOf("options" to items))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_menu_btn) {
            InputDialog(this).show() //call CreateItemFromString (callback)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun createItemFromString(name: String) {
        todoList.addElement(TodoList(name, null, ArrayList()))
        val nodes = FirebaseManager.firebaseInstance.database.reference
            .child(PreferencesUtils.getString("userKey", "", requireContext())!!)
            .child("children")
            .child(itemClickedPosition.toString())
            .child("activities")
            .child(itemClickedPosition.toString())
            .child("activityTodoList")

        activityTodoListViewModel.addActivityTodo(todoList, nodes)
    }

}

interface ActivityTodoClickListener {
    fun  onActivityTodoClickListener(items: List<Options>)
}

