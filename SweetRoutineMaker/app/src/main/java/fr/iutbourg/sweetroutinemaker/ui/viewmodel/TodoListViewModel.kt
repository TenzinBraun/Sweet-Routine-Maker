package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.iutbourg.sweetroutinemaker.data.model.Options

class TodoListViewModel : BaseViewModel<Options>() {




    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodoListViewModel() as T
        }
    }

    override fun getItemList(): LiveData<List<Options>> {
        TODO("Not yet implemented")
    }
}
