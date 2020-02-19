package me.hhs.wanandroid.model.collect;

public class CollectModelFactory {
    public static ICollectModel model;

    public static ICollectModel getInstance() {
        if (model == null) {
            model = new CollectModelImpl();
        }
        return model;
    }


}
