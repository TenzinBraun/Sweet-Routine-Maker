package fr.iutbourg.sweetroutinemaker.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class User (
    var key: String? = null,
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("isAuthenticated") var isAuthenticated: Boolean? = null,
    @SerializedName("isNew") var isNew: Boolean? = null,
    @SerializedName("isCreated") var isCreated: Boolean? = null,
    @SerializedName("children") var children: ArrayList<ChildProfile>? = null
)  : Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(key, null, null, null, null, null, null, null) {
        uid = hashMap["uid"] as String

        val test = (hashMap["children"] as ArrayList<HashMap<String, String>>)
        test.let {
            children = ArrayList()
        }
        test.forEach {
            children?.add(ChildProfile(null,it["name"], null))
        }
        val smt = test
    }
}




