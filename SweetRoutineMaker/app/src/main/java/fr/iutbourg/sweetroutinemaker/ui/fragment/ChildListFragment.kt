package fr.iutbourg.sweetroutinemaker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.ui.adapter.ChildListAdapter
import kotlinx.android.synthetic.main.childlist_fragment.view.*
import java.util.*

class ChildListFragment : Fragment(), ChildClickListener {
    private lateinit var childListAdapter: ChildListAdapter
    private lateinit var childList: List<ChildProfile>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        args.let {
            childList = it?.getSerializable("childrenList") as ArrayList<ChildProfile>

        }
        return inflater.inflate(R.layout.childlist_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childListAdapter = ChildListAdapter(childList, this)

        view.recycler_view_child.apply {
            adapter = childListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    override fun onChildClickListener(view: View, activityTodo: ListActivityTodo) {
        //view.findNavController().navigate()
    }
}

interface ChildClickListener {
    fun onChildClickListener(view: View, activityTodo: ListActivityTodo)
}