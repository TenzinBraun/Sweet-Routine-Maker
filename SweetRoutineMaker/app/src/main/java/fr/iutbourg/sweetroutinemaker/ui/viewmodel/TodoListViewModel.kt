package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository

class TodoListViewModel(
    private val firebaseRepository: FirebaseRepository<List<Options>>
) : BaseViewModel<Options>() {

    fun addTodo(options: List<Options>, nodes: DatabaseReference)
            = firebaseRepository.add(options, nodes)

    fun getTodos(nodes: DatabaseReference)
            = firebaseRepository.getTodo(nodes, viewModelScope)


    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodoListViewModel(FirebaseDatabaseAction.firebaseContext()) as T
        }
    }

    override fun getItemList(): LiveData<List<Options>> {
        TODO("Not yet implemented")
    }
}
