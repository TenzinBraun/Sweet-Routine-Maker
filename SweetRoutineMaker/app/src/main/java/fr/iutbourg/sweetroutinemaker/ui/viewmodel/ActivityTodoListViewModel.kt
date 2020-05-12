package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import fr.iutbourg.sweetroutinemaker.data.model.TodoList

class ActivityTodoListViewModel : BaseViewModel<TodoList>() {

    override fun getItemList(): LiveData<List<TodoList>> {
        TODO("Not yet implemented")
    }
}