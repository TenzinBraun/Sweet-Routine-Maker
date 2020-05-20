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
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityListViewModel
import fr.iutbourg.sweetroutinemaker.ui.widget.InputDialog
import kotlinx.android.synthetic.main.activity_list_fragment.view.*


class ActivityListFragment : Fragment(), ActivityClickListener, CreationItemHandler {

    private lateinit var activityListViewModel: ActivityListViewModel
    private lateinit var activityListAdapter: ActivityListAdapter
    private lateinit var activityTodoList: ListActivityTodo
    private var itemClickedPosition = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arg = arguments
        arguments?.let {
            activityTodoList = it.getSerializable("activities") as ListActivityTodo
            itemClickedPosition = it.getSerializable("position") as Int
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
        setHasOptionsMenu(true)

        activityListAdapter = ActivityListAdapter(activityTodoList.activities!!, this)

        //activityListViewModel.getChildActivities()

        view.recycler_view_activity.apply {
            adapter = activityListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.child_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_menu_btn) {
            InputDialog(this).show() //call CreateItemFromString (callback)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityClick(view: View, todoList: ArrayList<TodoList>) {
        findNavController().navigate(
            R.id.action_activityListFragment_to_activityTodoListFragment,
            bundleOf("todoList" to todoList)
        )
    }

    override fun createItemFromString(name: String) {
        activityTodoList.activities!!.add(ActivityTodo(null, name, ArrayList()))
        val nodes = FirebaseManager.firebaseInstance.database.reference
            .child(PreferencesUtils.getString("userKey", "", requireContext())!!)
            .child("children")
            .child(itemClickedPosition.toString())
            .child("activities")

        activityListViewModel.addActivitiesForChild(
            activityTodoList.activities!!,
            nodes
        )
    }

}

interface ActivityClickListener {
    fun onActivityClick(view: View, todoList: ArrayList<TodoList>)
}