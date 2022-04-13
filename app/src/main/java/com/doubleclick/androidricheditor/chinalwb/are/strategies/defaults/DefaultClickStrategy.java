package com.doubleclick.androidricheditor.chinalwb.are.strategies.defaults;

import android.content.Context;
import android.content.Intent;
import android.text.style.URLSpan;

import com.doubleclick.androidricheditor.chinalwb.are.Util;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreAtSpan;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreImageSpan;
import com.doubleclick.androidricheditor.chinalwb.are.spans.AreVideoSpan;
import com.doubleclick.androidricheditor.chinalwb.are.strategies.AreClickStrategy;


public class DefaultClickStrategy implements AreClickStrategy {
    @Override
    public boolean onClickAt(Context context, AreAtSpan atSpan) {
        Intent intent = new Intent();
        intent.setClass(context, DefaultProfileActivity.class);
        intent.putExtra("userKey", atSpan.getUserKey());
        intent.putExtra("userName", atSpan.getUserName());
        context.startActivity(intent);
        return true;
    }

    @Override
    public boolean onClickImage(Context context, AreImageSpan imageSpan) {
        Intent intent = new Intent();
        AreImageSpan.ImageType imageType = imageSpan.getImageType();
        intent.putExtra("imageType", imageType);
        if (imageType == AreImageSpan.ImageType.URI) {
            intent.putExtra("uri", imageSpan.getUri());
        } else if (imageType == AreImageSpan.ImageType.URL) {
            intent.putExtra("url", imageSpan.getURL());
        } else {
            intent.putExtra("resId", imageSpan.getResId());
        }
        intent.setClass(context, DefaultImagePreviewActivity.class);
        context.startActivity(intent);
        return true;
    }

    @Override
    public boolean onClickVideo(Context context, AreVideoSpan videoSpan) {
        Util.toast(context, "Video span");
        return true;
    }

    @Override
    public boolean onClickUrl(Context context, URLSpan urlSpan) {
        // Use default behavior
        return false;
    }
}
