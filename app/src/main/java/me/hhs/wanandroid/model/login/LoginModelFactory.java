package me.hhs.wanandroid.model.login;

public class LoginModelFactory {
    public static ILoginModel loginModel;

    public static ILoginModel getInstance() {
        if (loginModel == null) {
            loginModel = new LoginModelImpl();
        }
        return loginModel;
    }
}
