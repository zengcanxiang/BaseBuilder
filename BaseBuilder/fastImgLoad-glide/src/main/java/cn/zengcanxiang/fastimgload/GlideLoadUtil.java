package cn.zengcanxiang.fastimgload;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;

import java.util.List;

import cn.zengcanxiang.baseBuilder.imgLoad.ImgLoader;

public class GlideLoadUtil implements ImgLoader<GlideOptions> {

    private final static String TAG = "GlideLoadUtil";

    @Override
    public void load(Context context, ImageView imageView, GlideOptions options) {
        RequestManager with;
        debugLog(options, "Glide debug Log：");
        if (context instanceof Activity) {
            with = Glide.with((Activity) context);
            debugLog(options, "和生命周期相关联");
        } else {
            with = Glide.with(context);
            debugLog(options, "不会和生命周期相关联");
        }

        DrawableTypeRequest load;
        if (TextUtils.isEmpty(options.loadImgUrl)) {
            debugLog(options, "加载的resId:" + options.loadImgRes);
            load = with.load(options.loadImgRes);
        } else {
            debugLog(options, "加载的url:" + options.loadImgUrl);
            //加载url图片
            load = with.load(options.loadImgUrl);
        }

        //常见设置
        load.error(options.errorImg)
                .placeholder(options.placeholderImg)
                .diskCacheStrategy(options.strategy);

        //是否加载使用动画
        if (options.isShowAnimation) {
            debugLog(options, "使用淡入淡出加载动画");
            load.crossFade();
        } else {
            debugLog(options, "不使用加载动画");
            load.dontAnimate();
        }

        //是否让图片充满整个ImageView的边框，然后裁掉超出的部分
        if (options.isCenter) {
            load.centerCrop();
        } else {
            load.fitCenter();
        }

        //加载变换图片操作
        List<Transformation> temp = options.transformations;
        if (temp != null && !temp.isEmpty()) {
            debugLog(options, "使用Transformation个数：" + temp.size());
            for (Transformation t : temp) {
                load.bitmapTransform(t);
            }
        }

        GenericRequestBuilder builder;

        if (options.isGif) {
            debugLog(options, "该图片为GIF");
            builder = load.asGif();
        } else {
            debugLog(options, "该图片为普通图片");
            builder = load.asBitmap();
        }

        if (options.sizeMultiplier > 0.0f) {
            debugLog(options, "该缩略图质量:" + options.sizeMultiplier);
            builder.thumbnail(options.sizeMultiplier);
        }

        builder.into(imageView);
    }

    @Override
    public long getCacheSize(Context context) {
        return Glide.getPhotoCacheDir(context).length();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    private void debugLog(GlideOptions options, String log) {
        if (options.isDebug) {
            Log.d(TAG, log);
        }
    }
}
