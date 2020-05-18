package fr.iutbourg.sweetroutinemaker.data.repository

import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction

class FirebaseRepository<Model> : FirebaseDatabaseAction<Model> {
    override fun edit(model: Model, nodes: DatabaseReference) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(model: Model, nodes: DatabaseReference) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(model: Model, nodes: DatabaseReference) {
        nodes.push().setValue(model)
    }



}