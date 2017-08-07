package com.yzh.modaldialog.dialog.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SelectorDialog extends PopupDialog {


    public SelectorDialog(@NonNull Context context) {
        super(context);
    }

    public SelectorDialog(@NonNull Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public SelectorDialog(@NonNull Context context, int layoutResId, int theme) {
        super(context, layoutResId, theme);
    }

    @Override
    protected void onDialogCreate(Dialog dialog, Bundle savedInstanceState) {
        super.onDialogCreate(dialog, savedInstanceState);

        ArrayList<View> buttons = new ArrayList<View>();
        searchButtons(dialog.getWindow()
                .getDecorView(), buttons);

        for (View v : buttons) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    endModal(v.getId());
                }
            });
        }
    }

    protected void searchButtons(View v, ArrayList<View> buttons) {
        if (!(v instanceof ViewGroup)) {
            return;
        }

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {
            v = vg.getChildAt(i);
            if (v instanceof Button) {
                buttons.add(v);
            }
            else if (v instanceof ImageButton) {
                buttons.add(v);
            }
            else {
                searchButtons(v, buttons);
            }
        }
    }
}
