package com.yzh.modaldialog.dialog.impl;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.yzh.modaldialog.R;

public class PopupDialog extends BaseDialog {
    // the show position of this dialog
    protected int gravity = Gravity.BOTTOM;
    protected int layoutWidth = LayoutParams.MATCH_PARENT;
    protected int layoutHeight = LayoutParams.WRAP_CONTENT;

    // the anim of dialog when showing
    protected int windowAnimations = R.style.bottom_up_animation;
    // the background color of dialog
    protected int backgroundColor = Color.TRANSPARENT;
    protected boolean cancelOnClickOutside = true;
    protected boolean cancelable = true;

    public PopupDialog(@NonNull Context context) {
        super(context);
    }

    public PopupDialog(@NonNull Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public PopupDialog(@NonNull Context context, int layoutResId, int theme) {
        super(context, layoutResId, theme);
    }


    @Override
    protected void onDialogCreate(Dialog dialog, Bundle savedInstanceState) {
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setGravity(gravity);
                window.setLayout(layoutWidth, layoutHeight);
                window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
                window.setWindowAnimations(windowAnimations);
            }
            dialog.setContentView(mLayoutResId);
            dialog.setCancelable(cancelable);
            dialog.setCanceledOnTouchOutside(cancelOnClickOutside);
        }
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setLayoutWidth(int layoutWidth) {
        this.layoutWidth = layoutWidth;
    }

    public void setLayoutHeight(int layoutHeight) {
        this.layoutHeight = layoutHeight;
    }

    public void setWindowAnimations(int windowAnimations) {
        this.windowAnimations = windowAnimations;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setCancelOnClickOutside(boolean cancelOnClickOutside) {
        this.cancelOnClickOutside = cancelOnClickOutside;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }
}
