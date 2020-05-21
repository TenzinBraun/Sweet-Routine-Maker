package fr.iutbourg.testcustomrecyclerview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.SectionHandler
import kotlinx.android.synthetic.main.dialog_add_section.*


class AddSectionDialog(context: Context, private val callback: SectionHandler) : Dialog(context){


    private var tags = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_section)
        tags.add("Entrée")
        tags.add("Dessert")
        tags.add("Traiteur")
        tags.add("Plat Chaud")
        tags.add("Plage")
        tags.add("Montagne")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_item,
            tags
        )
        spinner.adapter = adapter
        numberPicker.maxValue = 1000
        numberPicker.minValue = 1
        validateSection.setOnClickListener {
            callback.createSection(spinner.selectedItem.toString(), numberPicker.value)
            dismiss()
        }
    }

}
