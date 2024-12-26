package Seri.ProxyDemo;

public class UserProxy implements IUser {
    private IUser target;
    public UserProxy(IUser target)
    {
        this.target=target;
    }

    @Override
    public void show() {

    }
//    @Override
//    public void show() {
//        target.show();
//        System.out.println("show proxy");
//    }

//    @Override
//    public void update() {
//        target.update();
//        System.out.println("update proxy");
//    }
}
