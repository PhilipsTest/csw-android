package com.philips.platform.csw.mock;


import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class FragmentActivityMock extends FragmentActivity {

    FragmentManagerMock fragmentManagerMock;
    public boolean finishWasCalled = false;
    public boolean isFinishing = false;

    public FragmentActivityMock(FragmentManagerMock fragmentManagerMock) {
        this.fragmentManagerMock = fragmentManagerMock;
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return fragmentManagerMock;
    }

    public void finish() {
        finishWasCalled = true;
    }

    @Override
    public boolean isFinishing() {
        return isFinishing;
    }
}
