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
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.extension.applyRequire
import fr.iutbourg.sweetroutinemaker.extension.initRecyclerView
import fr.iutbourg.sweetroutinemaker.ui.adapter.ActivityListAdapter
import fr.iutbourg.sweetroutinemaker.ui.adapter.TodoListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ActivityListViewModel
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_shopping_list.*


class ActivityListFragment(listActivity: ListActivityTodo) : Fragment() {

    private lateinit var activityListViewModel: ActivityListViewModel
    private lateinit var activityListAdapter: ActivityListAdapter
    private var activityTodoList = listActivity.activities
    val db = FirebaseDatabase.getInstance().reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.run {
            activityListViewModel = ViewModelProvider(
                this,
                TodoListViewModel
            ).get()
        } ?: throw IllegalStateException("Invalid Activity")
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityListAdapter = ActivityListAdapter()
        shoppingRecyclerView.applyRequire(activityListAdapter, LinearLayoutManager(requireContext()))
        activityTodoList!!.initRecyclerView(activityListViewModel, activityListAdapter)
            .observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    activityListAdapter.submitList(it)
                }
            })
    }
}