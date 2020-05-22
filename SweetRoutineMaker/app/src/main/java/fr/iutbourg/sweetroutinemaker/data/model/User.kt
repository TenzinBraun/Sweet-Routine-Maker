package fr.iutbourg.sweetroutinemaker.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import fr.iutbourg.sweetroutinemaker.extension.addElement
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
    @SerializedName("children") var children: ArrayList<ChildProfile>? = null,
    @SerializedName("pictures") var pictures: ArrayList<PictureTodo>? = null,
    @SerializedName("tags") var tags: ArrayList<String>? = null
)  : Serializable {
    constructor(key: String, hashMap: HashMap<String, Any>): this(key, null, null, null, null, null, null, null, null, null) {
        uid = hashMap["uid"] as String
        email = hashMap["email"] as String

        val childList: ArrayList<HashMap<String, String>>?  = (hashMap["children"] as ArrayList<HashMap<String, String>>)
        val pictureList: ArrayList<HashMap<String, String>>? = (hashMap["pictures"] as ArrayList<HashMap<String, String>>)
        val tagList: ArrayList<String>? = (hashMap["tags"] as ArrayList<String>)

        childList?.let {
            children = ArrayList()
            childList.forEach { child ->
                children!!.add(ChildProfile(null,child["name"], null))
            }
        }

        pictureList?.let {
            pictures = ArrayList()
            it.forEach { picture ->
                val tempTagsOfPictures = ArrayList<String>()
                val tagsOfPicture = (picture["tags"] as ArrayList<String>)
                tagsOfPicture.let {
                    tagsOfPicture.forEach { tag ->
                        tempTagsOfPictures.add(tag)
                    }
                }
                pictures!!.addElement(PictureTodo(picture["itemBase64"]!!, tempTagsOfPictures))

            }
        }

        tagList?.let {
            tags = ArrayList()
            tagList.forEach {
                tags!!.addElement(it)
            }
        }

    }
}




