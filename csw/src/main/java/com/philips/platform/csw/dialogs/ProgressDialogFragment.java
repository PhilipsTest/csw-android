package com.philips.platform.csw.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.philips.platform.csw.permission.PermissionFragment;
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
            //On Back Key Pressed, Dismissing the dialog.
            dialog.dismiss();

            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                Fragment currentFragment = getCurrentFragment(fragmentManager);
                if (currentFragment instanceof PermissionFragment) {
                    doCallOnBackPressed();
                } else {
                    fragmentManager.popBackStack();
                }
            } else {
                doCallOnBackPressed();
            }
        }
        return true;
    }

    private void doCallOnBackPressed() {
        if (getActivity() != null) {
            this.getActivity().onBackPressed();
        }
    }

    private Fragment getCurrentFragment(FragmentManager fragmentManager) {
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
        return currentFragment;
    }
}
