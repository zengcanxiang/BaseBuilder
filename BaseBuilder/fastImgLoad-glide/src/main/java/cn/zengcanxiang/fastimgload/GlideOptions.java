package cn.zengcanxiang.fastimgload;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cn.zengcanxiang.baseBuilder.imgLoad.ImgLoadOptions;

public class GlideOptions extends ImgLoadOptions<GlideBuilder> {

    boolean isCenter;

    float sizeMultiplier = 0.0f;

    boolean isShowAnimation = true;

    DiskCacheStrategy strategy;

    List<Transformation> transformations;

    GlideOptions(GlideBuilder builder) {
        super(builder);
        this.isCenter = builder.getCenter();
        this.sizeMultiplier = builder.getSizeMultiplier();
        this.isShowAnimation = builder.isShowAnimation();
        this.strategy = builder.getStrategy();
        this.transformations = builder.getTransformations();
    }

}
