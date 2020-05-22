package fr.iutbourg.sweetroutinemaker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.ActivityTodo
import fr.iutbourg.sweetroutinemaker.data.model.Options
import fr.iutbourg.sweetroutinemaker.data.model.TodoList
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
                    snapshot.value?.let {
                        data.postValue(getChildActivities(it as ArrayList<HashMap<String, Any>>) as Model)
                    }

                }
            })
        }
        return data
    }
    private fun getChildActivities(array: ArrayList<HashMap<String, Any>>?): List<ActivityTodo> {
        val act = mutableListOf<ActivityTodo>()

        array.let {
            it?.forEach { hashMap ->
                act.add(ActivityTodo(hashMap.keys.first(), hashMap))
            }
        }

        return act
    }

    override fun getTodoListForActivities(
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
                    snapshot.value?.let {
                        data.postValue(getActivityTodoList(it as ArrayList<HashMap<String, Any>>) as Model)
                    }
                }

            })
        }
        return data
    }
   private fun getActivityTodoList(array: ArrayList<HashMap<String, Any>>?): List<TodoList> {
       var actTodo = mutableListOf<TodoList>()

       array?.let {
           it.forEach { hashMap ->
               actTodo.add(TodoList(hashMap.keys.first(), hashMap))
           }
       }

       return actTodo
   }

    override fun getTodo(
        nodes: DatabaseReference,
        viewModelScope: CoroutineScope
    ): LiveData<Model> {
        val data = MutableLiveData<Model>()

        viewModelScope.launch {
            nodes.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.value is ArrayList<*>?) {
                        data.postValue(getTodos(snapshot.value as ArrayList<HashMap<String, Any>>?) as Model)
                    }
                }
            })
        }
        return data
    }

    private fun getTodos(array: ArrayList<HashMap<String, Any>>?): List<Options> {
        val todos = mutableListOf<Options>()

        array?.let {
            it.forEach { hashMap ->
                todos.add(Options(hashMap.keys.first(), hashMap))
            }
        }

        return todos
    }


}

class Collections<M>: ArrayList<M>(), Collection<M>
