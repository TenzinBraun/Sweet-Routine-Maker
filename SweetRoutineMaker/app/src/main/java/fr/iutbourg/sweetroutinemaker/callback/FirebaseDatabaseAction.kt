package fr.iutbourg.sweetroutinemaker.callback

interface FirebaseDatabaseAction<Model> {
    fun edit(position: Int, model: Model)
    fun delete(model: Model)
    fun add(model: Model)
}