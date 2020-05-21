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
    var activities: ArrayList<ActivityTodo>?
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
    var activityTodoBase64: String?,
    var activityTodoLabel: String?,
    var todoList: ArrayList<TodoList>?
): Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(null, null, null) {
        activityTodoBase64 = hashMap["activityTodoBase64"] as String?
        activityTodoLabel = hashMap["activityTodoLabel"] as String?

        val test = (hashMap["todoList"] as ArrayList<HashMap<String, String>>?)

        test.let{
            todoList = ArrayList()
        }
    }
}

//classe Lorsque l'on choisit une liste
@Keep
data class TodoList(
    var listLabel: String?,
    var listBase64: String?,
    var items: List<Options>?
): Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(null, null, null) {
        listLabel = hashMap["listLabel"] as String?
        listBase64 = hashMap["listBase64"] as String?

        val test = (hashMap["items"] as ArrayList<HashMap<String, String>>?)

        test.let {
            items = ArrayList()
        }
    }
}

enum class RESTRICTIONS(restriction: Int): Serializable {
    RESTRICTED(0),
    FULL_CONTROL(1)
}

data class ChildProfile(
    var key: String? = null,
    var name: String? = null,
    var todoList: ListActivityTodo? = null
): Serializable