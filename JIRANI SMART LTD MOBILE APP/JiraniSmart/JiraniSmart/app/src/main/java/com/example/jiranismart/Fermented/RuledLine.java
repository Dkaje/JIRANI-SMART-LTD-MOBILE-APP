package com.example.jiranismart.Fermented;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class RuledLine extends AppCompatTextView {
    public RuledLine(Context context) {
        this(context, null);
    }

    public RuledLine(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        super.setText(content, type);
    }
}
