package fr.iutbourg.sweetroutinemaker.ui.widget

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.CreationItemHandler
import fr.iutbourg.sweetroutinemaker.ui.activity.MainActivity
import fr.iutbourg.sweetroutinemaker.ui.fragment.ChildListFragment
import kotlinx.android.synthetic.main.dialog_input.*

class InputDialog (
    private val fragment: Fragment
) : BaseDialog(fragment.requireActivity()) {

    private val callback = fragment as CreationItemHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.dialog_input)

        dialog_input_button.setOnClickListener {
            callback.createItemFromString(dialog_input_edit_text.text.toString())
            this.dismiss()
        }

        setSizeForDialog()
    }
}