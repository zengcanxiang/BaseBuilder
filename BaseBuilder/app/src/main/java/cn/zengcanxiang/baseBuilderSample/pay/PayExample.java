package cn.zengcanxiang.baseBuilderSample.pay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.orhanobut.logger.Logger;

import cn.zengcanxiang.fastpay.helper.Ali.AliHelper;
import cn.zengcanxiang.fastpay.helper.Ali.AliPayParams;
import cn.zengcanxiang.fastpay.helper.Wx.OrderParams;
import cn.zengcanxiang.fastpay.helper.Wx.WxHelper;
import cn.zengcanxiang.fastpay.option.AliOptions;
import cn.zengcanxiang.fastpay.pay.AliPay;


public class PayExample extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aliPay();
//        wxPay();
    }

    private void wxPay() {

        WxHelper.setAppId("wx82615da696c3e725");
        WxHelper.setMchId("efg");
        WxHelper.setWxApiKey("abc");

        //构建下单参数
        OrderParams orderParams = new OrderParams();
        orderParams.setOutTradeNo("20170407050311569296");
        orderParams.setBody("测试交易");
        orderParams.setTotalMoney("1");//分为单位
        orderParams.setTradeType("App");
        orderParams.setSpbillCreateIp("103.2.3.5");
        orderParams.setNotifyUrl("http://www.baidu.com");

        Logger.d(WxHelper.buildWxPlaceOrderParams(orderParams));

        //构建支付参数
        Logger.d(json2xml(WxHelper.buildPayParams("123456")));

//        wx82615da696c3e725
//        WXOptions wxOptions = new WXOptions.WXBuilder()
//                .setParams("")
//                .setContext(this)
//                .openDebug()
//                .builder();
//        WXPay wxPay = new WXPay(wxOptions);
//        wxPay.doPay(new WXPay.WXPayResultCallBack() {
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onError(int error_code) {
//
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }

    private String json2xml(String json) {

        return json;
    }

    private void aliPay() {
        AliHelper.setAppId("2016112103043475");
        AliHelper.setSellerId("2088521293793795");
        AliHelper.setRsaPrivate("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJTSBigLChiyUPRcaBLwplQRgnAnpXczPmFq+xi2hWQ2VJgxP9sOJ0qOoT2JLGble3v9aLZwgXEj52TYQSsk32Xw82vJhMkeUoPM/YFGASZZkK9Wn1M3NvZBbnwrybXZPN+o1oca3JAWlWKlvYtkg3Md3miItlfstVYtL0xPo/s3AgMBAAECgYBqSU2oT6oYVVJIE68ox8KjDBZSr54Xcfu9a8CFt+Ww2rS0NYhblNIvB4rU/iE+I+t7dFLS6aso+PGMLn7sp1r8xrcSxEo31MnG0Bhfj8SQ8+1wf2Elb6C971hrtNDwoWkTD59/ulOnLKw0SInOfGf5GCVOKurxdwImbfOnNv3EsQJBAMS3mq+vfx9PzSBVnL7Q1KE6Q5PYTD97ge1YjHuSSF3fHcUABwDuLl47VFibICOdEWuM5ZENWHOwl878RkMbxJUCQQDBq0OI7AIZ3g9YRIbbz3PV2/K61n18Kryk6Qmu20hSLCXB04G4n49HP5FgYt1VMFdkPwjHuGtlFxdMsmNlmeGbAkAKRw1t4F1j5WdyKQbncUGqqsCdGM/jATDolZfBXQMq5F1JzZaQFeGWSsM9Xd1v/uTKGbJkwa29XZyQ091X5DuBAkEApBpmw2KX9StAfV6zlxYV5STWbJLLCad86Y+9cLjGczqNU8vQo4wwcALhQHcFbOXlZ0AyRGl0Nth2Gbseljzp+QJBAKwZHF/5dV8N1gRxJ+MDMPKjorJU77mwelpAqAZ221kwTm4+4RqBgTgZYbl0Il9qV+79io3JpmyGkS3AuJL7sdA=");

        AliPayParams params = new AliPayParams();
        params.setOutTradeNo("20170407050311569296");
        params.setSubject("测试交易");
        params.setTotalMoney("0.01D");

        AliOptions aliOptions = new AliOptions.AliBuilder()
                .setParams(AliHelper.buildPayParams(params))
                .setContext(this)
                .openDebug()
                .builder();

        AliPay.AliPayResultCallBack callBack = new AliPay.AliPayResultCallBack() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "成功");
            }

            @Override
            public void onError(int error_code) {
            }

            @Override
            public void onDealing() {
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onFinish() {
            }
        };

        AliPay aliPay = new AliPay(aliOptions);
        aliPay.doPay(callBack);
    }
}
