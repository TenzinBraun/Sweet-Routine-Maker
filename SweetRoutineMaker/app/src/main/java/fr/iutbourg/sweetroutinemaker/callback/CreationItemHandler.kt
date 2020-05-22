package fr.iutbourg.sweetroutinemaker.callback

interface CreationItemHandler {
    fun createItemFromString(name: String)
}

interface EditingModeHandler {
    fun launchEditingListMode(listName: String?)
}