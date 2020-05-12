package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Model> : ViewModel() {
   abstract fun getItemList() : LiveData<List<Model>>
}
