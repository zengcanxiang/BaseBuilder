package cn.zengcanxiang.baseBuilder.imgLoad;

import cn.zengcanxiang.baseBuilder.BaseBuilder;
import cn.zengcanxiang.baseBuilder.BaseOptions;


public class ImgLoadBuilder extends BaseBuilder {
    /**
     * 错误图片
     */
    private int errorImg;
    /**
     * 占位图
     */
    private int placeholderImg;

    /**
     * 加载图片的url
     */
    private String loadImgUrl;

    /**
     * 加载res图片
     */
    private int loadImgRes;

    /**
     * 是否是加载Gif图
     */
    protected boolean isGif = false;

    @Override
    public <O extends BaseOptions> O builder() {
        return null;
    }

    public ImgLoadBuilder errorImg(int errorImg) {
        this.errorImg = errorImg;
        return this;
    }

    public ImgLoadBuilder placeholderImg(int placeholderImg) {
        this.placeholderImg = placeholderImg;
        return this;
    }

    public ImgLoadBuilder imgUrl(String loadImgUrl) {
        this.loadImgUrl = loadImgUrl;
        return this;
    }

    public ImgLoadBuilder imgRes(int loadImgRes) {
        this.loadImgRes = loadImgRes;
        return this;
    }


    public int getErrorImg() {
        return errorImg;
    }

    public int getPlaceholderImg() {
        return placeholderImg;
    }

    public String getLoadImgUrl() {
        return loadImgUrl;
    }

    public int getLoadImgRes() {
        return loadImgRes;
    }

    public boolean isGif() {
        return isGif;
    }

}
