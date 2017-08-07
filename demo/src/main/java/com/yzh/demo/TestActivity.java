package com.yzh.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yzh.modaldialog.dialog.impl.CommonSelectorDialog;

/**
 * Created by yzh on 2017/8/7.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_activity);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
    }

    @Override
    public void onClick(View view) {
        CommonSelectorDialog dialog = new CommonSelectorDialog.Builder(this).setTitle("This is title!")
                .setDesc("This is description!\nbalabalabalabalabalabalabalabalabalabalabalabalabalabala!")
                .setNeutralButtonString("NeutraButton")
                .setNegativeButtonString("NegativeButton")
                .setPositiveButtonString("PositiveButton")
                .setCancelWhenClickOutside(true)
                .build();

        int result = dialog.doModal();

        if (this.resultTextView != null) {
            this.resultTextView.setText(String.valueOf(result) + ", " + idToResultStr(result));
        }
    }

    /**
     * return the result string by R.id.x
     *
     * @param id
     * @return
     */
    private String idToResultStr(int id) {
        String resultStr = "";
        switch (id) {
            case R.id.positiveButton:
                resultStr = "positiveButton";
                break;
            case R.id.negativeButton:
                resultStr = "negativeButton";
                break;
            case R.id.neutralButton:
                resultStr = "neutralButton";
                break;

        }
        return resultStr;
    }

}
