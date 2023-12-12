package prytdan.quizgame.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.content.ContextCompat
import prytdan.quizgame.R

object DialogInflater {

    @SuppressLint("InflateParams")
    fun showDialogWithText(
        layoutInflater: LayoutInflater,
        context: Context,
        string: String
    ): Dialog {
        val dialogInflater = layoutInflater.inflate(R.layout.check_internet_dialog, null)
        val dialog = Dialog(context)
        dialog.setContentView(dialogInflater)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.check_internet_dialog_background
            )
        )
        dialog.findViewById<TextView>(R.id.text_dialog_title).text = string
        dialog.setCancelable(true)
        dialog.show()
        return dialog
    }
}