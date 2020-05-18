package fr.iutbourg.sweetroutinemaker.ui.widget

import android.app.Dialog
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity

abstract class BaseDialog(private val activity: FragmentActivity) : Dialog(activity){

    protected fun setSizeForDialog() {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window?.attributes)
        lp.width = displayMetrics.widthPixels * 95 / 100
        when (1) {
            0 -> lp.height = WindowManager.LayoutParams.MATCH_PARENT
            1 -> lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            else -> lp.height = displayMetrics.heightPixels * 1 / 100
        }
        this.window?.attributes = lp
    }

}