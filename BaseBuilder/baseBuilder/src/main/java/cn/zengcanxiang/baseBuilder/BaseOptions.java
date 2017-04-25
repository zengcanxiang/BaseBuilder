package cn.zengcanxiang.baseBuilder;

@SuppressWarnings("all")
public class BaseOptions<B extends BaseBuilder> {

    /**
     * 是否是debug模式
     */
    public boolean isDebug = false;

    protected BaseOptions(B builder) {
        this.isDebug=builder.isDebug();
    }

}
