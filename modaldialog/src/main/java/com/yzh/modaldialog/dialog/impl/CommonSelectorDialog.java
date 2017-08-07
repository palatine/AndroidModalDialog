package com.yzh.modaldialog.dialog.impl;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.yzh.modaldialog.R;


public class CommonSelectorDialog extends SelectorDialog {
    /**
     * the textsize of this dialog
     */
    public enum TextSize {
        SMALLEST(12), SMALL(14), NORMAL(16), BIG(18), BIGGEST(20);

        int val;

        TextSize(int val) {
            this.val = val;
        }

        public int toValue() {
            return val;
        }
    }

    protected TextView mTitleTextView, mDescTextView;
    protected Button mPositiveButton, mNegativeButton, mNeutralButton;

    protected String mTitleString;
    protected String mDescString;
    protected String mPositiveButtonString;
    protected String mNegativeButtonString;
    protected String mNeutralButtonString;
    protected TextSize mTextSize = TextSize.NORMAL;
    protected int mTextGravity = Gravity.CENTER;

    /**
     * use builder for new instance
     *
     * @param title
     * @param desc
     * @param positiveButtonString
     * @param negativeButtonString
     * @param neutralButtonString
     * @param clickWhenClickOutside
     * @param textSize
     * @param textGravity
     */
    private CommonSelectorDialog(Context context,
            String title,
            String desc,
            String positiveButtonString,
            String negativeButtonString,
            String neutralButtonString,
            boolean clickWhenClickOutside,
            TextSize textSize,
            int textGravity) {
        super(context);
        this.mTitleString = title;
        this.mDescString = desc;
        this.mPositiveButtonString = positiveButtonString;
        this.mNegativeButtonString = negativeButtonString;
        this.mNeutralButtonString = neutralButtonString;
        this.mTextSize = textSize;
        this.mTextGravity = textGravity;
        super.cancelOnClickOutside = clickWhenClickOutside;
    }

    @Override
    protected void onDialogCreate(Dialog dialog, Bundle savedInstanceState) {
        super.mLayoutResId = hasThreeChoiceBtn() ? R.layout.dialog_common_selector_vertical : R.layout.dialog_common_selector;
        setGravity(Gravity.CENTER);
        setLayoutWidth(LayoutParams.WRAP_CONTENT);
        setWindowAnimations(android.R.anim.fade_in);
        super.onDialogCreate(dialog, savedInstanceState);

        this.mTitleTextView = (TextView) dialog.findViewById(R.id.titleTextView);
        this.mDescTextView = (TextView) dialog.findViewById(R.id.descTextView);
        this.mPositiveButton = (Button) dialog.findViewById(R.id.positiveButton);
        this.mNegativeButton = (Button) dialog.findViewById(R.id.negativeButton);
        this.mNeutralButton = (Button) dialog.findViewById(R.id.neutralButton);
        setupTitleDescButtons();
    }

    private void setupTitleDescButtons() {
        // for title
        boolean isEmpty = TextUtils.isEmpty(mTitleString);
        this.mTitleTextView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        this.mTitleTextView.setText(mTitleString);
        // for desc
        isEmpty = TextUtils.isEmpty(mDescString);
        this.mDescTextView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        this.mDescTextView.setText(mDescString);
        this.mDescTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, this.mTextSize.toValue());
        this.mDescTextView.setGravity(mTextGravity);
        // for mPositiveButton
        isEmpty = TextUtils.isEmpty(mPositiveButtonString);
        this.mPositiveButton.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        this.mPositiveButton.setText(mPositiveButtonString);
        // for mNegativeButton
        isEmpty = TextUtils.isEmpty(mNegativeButtonString);
        this.mNegativeButton.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        this.mNegativeButton.setText(mNegativeButtonString);
        // for mNeutralButton
        isEmpty = TextUtils.isEmpty(mNeutralButtonString);
        this.mNeutralButton.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        this.mNeutralButton.setText(mNeutralButtonString);
    }

    /**
     * whether has three choice button
     *
     * @return
     */
    private boolean hasThreeChoiceBtn() {
        return !TextUtils.isEmpty(mNeutralButtonString) && !TextUtils.isEmpty(mNegativeButtonString) && !TextUtils.isEmpty(mPositiveButtonString);
    }

    /**
     * Builder for construct a new instance of {@link CommonSelectorDialog}
     */
    public static class Builder {
        private Context context;
        private String title;
        private String desc;
        private String positiveButtonString;
        private String negativeButtonString;
        private String neutralButtonString;
        private TextSize textSize = TextSize.NORMAL;
        private int textGravity = Gravity.CENTER;
        // defulat is true
        private boolean cancelWhenClickOutside = true;

        public Builder(Context context) {
            this.context = context;
            if (context == null) {
                throw new RuntimeException("context can't be null!");
            }
        }

        public Builder setTitleResID(int titleResID) {
            this.title = getString(titleResID);
            return this;
        }

        public Builder setDescResID(int descResID) {
            this.desc = getString(descResID);
            return this;
        }

        public Builder setPositiveButtonResID(int positiveButtonResID) {
            this.positiveButtonString = getString(positiveButtonResID);
            return this;
        }

        public Builder setNegativeButtonResID(int negativeButtonResID) {
            this.negativeButtonString = getString(negativeButtonResID);
            return this;
        }

        public Builder setNeutralButtonResId(int neutralButtonResId) {
            this.neutralButtonString = getString(neutralButtonResId);
            return this;
        }

        public Builder setCancelWhenClickOutside(boolean cancelWhenClickOutside) {
            this.cancelWhenClickOutside = cancelWhenClickOutside;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder setTextSize(TextSize textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setTextGravity(int textGravity) {
            this.textGravity = textGravity;
            return this;
        }

        public Builder setPositiveButtonString(String positiveButtonString) {
            this.positiveButtonString = positiveButtonString;
            return this;
        }

        public Builder setNegativeButtonString(String negativeButtonString) {
            this.negativeButtonString = negativeButtonString;
            return this;
        }

        public Builder setNeutralButtonString(String neutralButtonString) {
            this.neutralButtonString = neutralButtonString;
            return this;
        }

        String getString(int strResId) {
            return context.getString(strResId);
        }

        public CommonSelectorDialog build() {
            return new CommonSelectorDialog(context, title, desc, positiveButtonString, negativeButtonString, neutralButtonString, cancelWhenClickOutside, textSize, textGravity);
        }
    }
}
