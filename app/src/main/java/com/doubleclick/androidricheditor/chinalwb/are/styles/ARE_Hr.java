package com.doubleclick.androidricheditor.chinalwb.are.styles;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import com.doubleclick.androidricheditor.chinalwb.are.AREditText;
import com.doubleclick.androidricheditor.chinalwb.are.Constants;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreHrSpan;
import com.doubleclick.androidricheditor.chinalwb.are.styles.toolbar.ARE_Toolbar;


public class ARE_Hr extends ARE_ABS_FreeStyle {

	private AREditText mEditText;

	public ARE_Hr(ImageView imageView, ARE_Toolbar toolbar) {
		super(toolbar);
		setListenerForImageView(imageView);
	}

	/**
	 * @param editText
	 */
	public void setEditText(AREditText editText) {
		this.mEditText = editText;
	}

	@Override
	public void setListenerForImageView(ImageView imageView) {
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Editable editable = mEditText.getEditableText();
                int start = mEditText.getSelectionStart();
                int end = mEditText.getSelectionEnd();

                SpannableStringBuilder ssb = new SpannableStringBuilder();
                ssb.append(Constants.CHAR_NEW_LINE);
                ssb.append(Constants.CHAR_NEW_LINE);
                ssb.append(Constants.ZERO_WIDTH_SPACE_STR);
                ssb.append(Constants.CHAR_NEW_LINE);
                ssb.setSpan(new AreHrSpan(), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                editable.replace(start, end, ssb);
			}
		});
	}

	@Override
	public void applyStyle(Editable editable, int start, int end) {
        // Do nothing
	}


	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setChecked(boolean isChecked) {
		// TODO Auto-generated method stub

	}
}
