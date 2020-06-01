package fr.iutbourg.sweetroutinemaker.ui.widget

import android.os.Bundle
import androidx.fragment.app.Fragment
import fr.iutbourg.sweetroutinemaker.R

import fr.iutbourg.sweetroutinemaker.callback.CreationItemHandler
import fr.iutbourg.sweetroutinemaker.callback.EditingModeHandler
import fr.iutbourg.sweetroutinemaker.extension.hide
import fr.iutbourg.sweetroutinemaker.extension.show
import kotlinx.android.synthetic.main.dialog_input.*

class InputDialog (
    private val fragment: Fragment
) : BaseDialog(fragment.requireActivity()) {

    private val callback = fragment as CreationItemHandler
    private var editingModeHandler: EditingModeHandler? = null
    private var isSwitchOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.dialog_input)
        if(fragment is EditingModeHandler) {
            editingModeHandler = fragment
        }

        editingModeHandler?.let {
            isFreeEditing.show()
            isFreeEditing.setOnCheckedChangeListener { _, isChecked ->
                isSwitchOn = isChecked
            }
        }


        dialog_input_button.setOnClickListener {
            if(isSwitchOn){
                editingModeHandler?.launchEditingListMode(dialog_input_edit_text.text.toString())
            }
            else {
                callback.createItemFromString(dialog_input_edit_text.text.toString())
            }
            this.dismiss()
        }

        setSizeForDialog()
    }
}