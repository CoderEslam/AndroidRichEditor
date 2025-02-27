package com.doubleclick.androidricheditor.chinalwb.are.styles;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.doubleclick.androidricheditor.chinalwb.are.AREditText;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreSubscriptSpan;


public class ARE_Subscript extends ARE_ABS_Style<AreSubscriptSpan> {

    private ImageView mSubscriptImage;

    private boolean mSubscriptChecked;

    private AREditText mEditText;

    /**
     * @param imageView image view
     */
    public ARE_Subscript(ImageView imageView) {
        super(imageView.getContext());
        this.mSubscriptImage = imageView;
        setListenerForImageView(this.mSubscriptImage);
    }

    /**
     * @param editText edit text
     */
    public void setEditText(AREditText editText) {
        this.mEditText = editText;
    }

    @Override
    public EditText getEditText() {
        return mEditText;
    }

    @Override
    public void setListenerForImageView(final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubscriptChecked = !mSubscriptChecked;
                ARE_Helper.updateCheckStatus(ARE_Subscript.this, mSubscriptChecked);
                if (null != mEditText) {
                    applyStyle(mEditText.getEditableText(),
                            mEditText.getSelectionStart(),
                            mEditText.getSelectionEnd());
                }
            }
        });
    }

    @Override
    public ImageView getImageView() {
        return mSubscriptImage;
    }

    @Override
    public void setChecked(boolean isChecked) {
        this.mSubscriptChecked = isChecked;
    }

    @Override
    public boolean getIsChecked() {
        return this.mSubscriptChecked;
    }

    @Override
    public AreSubscriptSpan newSpan() {
        return new AreSubscriptSpan();
    }
}
