package me.hhs.wanandroid.model;

/**
 * Created by KevinSong on 2019/7/26
 */
public interface IOnResponseListener<T> {
    void  onSuccess( T data );

    void  onFailure( String code ,String msg);

    void onError(Throwable throwable);
}
