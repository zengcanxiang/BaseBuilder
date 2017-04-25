package cn.zengcanxiang.baseBuilder.imgLoad;

import cn.zengcanxiang.baseBuilder.BaseOptions;

public class ImgLoadOptions<I extends ImgLoadBuilder> extends BaseOptions {

    /**
     * 错误图片
     */
    public int errorImg;
    /**
     * 占位图
     */
    public int placeholderImg;

    /**
     * 加载图片的url
     */
    public String loadImgUrl;

    /**
     * 加载res图片
     */
    public int loadImgRes;

    /**
     * 是否是加载Gif图
     */
    public boolean isGif = false;

    protected ImgLoadOptions(I builder) {
        super(builder);
        this.errorImg = builder.getErrorImg();
        this.placeholderImg = builder.getPlaceholderImg();
        this.loadImgUrl = builder.getLoadImgUrl();
        this.loadImgRes = builder.getLoadImgRes();
        this.isGif = builder.isGif();
    }

}
