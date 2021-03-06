/*
 * Copyright (c) 2017 Koninklijke Philips N.V.
 * All rights are reserved. Reproduction or dissemination
 * in whole or in part is prohibited without the prior written
 * consent of the copyright holder.
 */

package com.philips.platform.csw.permission.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BasePermissionViewHolder extends RecyclerView.ViewHolder {
    public BasePermissionViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onViewRecycled();
}
