package cn.zengcanxiang.fastimgload;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.zengcanxiang.baseBuilder.imgLoad.ImgLoadBuilder;

public class GlideBuilder extends ImgLoadBuilder {

    private float sizeMultiplier = 0.0f;

    private DiskCacheStrategy strategy = DiskCacheStrategy.SOURCE;

    private boolean isShowAnimation = true;

    private boolean isCenter = false;

    private List<Transformation> transformations = new ArrayList<>();

    @Override
    public GlideOptions builder() {
        return new GlideOptions(this);
    }

    public ImgLoadBuilder asGif(boolean gif) {
        isGif = gif;
        return this;
    }

    /**
     * 是否有显示动画 -- 淡入淡出
     */
    public GlideBuilder isShowAnimation(boolean showAnimation) {
        isShowAnimation = showAnimation;
        return this;
    }

    /**
     * 设置缩略图质量
     */
    public GlideBuilder sizeMultiplier(float sizeMultiplier) {
        this.sizeMultiplier = sizeMultiplier;
        return this;
    }

    /**
     * 设置缓存方式
     */
    public GlideBuilder strategy(DiskCacheStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    /**
     * 设置是否让图片充满整个ImageView的边框，并且裁掉超出的部分
     */
    public GlideBuilder isCenter(boolean isCenter) {
        this.isCenter = isCenter;
        return this;
    }

    public GlideBuilder transformation(Transformation transformation) {
        transformations.add(transformation);
        return this;
    }

    public GlideBuilder transformations(Transformation... transformations) {
        Transformation[] list =
                new Transformation[transformations.length];
        Collections.addAll(this.transformations, list);
        return this;
    }

    public GlideBuilder transformations(ArrayList<Transformation> list) {
        transformations.addAll(list);
        return this;
    }

    float getSizeMultiplier() {
        return sizeMultiplier;
    }

    DiskCacheStrategy getStrategy() {
        return strategy;
    }

    boolean isShowAnimation() {
        return isShowAnimation;
    }

    boolean getCenter() {
        return isCenter;
    }

    List<Transformation> getTransformations() {
        return transformations;
    }
}
