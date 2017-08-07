package com.yzh.modaldialog.dialog.impl;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.yzh.modaldialog.dialog.interfaces.IModalDialog;
import com.yzh.modaldialog.dialog.utils.ThreadUtility;

/**
 * the base implementation of IModalDialog
 * Created by yzh on 2017/8/1.
 */
public abstract class BaseDialog implements IModalDialog {
    protected static final String TAG = BaseDialog.class.getCanonicalName();

    // result of dialog
    protected int mResult = AlertDialog.BUTTON_NEUTRAL;
    protected Context mContext = null;
    protected ModalDialogMsgHandler mModalDialogMsgHandler = null;
    // resource id of content view
    protected int mLayoutResId = 0;
    private int mTheme = 0;
    private Dialog mDialog = null;

    // keep the reference of the dialog which is showing
    protected static BaseDialog instance = null;

    public BaseDialog(@NonNull Context context) {
        this(context, 0, 0);
    }

    public BaseDialog(@NonNull Context context, int layoutResId) {
        this(context, layoutResId, 0);
    }

    public BaseDialog(@NonNull Context context, int layoutResId, int theme) {
        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mTheme = theme;
        if (this.mContext == null) {
            throw new IllegalArgumentException("context should not be null!");
        }
    }

    @Override
    public int doModal() {
        ThreadUtility.runOnUiThreadBlocked(new Runnable() {
            @Override
            public void run() {
                mResult = doModalOnUiThread();
            }
        });
        return this.mResult;
    }

    /**
     * do modal on ui thread
     *
     * @return
     */
    protected int doModalOnUiThread() {
        this.mResult = AlertDialog.BUTTON_NEUTRAL;
        this.mModalDialogMsgHandler = new ModalDialogMsgHandler();

        try {
            // if called when other dialog is showing
            if (BaseDialog.instance != null) {
                BaseDialog.instance.endModal(AlertDialog.BUTTON_NEUTRAL);
            }
            BaseDialog.instance = this;

            this.mDialog = new DialogImpl(mContext, mTheme);
            this.mDialog.show();
            Looper.loop();
        }
        catch (DisruptModalException e) {
            if (BaseDialog.instance == this) {
                BaseDialog.instance = null;
            }

            onClosed();
        }

        return mResult;
    }

    @Override
    public void endModal(int result) {
        if (this.mModalDialogMsgHandler != null) {
            this.mResult = result;
            this.mModalDialogMsgHandler.obtainMessage(ModalDialogMsgHandler.WHAT_EXIT_MODAL)
                    .sendToTarget();
        }
    }

    /**
     * call when a dialog is created, you can setup you dialog here
     *
     * @param dialog
     * @param savedInstanceState
     */
    protected abstract void onDialogCreate(Dialog dialog, Bundle savedInstanceState);

    /**
     * call when dialog is closed
     */
    @CallSuper
    protected void onClosed() {
        try {
            Dialog dialog = this.mDialog;
            this.mDialog = null;
            this.mModalDialogMsgHandler = null;
            dialog.dismiss();
        }
        catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * default dialog impl
     */
    private class DialogImpl extends Dialog {

        public DialogImpl(@NonNull Context context, @StyleRes int themeResId) {
            super(context, themeResId);
        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (getWindow() != null) {
                getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            BaseDialog.this.onDialogCreate(this, savedInstanceState);
        }

        @Override
        public void onDetachedFromWindow() {
            if (BaseDialog.this.mDialog != null) {
                // if detached(when canceled), we will pass the end modal result -> BUTTON_NEUTRAL
                BaseDialog.this.endModal(BUTTON_NEUTRAL);
            }
            super.onDetachedFromWindow();
        }
    }

    /**
     * the modal dialog msg handler
     */
    private static class ModalDialogMsgHandler extends Handler {
        /**
         * the msg's what value used to identify exit modal event
         */
        static final int WHAT_EXIT_MODAL = 0x10000045;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == WHAT_EXIT_MODAL) {
                throw new DisruptModalException();
            }
        }
    }

    /**
     * the exception used to exit modal
     */
    private static class DisruptModalException extends RuntimeException {}
}
