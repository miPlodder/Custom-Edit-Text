package com.example.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

//this edittext will create space after 'n' characters
//this edittext will also limit the number of characters to 'n'
public class BetterVisualizerEditText extends AppCompatEditText implements TextWatcher{

    private static final String TAG = BetterVisualizerEditText.class.getSimpleName();
    public static final int DEFAULT_WORD_LIMIT = 7;
    public static final int DEFAULT_SPACE_AFTER = 4;

    private int wordLimit;
    private int currWordLength;
    private int spaceAfter;
    private boolean isAdded = true;

    public BetterVisualizerEditText(Context context) {
        super(context);
        init(null);
    }

    public BetterVisualizerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BetterVisualizerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet set) {
        addTextChangedListener(this);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setKeyListener(DigitsKeyListener.getInstance("0123456789-"));

        if (set == null)
            return;

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.BetterVisualizerEditText);
        wordLimit = ta.getInteger(R.styleable.BetterVisualizerEditText_wordLimit, DEFAULT_WORD_LIMIT);
        spaceAfter = ta.getInteger(R.styleable.BetterVisualizerEditText_spaceAfter, DEFAULT_SPACE_AFTER);
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
        if (getActualText().length() <= wordLimit) {
            if (spaceAfter != 0 && (getActualText().length() % spaceAfter == 0) && !isErasing(s) && isAdded) {
                isAdded = false;
                append("-");
            } else {
                isAdded = true;
            }
            currWordLength = s.length();
        } else {
            s.replace(s.length() - 1, s.length(), "");
        }
    }

    private boolean isErasing(Editable s) {
        if (s.length() < currWordLength) {
            return true;
        } else {
            return false;
        }
    }

    public String getActualText() {
        return getText().toString().replace("-", "");
    }
}
