package fr.iutbourg.sweetroutinemaker.data.model

import android.util.Base64

data class Picture(
    val namePicture: String,
    val itemBase64: Base64,
    val tags: List<String>
)


data class Options(
    val todoItemB64 : String?,
    val todoItemLabel : String,
    val isRestricted: RESTRICTIONS = RESTRICTIONS.FULL_CONTROL
)

//Classe la plus haute (Homescreen)
data class ListActivityTodo(
    val activities: List<ActivityTodo>?
)

//Classe lorsque l'on choisit une activité
data class ActivityTodo(
    val activityTodoBase64: String?,
    val activityTodoLabel: String,
    val todoList: List<TodoList>?
)

//classe Lorsque l'on choisit une liste
data class TodoList(
    val listLabel: String,
    val listBase64: String?,
    val items: List<Options>?
)

enum class RESTRICTIONS(restriction: Int) {
    RESTRICTED(0),
    FULL_CONTROL(1)
}


data class ChildProfile (
    val key: String? = null,
    val name: String? = null,
    val todoList: ListActivityTodo? = null
)