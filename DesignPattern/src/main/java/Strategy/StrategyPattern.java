package Strategy;

/**
 * @author Sandeepin
 * 2018/9/2 0002
 */

// 创建抽象策略 出行策略
interface IStrategy {
    void Travel();
}

// 创建具体策略 有三种具体的策略可供选择，骑自行车、开车、坐火车
// 骑自行车
class BikeStrategy implements IStrategy {
    @Override
    public void Travel() {
        System.out.println("通过 Bike 旅行！");
    }
}

// 开车
class CarStrategy implements IStrategy {
    @Override
    public void Travel() {
        System.out.println("通过 Car 旅行！");
    }
}

// 坐火车
class TrainStrategy implements IStrategy {
    @Override
    public void Travel() {
        System.out.println("通过 Train 旅行！");
    }
}

// 创建环境角色，对外提供了一个 Travel() 接口，最终由客户端调用。在内部，它最终调用的是 IStrategy 的相应方法
class Context {
    private IStrategy strategy;

    Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    void travel() {
        strategy.Travel();
    }

}

public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context(new BikeStrategy());
        context.travel();
        context = new Context(new CarStrategy());
        context.travel();
        context = new Context(new TrainStrategy());
        context.travel();
    }
}
