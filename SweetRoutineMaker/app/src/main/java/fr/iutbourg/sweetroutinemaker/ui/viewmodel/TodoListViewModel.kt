package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.iutbourg.sweetroutinemaker.data.repository.ShoppingRepository

class TodoListViewModel(shoppingRepository : ShoppingRepository) : BaseViewModel() {




    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodoListViewModel(ShoppingRepository.shoppingInstance) as T
        }
    }

    override fun getItemList() {
        TODO("Not yet implemented")
    }
}
