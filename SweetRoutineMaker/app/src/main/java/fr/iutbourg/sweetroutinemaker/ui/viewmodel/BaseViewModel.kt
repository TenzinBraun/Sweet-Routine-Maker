package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
   abstract fun getItemList()
}
