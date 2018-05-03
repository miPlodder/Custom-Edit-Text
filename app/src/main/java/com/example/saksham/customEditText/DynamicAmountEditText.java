package com.example.saksham.bettervisualizeredittext;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class DynamicAmountEditText extends AppCompatEditText implements TextWatcher{
    public DynamicAmountEditText(Context context) {
        super(context);
        init(null);
    }

    public DynamicAmountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DynamicAmountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {

        if (attrs == null) {
            return;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
