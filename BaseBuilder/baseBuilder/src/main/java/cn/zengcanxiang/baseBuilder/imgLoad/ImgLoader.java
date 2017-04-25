package cn.zengcanxiang.baseBuilder.imgLoad;

import android.content.Context;
import android.widget.ImageView;


public interface ImgLoader<O extends ImgLoadOptions> {

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param img     加载图片的控件
     * @param options 加载图片配置
     */
    void load(Context context, ImageView img, final O options);

    /**
     * 获取缓存大小
     *
     * @return 缓存文件夹大小
     */
    long getCacheSize(Context context);

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache(Context context);

    /**
     * 清理内存缓存
     */
    void clearMemory(Context context);

}
