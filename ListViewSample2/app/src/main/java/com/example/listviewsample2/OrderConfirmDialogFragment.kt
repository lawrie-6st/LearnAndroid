package com.example.listviewsample2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast

class OrderConfirmDialogFragment :androidx.fragment.app.DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.dialog_title)
        builder.setMessage(R.string.dialog_msg)
        builder.setPositiveButton(R.string.dialog_btn_ok,DialogButtonClickListner())
        builder.setNegativeButton(R.string.dialog_btn_ng,DialogButtonClickListner())
        builder.setNeutralButton(R.string.dialog_btn_nu,DialogButtonClickListner())
        val dialog = builder.create()
        return dialog
    }

    private inner class DialogButtonClickListner : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, which: Int) {
            var msg = ""
            when(which){
                DialogInterface.BUTTON_POSITIVE ->
                    msg = getString(R.string.dailog_ok_toast)
                DialogInterface.BUTTON_NEGATIVE ->
                    msg = getString(R.string.dailog_ng_toast)
                DialogInterface.BUTTON_NEUTRAL ->
                    msg = getString(R.string.dailog_nu_toast)
            }
            Toast.makeText(activity,msg, Toast.LENGTH_LONG).show()
        }
    }
}