package com.doubleclick.androidricheditor.chinalwb.are.styles.toolitems;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doubleclick.androidricheditor.R;
import com.doubleclick.androidricheditor.chinalwb.are.AREditText;
import com.doubleclick.androidricheditor.chinalwb.are.Util;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreImageSpan;
import com.doubleclick.androidricheditor.chinalwb.are.strategies.ImageStrategy;
import com.doubleclick.androidricheditor.chinalwb.are.styles.IARE_Style;
import com.doubleclick.androidricheditor.chinalwb.are.styles.toolitems.styles.ARE_Style_Image;

import java.io.File;

/**
 * Created by wliu on 13/08/2018.
 */

public class ARE_ToolItem_Image extends ARE_ToolItem_Abstract {

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        return null;
    }

    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            AREditText editText = this.getEditText();
            mStyle = new ARE_Style_Image(editText, (ImageView) mToolItemView);
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
            imageView.setImageResource(R.drawable.image);
            imageView.bringToFront();
            mToolItemView = imageView;
        }

        return mToolItemView;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        return;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (ARE_Style_Image.REQUEST_CODE == requestCode) {
                ARE_Style_Image imageStyle = (ARE_Style_Image) getStyle();
                Uri uri = data.getData();
                ImageStrategy imageStrategy = this.getEditText().getImageStrategy();
                if (imageStrategy != null) {
                    imageStrategy.uploadAndInsertImage(uri, imageStyle);
                    return;
                }
                imageStyle.insertImage(uri, AreImageSpan.ImageType.URI);
            }
        }
    }
}
