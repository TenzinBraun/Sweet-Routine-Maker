package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository

class ActivityListViewModel(
    private val firebaseRepository: FirebaseRepository<List<ActivityTodo>>
) : BaseViewModel<ActivityTodo>() {

    fun addActivitiesForChild(activities: List<ActivityTodo> , nodes: DatabaseReference)
            = firebaseRepository.add(activities, nodes)

    fun getChildActivities(nodes: DatabaseReference)
    = firebaseRepository.getActivitiesForChild(nodes, viewModelScope)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ActivityListViewModel(FirebaseDatabaseAction.firebaseContext()) as T
        }
    }

    override fun getItemList(): LiveData<List<ActivityTodo>> {
        TODO("Not yet implemented")
    }
}