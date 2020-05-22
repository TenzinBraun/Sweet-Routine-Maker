package fr.iutbourg.sweetroutinemaker.ui.widget

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.extension.applyRequire
import fr.iutbourg.sweetroutinemaker.extension.hide
import fr.iutbourg.sweetroutinemaker.extension.show
import fr.iutbourg.sweetroutinemaker.ui.activity.MainActivity
import fr.iutbourg.sweetroutinemaker.ui.activity.TagListHandler
import fr.iutbourg.sweetroutinemaker.ui.adapter.AddTagAdapter
import kotlinx.android.synthetic.main.dialog_add_tag.*
import java.util.*


class TagAddDialog(activity: MainActivity) : BaseDialog(activity) {

    private lateinit var addTagAdapter: AddTagAdapter
    private var list = mutableListOf<String>()
    private val callback = activity as TagListHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        addTagAdapter = AddTagAdapter()
        setContentView(R.layout.dialog_add_tag)

        addTagToList.setOnClickListener {
            addtTagEditText.text.apply {
                trim()
                val tagEdited = toString()
                tagEdited.toLowerCase(Locale.ROOT)
                if (!list.contains(tagEdited))
                    list.addElement(tagEdited)

                addTagValidate.show()
            }
        }

        addTagValidate.setOnClickListener {
            callback.importTagList(list)
            this.dismiss()
        }

        if (list.isEmpty()) {
            addTagValidate.hide()
        }

        addTagRecycler.applyRequire(addTagAdapter, LinearLayoutManager(context))
        setSizeForDialog()
    }
}