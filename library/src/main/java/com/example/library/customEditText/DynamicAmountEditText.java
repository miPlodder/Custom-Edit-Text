package com.example.library.customEditText;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.View;

import com.example.library.R;

//this custom edittext will add specific number of integer and decimal places
public class DynamicAmountEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener{

    private static String TAG = DynamicAmountEditText.class.getSimpleName();
    public static int DEFAULT_INTEGER_LIMIT = 5;
    public static int DEFAULT_DECIMAL_LIMIT = 2;

    private int integerLimit ;
    private int decimalLimit ;

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

    private void init(AttributeSet attrs) {

        addTextChangedListener(this);
        setOnFocusChangeListener(this);
        setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        setKeyListener(DigitsKeyListener.getInstance("0123456789."));

        if (attrs == null) {
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DynamicAmountEditText);
        integerLimit = ta.getInteger(R.styleable.DynamicAmountEditText_integerLimit, DEFAULT_INTEGER_LIMIT);
        decimalLimit = ta.getInteger(R.styleable.DynamicAmountEditText_decimalLimit, DEFAULT_DECIMAL_LIMIT);
        ta.recycle();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        //check limit for integer part
        if (getInteger(s).length() > integerLimit) {
            removeLast(s);
        }

        //check limit for decimal part
        if (getDecimal(s).length() > decimalLimit) {
            removeLast(s);
        }

        //check for zero or one dot
        if (s.length() > 0 && s.charAt(s.length()-1) == '.' && !isDotLegal(s)) {
            removeLast(s);
        }
    }

    public String getInteger(Editable s) {
        try {
            return s.toString().split("\\.")[0];
        } catch (ArrayIndexOutOfBoundsException ioobe) {
            return "";
        }
    }

    public String getDecimal(Editable s) {
        try {
            return s.toString().split("\\.")[1];
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return "";
        } 
    }

    private boolean isDotLegal(Editable s) {
        int counter = 0 ;
        char[] chs = new char[s.length()];
        s.getChars(0, s.length(), chs, 0);

        for (char item : chs) {
            if (item == '.') {
                counter++;
            }
        }
        return counter > 1 ? false : true ;
    }

    private Editable removeLast(Editable s) {
        return s.replace(s.length()-1, s.length(), "");
    }
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (selStart != getText().length()) {
            setSelection(getText().length());
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (getEditableText().length() != 0 && getEditableText().charAt(getEditableText().length() - 1) == '.') {
            removeLast(getEditableText());
        }
    }

    //use this method instead of getText() to get text
    public Editable getActualText() {
        if (getEditableText().length() != 0 && getEditableText().charAt(getEditableText().length() - 1) == '.') {
            return removeLast(getEditableText());
        }
        return getEditableText();
    }
}