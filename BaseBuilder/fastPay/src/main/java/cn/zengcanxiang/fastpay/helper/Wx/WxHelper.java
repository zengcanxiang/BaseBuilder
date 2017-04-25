package cn.zengcanxiang.fastpay.helper.Wx;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p>帮助客户端在没有服务器配合下快速完成支付参数的构建</p>
 * <p color='red'>本功能将加密过程在客户端实现,存在反编译、二次打包、debug密钥等风险。</p>
 * <p>请斟酌风险后使用，概不负责</p>
 */
public class WxHelper {

    private static String APP_ID = "";
    /**
     * 商户号
     */
    private static String MCH_ID = "";
    /**
     * 商户密钥
     */
    private static String WX_API_KEY = "";


    private static String WX_PACKAGE_VALUE = "Sign=WXPay";

    public static String buildPayParams(String prepayId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appid", APP_ID);
            jsonObject.put("partnerid", MCH_ID);
            jsonObject.put("prepayid", prepayId);
            jsonObject.put("package", WX_PACKAGE_VALUE);
            jsonObject.put("timestamp", getTimestamp());
            jsonObject.put("noncestr", RandomUtil.random(32));
            jsonObject.put("sign", SignUtil.sign(jsonObject, WX_API_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 生成微信下单参数
     */
    public static String buildWxPlaceOrderParams(OrderParams params) {
        return buildWxPlaceOrderParams(params.getOutTradeNo(), params.getBody(), params.getTotalMoney(),
                params.getTradeType(), params.getNotifyUrl(), params.getSpbillCreateIp(),
                params.getDeviceInfo(), params.getSignType(), params.getDetail(),
                params.getAttach(), params.getFeeType(), params.getTimeStart(),
                params.getTimeExpire(), params.getGoodsType(), params.getLimitPay());
    }

    /**
     * 生成微信下单参数
     *
     * @param outTradeNo     商户唯一订单号
     * @param body           品描述交易字段
     * @param totalMoney     需要支付的价格
     * @param tradeType      支付类型
     * @param notifyUrl      通知地址
     * @param spbillCreateIp 用户端实际ip
     */
    public static String buildWxPlaceOrderParams(String outTradeNo, String body, String totalMoney,
                                                 String tradeType, String notifyUrl, String spbillCreateIp) {
        return buildWxPlaceOrderParams(outTradeNo, body, totalMoney, tradeType, notifyUrl, spbillCreateIp,
                "", "", "", "", "", "", "", "", "");
    }

    /**
     * 生成微信下单参数
     *
     * @param outTradeNo     商户唯一订单号
     * @param body           品描述交易字段
     * @param totalMoney     需要支付的价格
     * @param tradeType      支付类型
     * @param notifyUrl      通知地址
     * @param spbillCreateIp 用户端实际ip
     * @param deviceInfo     终端设备号
     * @param signType       签名类型
     * @param detail         商品详情
     * @param attach         附加数据
     * @param feeType        货币类型
     * @param timeStart      交易起始时间
     * @param timeExpire     交易结束时间
     * @param goodsType      商品标记
     * @param limitPay       指定支付方式
     */
    public static String buildWxPlaceOrderParams(String outTradeNo, String body, String totalMoney,
                                                 String tradeType, String notifyUrl, String spbillCreateIp,
                                                 String deviceInfo, String signType, String detail,
                                                 String attach, String feeType, String timeStart,
                                                 String timeExpire, String goodsType, String limitPay) {
        if (TextUtils.isEmpty(APP_ID) || TextUtils.isEmpty(MCH_ID) ||
                TextUtils.isEmpty(outTradeNo) || TextUtils.isEmpty(body) ||
                TextUtils.isEmpty(totalMoney) || TextUtils.isEmpty(tradeType) ||
                TextUtils.isEmpty(notifyUrl) || TextUtils.isEmpty(spbillCreateIp)) {
            throw new IllegalArgumentException("微信下单必填参数不能为空");
        }
        JSONObject json = new JSONObject();
        try {
            //必填字段
            json.put("appid", APP_ID);
            json.put("mch_id", MCH_ID);
            json.put("out_trade_no", outTradeNo);
            json.put("body", body);
            json.put("total_fee", totalMoney);
            json.put("trade_type", tradeType);
            json.put("notify_url", notifyUrl);
            json.put("spbill_create_ip", spbillCreateIp);
            json.put("nonce_str", RandomUtil.random(32));
            //非必填字段
            if (!TextUtils.isEmpty(deviceInfo)) {
                json.put("device_info", deviceInfo);
            }
            if (!TextUtils.isEmpty(signType)) {
                json.put("sign_type", signType);
            }
            if (!TextUtils.isEmpty(detail)) {
                json.put("detail", detail);
            }
            if (!TextUtils.isEmpty(attach)) {
                json.put("attach", attach);
            }
            if (!TextUtils.isEmpty(feeType)) {
                json.put("fee_type", feeType);
            }
            if (!TextUtils.isEmpty(timeStart)) {
                json.put("time_start", timeStart);
            }
            if (!TextUtils.isEmpty(timeExpire)) {
                json.put("time_expire", timeExpire);
            }
            if (!TextUtils.isEmpty(goodsType)) {
                json.put("goods_tag", goodsType);
            }
            if (!TextUtils.isEmpty(limitPay)) {
                json.put("limit_pay", limitPay);
            }
            //签名
            json.put("sign", SignUtil.sign(json, WX_API_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    /**
     * 解析下单结果
     *
     * @return 下单成功返回的预支付交易会话ID
     */
    public static String analyzePlaceOrderResult() {

        return "";
    }

    public static void setWxApiKey(String wxApiKey) {
        WX_API_KEY = wxApiKey;
    }

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

    public static void setMchId(String mchId) {
        MCH_ID = mchId;
    }

    /**
     * 获取10位时间戳,自1970年1月1日 0点0分0秒以来的秒数
     */
    private static String getTimestamp() {
        return System.currentTimeMillis() / 1000 + "";
    }

}
