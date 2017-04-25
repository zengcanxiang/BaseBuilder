package cn.zengcanxiang.baseBuilder;

@SuppressWarnings("all")
public abstract class BaseBuilder {

    /**
     * 是否是debug模式
     */
    private boolean isDebug = false;

    public BaseBuilder() {
    }

    public abstract <O extends BaseOptions> O builder();

    /**
     * 打开debug模式
     */
    public BaseBuilder openDebug() {
        this.isDebug = true;
        return this;
    }

    /**
     * 关闭debug模式
     */
    public BaseBuilder closeDebug() {
        this.isDebug = false;
        return this;
    }

    public boolean isDebug() {
        return isDebug;
    }
}