package Adapter;

/**
 * @author Sandeepin
 * 2018/9/2 0002
 */

// 待适配的类：已经有的220V电压
class Voltage220 {
    int output220V() {
        int src = 220;
        System.out.println("我是 " + src + " V");
        return src;
    }
}

// 适配后的类：需要的5V电压
interface Voltage5 {
    int output5V();
}

// Adapter类：完成220V-5V的转变
class VoltageAdapter extends Voltage220 implements Voltage5 {

    @Override
    public int output5V() {
        int src = output220V();
        System.out.println("类适配器工作，开始适配电压");
        int dst = src / 44;
        System.out.println("适配完成后输出电压：" + dst + " V");
        return dst;
    }
}

// Client类：手机, 需要5V电压
class Mobile {

    void charging(Voltage5 voltage5) {
        if (voltage5.output5V() == 5) {
            System.out.println("电压刚刚好5V，开始充电！");
        } else if (voltage5.output5V() > 5) {
            System.out.println("电压超过5V，都闪开，我要变成Note7了！");
        }
    }
}

public class ClassAdapter {
    public static void main(String[] args) {
        System.out.println("===============类适配器==============");
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter());
    }
}

