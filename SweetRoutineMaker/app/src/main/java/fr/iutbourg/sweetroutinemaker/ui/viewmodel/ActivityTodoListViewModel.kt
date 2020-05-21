package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository

class ActivityTodoListViewModel(
    private val firebaseRepository: FirebaseRepository<List<TodoList>>
): BaseViewModel<TodoList>() {

    fun addActivityTodo(todoList: List<TodoList>, nodes: DatabaseReference)
            = firebaseRepository.add(todoList, nodes)

    fun getActivityTodoList(nodes: DatabaseReference)
            = firebaseRepository.getTodoListForActivities(nodes, viewModelScope)



    override fun getItemList(): LiveData<List<TodoList>> {
        TODO("Not yet implemented")
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ActivityTodoListViewModel(FirebaseDatabaseAction.firebaseContext()) as T
        }
    }
}