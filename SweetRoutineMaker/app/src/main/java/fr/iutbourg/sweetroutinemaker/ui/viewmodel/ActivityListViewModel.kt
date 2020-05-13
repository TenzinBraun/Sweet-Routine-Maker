package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.repository.ShoppingRepository

class ActivityListViewModel : BaseViewModel<ActivityTodo>() {


    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ActivityListViewModel() as T
        }
    }


    override fun getItemList(): LiveData<List<ActivityTodo>> {
        TODO("Not yet implemented")
    }
}