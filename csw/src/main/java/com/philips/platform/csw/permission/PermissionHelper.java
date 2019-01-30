/*
 * Copyright (c) 2018 Koninklijke Philips N.V.
 * All rights are reserved. Reproduction or dissemination
 * in whole or in part is prohibited without the prior written
 * consent of the copyright holder.
 */

package com.philips.platform.csw.permission;



public class PermissionHelper {
    private static volatile PermissionHelper permissionHelper;
    private MyAccountUIEventListener myAccountUIEventListener;

    public static synchronized PermissionHelper getInstance() {
        if (permissionHelper == null) {

                if (permissionHelper == null) {
                    permissionHelper = new PermissionHelper();
                }

        }
        return permissionHelper;
    }

    public MyAccountUIEventListener getMyAccountUIEventListener() {
        return myAccountUIEventListener;
    }

    public void setMyAccountUIEventListener(MyAccountUIEventListener myAccountUIEventListener) {
        this.myAccountUIEventListener = myAccountUIEventListener;
    }
}
