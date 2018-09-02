package Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Sandeepin
 * 2018/9/2 0002
 */
public class JavaObserver {
    public static void main(String[] args) {
        ServerManager sm = new ServerManager();
        AObserver a = new AObserver(sm);
        BObserver b = new BObserver(sm);
        sm.setData(5);

        //注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
        sm.deleteObserver(a);
        sm.setData(10);
    }
}

class ServerManager extends Observable {
    private int data = 0;

    int getData() {
        return data;
    }

    void setData(int i) {
        if (this.data != i) {
            this.data = i;
            setChanged();
        }
        // 只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干
        notifyObservers();
    }

}

// 观察者一：AObserver
class AObserver implements Observer {

    AObserver(ServerManager sm) {
        super();
        sm.addObserver(this);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("a 收到数据Data改变为：" + ((ServerManager) arg0).getData());

    }

}

// 观察者二：BObserver
class BObserver implements Observer {

    BObserver(ServerManager sm) {
        super();
        sm.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("b 收到数据Data改变为：" + ((ServerManager) o).getData());
    }
}