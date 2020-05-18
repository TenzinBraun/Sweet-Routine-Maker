package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.iutbourg.sweetroutinemaker.data.model.TodoList

class ActivityTodoListViewModel : BaseViewModel<TodoList>() {

    override fun getItemList(): LiveData<List<TodoList>> {
        TODO("Not yet implemented")
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ActivityTodoListViewModel() as T
        }
    }
}