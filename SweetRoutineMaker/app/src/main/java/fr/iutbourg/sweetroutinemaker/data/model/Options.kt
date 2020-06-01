package fr.iutbourg.sweetroutinemaker.data.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class PictureTodo(
    var itemBase64: String?,
    var tags: List<String>?
): Serializable

@Keep
data class Options(
    var todoItemB64 : String?,
    var todoItemLabel : String?,
    var isRestricted: RESTRICTIONS = RESTRICTIONS.FULL_CONTROL
): Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(null, null, RESTRICTIONS.FULL_CONTROL) {
        todoItemB64  = hashMap["todoItemB64"] as String?
        todoItemLabel  = hashMap["todoItemLabel"] as String?
        isRestricted  = hashMap["isRestricted"] as RESTRICTIONS
    }
}

//Classe la plus haute (Homescreen)
@Keep
data class ListActivityTodo(
    var activities: ArrayList<ActivityTodo>?
): Serializable

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
    var items: List<Options>?,
    var editedList: Boolean? = false,
    var sections: ArrayList<SectionItem>?
): Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(null, null, null, false, null) {
        listLabel = hashMap["listLabel"] as String?
        listBase64 = hashMap["listBase64"] as String?
        editedList = hashMap["editedList"] as Boolean?
        sections = hashMap["sections"] as ArrayList<SectionItem>?

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