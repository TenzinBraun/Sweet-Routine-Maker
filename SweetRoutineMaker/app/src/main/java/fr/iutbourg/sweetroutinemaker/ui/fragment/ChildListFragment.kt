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
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.ui.adapter.ChildListAdapter
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.ChildListViewModel
import fr.iutbourg.sweetroutinemaker.ui.widget.InputDialog
import kotlinx.android.synthetic.main.childlist_fragment.view.*
import kotlin.collections.ArrayList

class ChildListFragment : Fragment(), ChildClickListener, CreationItemHandler {
    private lateinit var childListAdapter: ChildListAdapter
    private lateinit var childList: List<ChildProfile>
    private lateinit var childListViewModel: ChildListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            childList = it.getSerializable("childrenList") as ArrayList<ChildProfile>

            childList.forEach{ child -> // on vérifie si le tableau d'activité est créer pour chaque enfant
                if (child.todoList == null) {
                    child.todoList = ListActivityTodo(ArrayList())
                }
            }
        }
        activity?.run {
            childListViewModel = ViewModelProvider(this, ChildListViewModel).get()
        } ?: throw IllegalStateException("Invalid Activity")

        return inflater.inflate(R.layout.childlist_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        childListAdapter = ChildListAdapter(childList, this)

        view.recycler_view_child.apply {
            adapter = childListAdapter
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


    override fun createItemFromString(name: String) {
        childList.addElement(ChildProfile(null, name, ListActivityTodo(ArrayList())))

        childListViewModel.addChildListForCurrentUser(
            childList,
            FirebaseManager.firebaseInstance.database.reference
                .child(PreferencesUtils.getString("userKey", "", requireContext())!!)
                .child("children")
        )
    }

    override fun onChildClickListener(activityTodo: ListActivityTodo, childIndex: Int) {
        findNavController().navigate(R.id.action_childListFragment_to_activityListFragment,
            bundleOf("activities" to activityTodo, "position" to childIndex))
    }

}

interface ChildClickListener {
    fun onChildClickListener(activityTodo: ListActivityTodo, childIndex: Int)
}