package com.devilsoftware.fgallery;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jsibbold.zoomage.ZoomageView;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_detail);


        ZoomageView mImageView = findViewById(R.id.image);
        String url = getIntent().getStringExtra("url");

        Glide.with(this)
                .load(url)
                .asBitmap()
                .error(android.R.drawable.spinner_background)
                .placeholder(getProgressBarIndeterminate())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);

        mImageView.setAnimateOnReset(true);

    }

    Drawable getProgressBarIndeterminate() {
        final int[] attrs = {android.R.attr.indeterminateDrawable};
        final int attrs_indeterminateDrawable_index = 0;
        TypedArray a = this.obtainStyledAttributes(android.R.style.Widget_ProgressBar, attrs);
        try {
            return a.getDrawable(attrs_indeterminateDrawable_index);
        } finally {
            a.recycle();
        }
    }
}
