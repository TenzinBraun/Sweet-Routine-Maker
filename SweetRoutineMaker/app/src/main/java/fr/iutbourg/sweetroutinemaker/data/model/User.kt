package fr.iutbourg.sweetroutinemaker.data.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("isAuthenticated") var isAuthenticated: Boolean? = null,
    @SerializedName("isNew") var isNew: Boolean? = null,
    @SerializedName("isCreated") var isCreated: Boolean? = null
)


