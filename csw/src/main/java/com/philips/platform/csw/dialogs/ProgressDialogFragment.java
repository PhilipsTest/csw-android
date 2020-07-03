package com.philips.platform.csw.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import androidx.annotation.NonNull;

import com.philips.platform.uid.view.widget.AlertDialogFragment;

public class ProgressDialogFragment extends AlertDialogFragment implements DialogInterface.OnKeyListener {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        dialog.setOnKeyListener(this);
        return dialog;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //On Back Key Pressed, Dismissing the dialog and finishing the activity also.
            dialog.dismiss();
            //if finish activity not require, please remove getActivity().finish()
            if (getActivity() != null) {
                this.getActivity().finish();
            }
        }
        return true;
    }
}
