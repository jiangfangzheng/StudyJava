package Adapter;

/**
 * @author Sandeepin
 * 2018/9/2 0002
 */

// 对象适配器模式
class VoltageAdapter2 implements Voltage5 {
    private Voltage220 mVoltage220;
    VoltageAdapter2(Voltage220 voltage220) {
        mVoltage220 = voltage220;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != mVoltage220) {
            int src = mVoltage220.output220V();
            System.out.println("对象适配器工作，开始适配电压");
            dst = src / 44;
            System.out.println("适配完成后输出电压：" + dst + " V");
        }
        return dst;
    }
}

public class ObjectAdapter {
    public static void main(String[] args) {
        System.out.println("===============对象适配器==============");
        VoltageAdapter2 voltageAdapter2 = new VoltageAdapter2(new Voltage220());
        Mobile mobile2 = new Mobile();
        mobile2.charging(voltageAdapter2);
    }
}
