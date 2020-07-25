package com.season.mvp.vm;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.season.mvp.model.ImageModel;

public class ImageBindingAdapter {
    @BindingAdapter({"app:imageUrl","app:placeholder"})
    public static void loadImageFromUrl(ImageView view, String url, Drawable drawable){
        ImageModel.bindImage2View(view, url, drawable);
    }
}
