package cn.zengcanxiang.fastpay.helper.Wx;

import java.util.Random;

/**
 * 微信随机字符串生成util
 */
class RandomUtil {

    static String random(int length) {
        if (length > 32) {
            throw new IllegalArgumentException("微信支付随机字符串长度不能超过32位");
        }
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
