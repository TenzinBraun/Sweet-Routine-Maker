package fr.iutbourg.sweetroutinemaker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.ListActivityTodo
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



    override fun getWithQuery(
        model: Model,
        nodes: DatabaseReference,
        orderBy: String,
        equalTo: String,
        viewModelScope: CoroutineScope
    ): LiveData<Model> {
        val data = MutableLiveData<Model>()

        val query: Query = nodes.orderByChild(orderBy).equalTo(equalTo)

        viewModelScope.launch {
            query.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (model is ListActivityTodo) {
                        data.postValue(getChildActivities(snapshot.value as Map<String, HashMap<String, Any>>) as Model)
                    }

                }

            })
        }
        return data
    }



   private fun getChildActivities(map: Map<String, HashMap<String, Any>>): ListActivityTodo {
        var act = ListActivityTodo(ArrayList())

        map?.let {
            for((key, value) in it.entries) {
                act = ListActivityTodo(key, value)
            }
        }
       return act
    }



}