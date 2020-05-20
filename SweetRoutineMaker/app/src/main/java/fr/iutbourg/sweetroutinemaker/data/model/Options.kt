package fr.iutbourg.sweetroutinemaker.data.model

import android.util.Base64
import androidx.annotation.Keep
import java.io.Serializable


data class PictureTodo(
    var itemBase64: String,
    var tags: List<String>
)

@Keep
data class Options(
    val todoItemB64 : String?,
    val todoItemLabel : String,
    val isRestricted: RESTRICTIONS = RESTRICTIONS.FULL_CONTROL
): Serializable

//Classe la plus haute (Homescreen)
@Keep
data class ListActivityTodo(
    val activities: ArrayList<ActivityTodo>?
): Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(null) {

        val test = (hashMap["activities"]  as ArrayList<HashMap<String, String>>)

        test.forEach {
            activities!!.add(ActivityTodo(null, it["name"]!!, null))
        }
    }
}

//Classe lorsque l'on choisit une activité
@Keep
data class ActivityTodo(
    val activityTodoBase64: String?,
    val activityTodoLabel: String,
    val todoList: ArrayList<TodoList>?
): Serializable

//classe Lorsque l'on choisit une liste
@Keep
data class TodoList(
    val listLabel: String,
    val listBase64: String?,
    val items: List<Options>?
): Serializable

enum class RESTRICTIONS(restriction: Int): Serializable {
    RESTRICTED(0),
    FULL_CONTROL(1)
}

data class ChildProfile(
    var key: String? = null,
    var name: String? = null,
    var todoList: ListActivityTodo? = null
): Serializable