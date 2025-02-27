package com.doubleclick.androidricheditor.chinalwb.are.styles.toolitems;

import android.content.Context;
import android.text.Editable;
import android.text.style.CharacterStyle;
import android.text.style.QuoteSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doubleclick.androidricheditor.R;
import com.doubleclick.androidricheditor.chinalwb.are.AREditText;
import com.doubleclick.androidricheditor.chinalwb.are.Constants;
import com.doubleclick.androidricheditor.chinalwb.are.Util;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreQuoteSpan;
import com.doubleclick.androidricheditor.chinalwb.are.styles.IARE_Style;
import com.doubleclick.androidricheditor.chinalwb.are.styles.toolitems.styles.ARE_Style_Quote;


public class ARE_ToolItem_Quote extends ARE_ToolItem_Abstract {

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        if (mToolItemUpdater == null) {
            mToolItemUpdater = new ARE_ToolItem_UpdaterDefault(this, Constants.CHECKED_COLOR, Constants.UNCHECKED_COLOR);
            setToolItemUpdater(mToolItemUpdater);
        }
        return mToolItemUpdater;
    }

    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            AREditText editText = this.getEditText();
            IARE_ToolItem_Updater toolItemUpdater = getToolItemUpdater();
            mStyle = new ARE_Style_Quote(editText, (ImageView) mToolItemView, toolItemUpdater);
        }
        return mStyle;
    }

    @Override
    public View getView(Context context) {
        if (null == context) {
            return mToolItemView;
        }
        if (mToolItemView == null) {
            ImageView imageView = new ImageView(context);
            int size = Util.getPixelByDp(context, 40);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.quote);
            imageView.bringToFront();
            mToolItemView = imageView;
        }

        return mToolItemView;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        boolean quoteExists = false;

        AREditText editText = this.getEditText();
        Editable editable = editText.getEditableText();
        printSpans(AreQuoteSpan.class);
        if (selStart > 0 && selStart == selEnd) {
            QuoteSpan[] quoteSpans = editable.getSpans(selStart - 1, selStart, QuoteSpan.class);
            if (quoteSpans != null && quoteSpans.length > 0) {
                quoteExists = true;
            }
        } else {
            QuoteSpan[] quoteSpans = editable.getSpans(selStart, selEnd, QuoteSpan.class);
            if (quoteSpans != null && quoteSpans.length > 0) {
                if (editable.getSpanStart(quoteSpans[0]) <= selStart
                        && editable.getSpanEnd(quoteSpans[0]) >= selEnd) {
                    quoteExists = true;
                }
            }
        }


        mToolItemUpdater.onCheckStatusUpdate(quoteExists);
    }
}
