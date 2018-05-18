package com.example.library.customEditText;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

import com.example.library.R;

//this EditText will create space after 'n' characters
//this EditText will also limit the number of characters to 'n'
public class BetterVisualizerEditText extends AppCompatEditText implements TextWatcher {

    private static final String TAG = BetterVisualizerEditText.class.getSimpleName();
    public static final int DEFAULT_WORD_LIMIT = 7;
    public static final int DEFAULT_SPACE_AFTER = 4;

    //number of total digits
    private int wordLimit;

    //number of digits after which you want space
    private int spaceAfter;

    private boolean isErasing;
    private boolean isFromReplaced = false;
    private int counter = 0;
    private int previousWordLength ;

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

    private void init(AttributeSet set) {
        addTextChangedListener(this);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setKeyListener(DigitsKeyListener.getInstance("0123456789 "));

        if (set == null)
            return;

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.BetterVisualizerEditText);
        wordLimit = ta.getInteger(R.styleable.BetterVisualizerEditText_wordLimit, DEFAULT_WORD_LIMIT);
        spaceAfter = ta.getInteger(R.styleable.BetterVisualizerEditText_spaceAfter, DEFAULT_SPACE_AFTER);
        ta.recycle();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        previousWordLength = s.length();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (previousWordLength > s.length()) {
            isErasing = true;
        } else {
            isErasing = false;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (getText().length() != 0) {
            if (getText().toString().length() <= (wordLimit + getSpaceCount())) {
                if (isFromReplaced) {
                    isFromReplaced = false;
                    return;
                }
                if (!isErasing) { //writing
                    counter++;
                    if (counter == spaceAfter+1) {
                        s.insert(s.length()-1, " " );
                        counter = 1;
                    }
                } else { //erasing
                    counter--;
                    if (counter == 0) {
                        removeLastIfSpace(s);
                        counter = spaceAfter;
                    }
                }
            } else {
                isFromReplaced = true;
                s.replace(s.length() - 1, s.length(), "");
            }
            setSelection(s.length());
        } else {
            counter = 0;
        }
    }

    //use this method instead of getText() to get the text
    public String getActualText() {
        return getText().toString().replace(" ", "");
    }

    public long getSpaceCount() {
        if (wordLimit%spaceAfter == 0) { //symmetric case
          return Math.round(wordLimit/spaceAfter) - 1;
        }
        return Math.round(Math.floor(wordLimit/spaceAfter));
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        setSelection(getEditableText().length());
    }

    private void removeLastIfSpace(Editable s) {
        if (s.toString().toCharArray()[s.length()-1] == ' ') {
            s.replace(s.length()-1, s.length(), "");
        }
    }
}
