package me.hhs.wanandroid.model.banner;

public class BannerModelFactory {
    public static IBannerModel bannerModel;

    public static IBannerModel getInstance(){
        if (bannerModel==null){
            bannerModel = new BannerModelImpl();
        }
        return bannerModel;
    }

}
