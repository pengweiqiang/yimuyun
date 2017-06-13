package com.yimuyun.lowraiseapp.widget;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;
import com.yimuyun.lowraiseapp.R;

/**
 * @author will on 2017/6/13 17:36
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity).load(path).placeholder(new ColorDrawable(activity.getResources().getColor(R.color.color_line_grey))).//加载中显示的图片
                error(new ColorDrawable(activity.getResources().getColor(R.color.color_line_grey)))//加载失败时显示的图片
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
