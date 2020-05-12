package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo

class ActivityListViewModel : BaseViewModel<ActivityTodo>() {


    override fun getItemList(): LiveData<List<ActivityTodo>> {
        TODO("Not yet implemented")
    }
}