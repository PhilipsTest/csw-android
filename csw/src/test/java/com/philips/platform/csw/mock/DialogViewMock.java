package com.philips.platform.csw.mock;


import androidx.fragment.app.FragmentActivity;

import com.philips.platform.csw.dialogs.DialogView;

public class DialogViewMock extends DialogView {

    public boolean isDialogVisible = false;

    @Override
    public void showDialog(FragmentActivity activity, String title, String body) {
        this.isDialogVisible = true;
    }

}
