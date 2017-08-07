package com.yzh.modaldialog.dialog.interfaces;

/**
 * Created by yzh on 2017/8/1.
 */

public interface IModalDialog {
    /**
     * show modal dialog on UI thread
     *
     * @return the dialog result
     */
    int doModal();

    /**
     * end the modal dialog by pass the result
     *
     * @param result
     */
    void endModal(int result);
}
