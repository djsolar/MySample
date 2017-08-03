package com.example.drawabletest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zhouyiran on 2017/7/25.
 */

public class MessageListItem extends RelativeLayout {

    private final int[] STATE_MESSAGE_READED = new int[] {R.attr.state_message_readed};

    private boolean mMessageReaded = false;

    private TextView messageSubject;

    public MessageListItem(Context context) {
        this(context, null);
    }

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadView();
    }

    public MessageListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadView();
    }

    public void setMessageReaded(boolean mMessageReaded) {
        if (this.mMessageReaded != mMessageReaded) {
            this.mMessageReaded = mMessageReaded;
            refreshDrawableState();
        }
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject.setText(messageSubject);
    }

    void loadView() {
        LayoutInflater.from(getContext()).inflate(R.layout.message_list_item, this, true);
        int fiveDPInPixels = convertDIPToPixels(5);
        int fiftyDPInPixels = convertDIPToPixels(50);

        setPadding(fiveDPInPixels, fiveDPInPixels, fiveDPInPixels, fiveDPInPixels);
        setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, fiftyDPInPixels));
        messageSubject = findViewById(R.id.message_subject);
        setBackgroundResource(R.drawable.message_list_item_background);
    }

    public int convertDIPToPixels(int dip) {
        // In production code this method would exist in a utility library.
        // e.g see my ScreenUtils class: https://gist.github.com/2504204
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if (mMessageReaded) {
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);
            return drawableState;
        } else {
            return super.onCreateDrawableState(extraSpace);
        }
    }
}
