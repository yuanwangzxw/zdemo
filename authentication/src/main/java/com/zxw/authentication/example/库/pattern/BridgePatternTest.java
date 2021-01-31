package com.zxw.authentication.example.库.pattern;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 桥接模式：通过不同组合，来实现笛卡尔积的效果,减少冗余
 * 如：微信与支付宝都有指纹，刷脸，密码支付，没有必要在每个支付方式里，冗余所有支付形式，
 * 而是分别实现不同的接口，通过不同的组合实现效果
 * 组合模式：域属性是集合，屏蔽了外部的单一操作与批量操作
 * 这里还是用了decorator，两个都是实现同一接口，感觉更易扩展
 * @author zxw
 * @date 2020-12-17 10:02
 **/
public class BridgePatternTest {
    public static void main(String[] args) {
        new WxPay()
                .setPayMode(new ZfbPay().setPayMode(new FacePayMode()))
                .transfer();
    }

}

@Data
@Accessors(chain = true)
abstract class Pay {

    protected Pay payMode;

    abstract void transfer();
}

class WxPay extends Pay {

    @Override
    void transfer() {
        System.out.print("微信：");
        payMode.transfer();
    }
}

class ZfbPay extends Pay {

    @Override
    void transfer() {
        System.out.print("支付宝：");
        payMode.transfer();
    }
}

class FacePayMode extends Pay {

    @Override
    void transfer() {
        System.out.println("人脸支付");
    }
}

class FingerPrintPayMode extends Pay {

    @Override
    void transfer() {
        System.out.println("指纹支付");
    }
}