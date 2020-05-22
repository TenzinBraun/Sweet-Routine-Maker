package fr.iutbourg.sweetroutinemaker.ui.widget

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.SectionHandler
import kotlinx.android.synthetic.main.dialog_add_section.*


class AddSectionDialog(context: FragmentActivity, private val callback: SectionHandler) :
    BaseDialog(context) {


    private var tags = mutableListOf<String>()
    private var shouldRecreateItSelf = false

    override fun onCreate(savedInstanceState: Bundle?) {
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_section)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_item,
            tags
        )
        spinner.adapter = adapter
        numberPicker.maxValue = 1000
        numberPicker.minValue = 1
        toggleRecreateSection.setOnCheckedChangeListener { _, isChecked ->
            shouldRecreateItSelf = isChecked
        }

        validateSection.setOnClickListener {
            callback.createSection(
                "spinner.selectedItem.toString()",
                numberPicker.value,
                shouldRecreateItSelf
            )
            dismiss()
        }
    }

}
