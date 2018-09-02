适配器模式
定义：适配器模式将某个类的接口转换成客户端期望的另一个接口表示，主的目的是兼容性，让原本因接口不匹配不能一起工作的两个类可以协同工作。其别名为包装器(Wrapper)。属于结构型模式。
主要分为三类：类的适配器、对象的适配器、接口的适配器。

一句话描述适配器模式：
（我们有的）需要被适配的类、接口、对象——>适配器（Adapter）——>（我们想要的）输出

使用场景： 系统需要使用现有的类，而这些类的接口不符合系统的需要。 
场景举例:读卡器是作为内存卡和笔记本之间的适配器，您将内存卡插入读卡器，再将读卡器插入笔记本，这样就可以通过笔记本来读取内存卡。
代码举例：
出门旅行，宾馆只有220V插头，为手机充电需要5V电压，那么需要制作一个电源适配器。
类适配器模式
需要被适配的类：
// 我们有的220V电压
public class Voltage220 {
    public int output220V() {
        int src = 220;
        System.out.println("我是" + src + "V");
        return src;
    }
}

我们想要的输出:
// 需要的5V电压
public interface Voltage5 {
    int output5V();
}
适配器类:
// Adapter类：完成220V-5V的转变
public class VoltageAdapter extends Voltage220 implements Voltage5 {
    @Override
    public int output5V() {
        int src = output220V();
        System.out.println("适配器工作开始适配电压");
        int dst = src / 44;
        System.out.println("适配完成后输出电压：" + dst);
        return dst;
    }
}
Client类：
// Client类：手机, 需要5V电压
public class Mobile {
    /**
     * 充电方法
     * @param voltage5
     */
    public void charging(Voltage5 voltage5) {
        if (voltage5.output5V() == 5) {
            System.out.println("电压刚刚好5V，开始充电");
        } else if (voltage5.output5V() > 5) {
            System.out.println("电压超过5V，都闪开 我要变成note7了");
        }
    }
}
测试代码：
        System.out.println("===============类适配器==============");
        Mobile mobile = new Mobile();
        mobile.charging(new VoltageAdapter());

对象适配器模式
持有 需要被适配的类，实现输出类接口，完成适配。
// 对象适配器模式
public class VoltageAdapter2 implements Voltage5 {
    private Voltage220 mVoltage220;
    public VoltageAdapter2(Voltage220 voltage220) {
        mVoltage220 = voltage220;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != mVoltage220) {
            int src = mVoltage220.output220V();
            System.out.println("对象适配器工作，开始适配电压");
            dst = src / 44;
            System.out.println("适配完成后输出电压：" + dst);
        }
        return dst;
    }
}
      System.out.println("\n===============对象适配器==============");
        VoltageAdapter2 voltageAdapter2 = new VoltageAdapter2(new Voltage220());
        Mobile mobile2 = new Mobile();
        mobile2.charging(voltageAdapter2);

接口适配器模式
当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，并为该接口中每个方法提供一个默认实现（空方法），那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求，它适用于一个接口不想使用其所有的方法的情况。
应用场景：不想实现接口中的所有方法
待适配的接口：
// 动画绘制监听器
    public static interface AnimatorListener {
        void onAnimationStart(Animator animation);
        void onAnimationEnd(Animator animation);
        void onAnimationCancel(Animator animation);
        void onAnimationRepeat(Animator animation);
    }

常规的使用：
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        valueAnimator.start();
接口适配器：
public abstract class AnimatorListenerAdapter implements Animator.AnimatorListener {
    @Override
    public void onAnimationCancel(Animator animation) {}
    @Override
    public void onAnimationEnd(Animator animation) {}
    @Override
    public void onAnimationRepeat(Animator animation) {}
    @Override
    public void onAnimationStart(Animator animation) {}
}
优雅的使用：
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                  System.out.println("Fuck!");
            }
        });
        valueAnimator.start();

总结
类适配器，以类给到，在Adapter里，就是将src当做类，继承， 
对象适配器，以对象给到，在Adapter里，将src作为一个对象，持有。 
接口适配器，以接口给到，在Adapter里，将src作为一个接口，实现。
Adapter模式最大的作用还是将原本不兼容的接口融合在一起工作。 适配器不是在详细设计时添加的，而是解决正在服役的项目的问题。
优点： 1、可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。

缺点： 1、过多地使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是 A 接口，其实内部被适配成了 B 接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。 2.由于 JAVA 至多继承一个类，所以至多只能适配一个适配者类，而且目标类必须是抽象类。



观察者模式
定义：当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。
使用场景： 一个对象（目标对象）的状态发生改变，所有的依赖对象（观察者对象）都将得到通知，进行广播通知。
    有一种方法是：让接收者反复检查发送者来进行更新，但是这种方法存在两个主要问题：
      1 占用大量的 CPU 时间来检查新的状态
      2 依赖于检测更新的时间间隔，可能不会立即获得更新
   对于这个问题，有一个简单的解决方案 - 观察者模式。
一句话描述：
观察者订阅被观察者(主题)的状态，当被观察者(主题)状态改变的时候会通知所有订阅的观察者。
Subject（抽象主题）：跟踪所有观察者，并提供添加和删除观察者的接口。
Observer（抽象观察者）：为所有的具体观察者定义一个接口，在得到主题的通知时进行自我更新。
ConcreteSubject（具体主题）：将有关状态存入各 ConcreteObserver 对象。当具体主题的状态发生任何更改时，通知所有观察者。
ConcreteObserver（具体观察者）：实现 Observer 所要求的更新接口，以便使本身的状态与主题的状态相协调。

场景举例:拍卖的时候，拍卖师观察最高标价，然后通知给其他竞价者竞价。
代码举例：
出门旅行，要打车，选择了滴滴打车，滴滴对打车的价格根据市场的情况不断变化，通知给每位司机。
创建抽象主题
// 抽象主题  提供关于注册、注销、通知观察者的接口
class ISubject
{
public:
    virtual void Attach(IObserver *) = 0; // 注册观察者
    virtual void Detach(IObserver *) = 0; // 注销观察者
    virtual void Notify() = 0; // 通知观察者
};
创建具体主题
// 具体主题 抽象主题的具体实现，用于管理所有的观察者：
class ConcreteSubject : public ISubject
{
public:
    ConcreteSubject() { m_fPrice = 10.0; }

    void SetPrice(float price) {
        m_fPrice = price;
    }

    void Attach(IObserver *observer) {
        m_observers.push_back(observer);
    }

    void Detach(IObserver *observer) {
        m_observers.remove(observer);
    }

    void Notify() {
        list<IObserver *>::iterator it = m_observers.begin();
        while (it != m_observers.end()) {
            (*it)->Update(m_fPrice);
            ++it;
        }
    }

private:
    list<IObserver *> m_observers; // 观察者列表
    float m_fPrice; // 价格
};

创建抽象观察者
// 抽象观察者   提供一个 Update() 接口，用于更新价格
class IObserver
{
public:
    virtual void Update(float price) = 0; // 更新价格
};

创建具体观察者
// 具体观察者  抽象观察者的具体实现，当接收到通知后，调整对应的价格：
class ConcreteObserver : public IObserver
{
public:
    ConcreteObserver(string name) { m_strName = name; }

    void Update(float price) {
        cout << m_strName << " - price: " << price << "\n";
    }

private:
     string m_strName; // 名字
};

使用：
 // 创建主题、观察者
    ConcreteSubject *pSubject = new ConcreteSubject();
    IObserver *pObserver1 = new ConcreteObserver("Jack Ma");
    IObserver *pObserver2 = new ConcreteObserver("Pony");

    // 注册观察者
    pSubject->Attach(pObserver1);
    pSubject->Attach(pObserver2);

    // 更改价格，并通知观察者
    pSubject->SetPrice(12.5);
    pSubject->Notify();

    // 注销观察者
    pSubject->Detach(pObserver2);
    // 再次更改状态，并通知观察者
    pSubject->SetPrice(15.0);
    pSubject->Notify();

https://www.cnblogs.com/gongjian/p/6104766.html

Observer对象是观察者，Observable对象是被观察者。

[1]创建被观察者类，它继承自java.util.Observable类；
添加它的观察者：void addObserver(Observer o)
方法把观察者对象添加到观察者对象列表中：addObserver()
当被观察者中的事件发生变化时，执行
setChanged();
notifyObservers();
setChange()方法用来设置一个内部标志位注明数据发生了变化；notifyObservers()方法会去调用观察者对象列表中所有的Observer的update()方法，通知它们数据发生了变化。
只有在setChange()被调用后，notifyObservers()才会去调用update()。
[2]创建观察者类，它实现java.util.Observer接口；
对于观察者类，实现Observer接口的唯一方法update
void update(Observable o, Object arg)

形参Object arg，对应一个由notifyObservers(Object arg);传递来的参数，当执行的是notifyObservers();时，arg为null。

被观察者：ServerManager
import java.util.Observable;

public class ServerManager extends Observable {
     private int data = 0;    
     public int getData(){         return data;    }   
     public void setData(int i){ 
         if(this.data != i){ this.data = i;setChanged();}  
         notifyObservers();  //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。  } }
     }

}
观察者一：AObserver
import java.util.Observable;
import java.util.Observer;

public class AObserver implements Observer {
    
    public AObserver(ServerManager sm) {
        super();
        // TODO Auto-generated constructor stub
        sm.addObserver(this);　　//注册加入观察者
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("AObserver receive:Data has changed to "+((ServerManager) arg0).getData());

    }

}

观察者二：BObserver
import java.util.Observable;
import java.util.Observer;

public class BObserver implements Observer {
    
    public BObserver(ServerManager sm) {
        super();
        sm.addObserver(this);　　//注册加入观察者
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        System.out.println("BObserver receive:Data has changed to "+((ServerManager) o).getData());
    }

}


测试：
public class TestDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServerManager sm = new ServerManager();
        AObserver a    = new AObserver(sm);
        BObserver b = new BObserver(sm);
        sm.setData(5);
        sm.deleteObserver(a);　　//注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
        sm.setData(10);
    }

}

优点： 1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。

缺点： 1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。 3、观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。


策略模式
定义：在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
使用场景：
 1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。 
2、一个系统需要动态地在几种算法中选择一种。 
3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
一句话描述：使算法独立于使用算法的客户端。
Context（环境角色）：持有一个对 Strategy 的引用，最终给客户端调用。
Strategy（抽象策略）：定义了一个公共接口，让不同的算法以不同的方式来实现。通过这个接口，Context 可以调用不同的算法。
ConcreteStrategy（具体策略）：实现 Strategy 定义的接口，提供具体算法的实现。
场景举例:商场买东西的时候，卖场往往根据不同的客户制定不同的报价策略，比如针对新客户不打折扣，针对老客户打9折，针对VIP客户打8折...
代码举例：
出门旅行，很多种出行方式，自行车、公交车、自驾、地铁、火车、飞机。。。如何选择最合适的呢？
如果离家近，又怕堵车，可以骑自行车。
如果离家远，但想省钱，早点出发，可以乘公交车。
如果有车，并且不介意支付停车费，可以选择自驾。
如果没有车，但赶时间，可以乘出租车。
…
这里的每一种出行方式都是一种具体的策略。如何选择，需要基于成本、便利和时间之间的权衡。

创建抽象策略
// 出行策略
interface IStrategy
{
public:
    virtual void Travel() = 0;
};

创建具体策略

有三种具体的策略可供选择，骑自行车、开车、坐火车：

// 骑自行车
class BikeStrategy : public IStrategy
{
public:
    virtual void Travel() override { std::cout << "Travel by bike" << std::endl; }
};

// 开车
class CarStrategy : public IStrategy
{
public:
    virtual void Travel() override { std::cout << "Travel by car" << std::endl; }
};

// 坐火车
class TrainStrategy : public IStrategy
{
public:
    virtual void Travel() override { std::cout << "Travel by train" << std::endl; }
};

创建环境角色
// 对外提供了一个 Travel() 接口，最终由客户端调用。在内部，它最终调用的是 IStrategy 的相应方法
class Context
{
public:
    Context(IStrategy *strategy) { m_pStrategy = strategy; }
    void Travel() { m_pStrategy->Travel(); }

private:
    IStrategy *m_pStrategy;
};
测试：
// 策略之间可以相互替换
    IStrategy *bike = new BikeStrategy();
    IStrategy *car = new CarStrategy();
    IStrategy *train = new TrainStrategy();

    Context *bikeContext = new Context(bike);
    Context *carContext = new Context(car);
    Context *trainContext = new Context(train);

    bikeContext->Travel();
    carContext->Travel();
    trainContext->Travel();

      Context context = new Context(new OperationAdd());    
      System.out.println("10 + 5 = " + context.executeStrategy(10, 5));
 
      context = new Context(new OperationSubstract());      
      System.out.println("10 - 5 = " + context.executeStrategy(10, 5));
 
      context = new Context(new OperationMultiply());    
      System.out.println("10 * 5 = " + context.executeStrategy(10, 5));


优点： 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。

缺点： 1、策略类会增多。 2、所有策略类都需要对外暴露。

