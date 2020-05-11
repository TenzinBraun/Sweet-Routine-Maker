package fr.iutbourg.sweetroutinemaker.data.model

data class TodoItem(
    val todoItemB64 : String?,
    val todoItemLabel : String
)

data class ListActivityTodo(
    val activities: List<ActivityTodo>?
)

data class ActivityTodo(
    val activityTodoBase64: String?,
    val activityTodoLabel: String,
    val options: ListOptions,
    val todoList: TodoList?
)

data class TodoList(
    val listLabel: String,
    val listBase64: String?,
    val items: List<TodoItem>?,
    val restrictions: RESTRICTIONS = RESTRICTIONS.FULL_CONTROL
)

data class TodoOption(
    val optionLabel: String,
    val optionIsActivated: Boolean = false
)

data class ListOptions(
    val targetActivityLabel: String,
    val openOption: List<TodoOption>
)

enum class RESTRICTIONS(restriction: Int) {
    RESTRICTED(0),
    HALF_CONTROL(1),
    FULL_CONTROL(2)
}