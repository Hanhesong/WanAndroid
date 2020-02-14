package me.hhs.wanandroid.model.register;

public class RegisterModelFactory {
    public static IRegisterModel model;

    public static IRegisterModel getInstance() {
        if (model == null) {
            model = new RegisterModelImpl();
        }
        return model;
    }
}
