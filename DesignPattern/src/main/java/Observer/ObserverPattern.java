package Observer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sandeepin
 * 2018/9/2 0002
 */
public class ObserverPattern {
    public static void main(String[] args) {
        // 创建 主题、观察者
        ConcreteSubject subject = new ConcreteSubject();
        IObserver observer1 = new ConcreteObserver("司机A");
        IObserver observer2 = new ConcreteObserver("司机B");

        // 注册观察者
        subject.attach(observer1);
        subject.attach(observer2);

        // 更改价格，并通知观察者
        subject.setPrice(12.5);
        subject.notifyObserver();

        // 注销观察者2
        subject.detach(observer2);

        // 再次更改状态，并通知观察者
        subject.setPrice(15.0);
        subject.notifyObserver();
    }
}

// 抽象主题, 提供关于注册、注销、通知观察者的接口
interface ISubject {
    // 注册观察者
    void attach(IObserver observer);

    // 注销观察者
    void detach(IObserver observer);

    // 通知观察者
    void notifyObserver();
}

// 具体主题, 抽象主题的具体实现，用于管理所有的观察者
class ConcreteSubject implements ISubject {
    // 价格
    private double price;
    // 观察者列表
    private List<IObserver> observerList;

    ConcreteSubject() {
        this.price = 10.0;
        observerList = new LinkedList<IObserver>();
    }

    void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void attach(IObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (IObserver observer : observerList) {
            observer.update(price);
        }
    }
}

// 抽象观察者，提供更新价格接口
interface IObserver {
    void update(double price);
}

// 具体观察者，抽象观察者的具体实现，当接收到通知后，调整对应的价格
class ConcreteObserver implements IObserver {
    private String name;

    ConcreteObserver(String name) {
        this.name = name;
    }

    public void update(double price) {
        System.out.println("司机名：" + name + " 单价: " + price + " 元/每公里");
    }
}
