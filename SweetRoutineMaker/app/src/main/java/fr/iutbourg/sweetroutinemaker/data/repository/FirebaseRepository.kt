package fr.iutbourg.sweetroutinemaker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.extension.GenericList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FirebaseRepository<Model> : FirebaseDatabaseAction<Model> {


    override fun edit(model: Model, nodes: DatabaseReference) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(model: Model, nodes: DatabaseReference) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(model: Model, nodes: DatabaseReference) {
        nodes.setValue(model)
    }

    override fun getActivitiesForChild(
        nodes: DatabaseReference,
        viewModelScope: CoroutineScope
    ): LiveData<Model> {
        val data = MutableLiveData<Model>()

        viewModelScope.launch {
            nodes.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    data.postValue(getChildActivities(snapshot.value as ArrayList<HashMap<String, Any>>) as Model)
                }
            })
        }
        return data
    }
    private fun getChildActivities(array: ArrayList<HashMap<String, Any>>): List<ActivityTodo> {
        var act = mutableListOf<ActivityTodo>()

        array.let {
            it.forEach { hashMap ->
                act.add(ActivityTodo(hashMap.keys.first(), hashMap))
            }
        }

        return act
    }


}
