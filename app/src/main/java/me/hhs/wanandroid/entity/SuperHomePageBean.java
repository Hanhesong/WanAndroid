package me.hhs.wanandroid.entity;

import java.util.List;

public class SuperHomePageBean extends BaseBean {

    private HomePageBean data;
    private int errCode;
    private String errMsg;

    public HomePageBean getData() {
        return data;
    }

    public void setData(HomePageBean data) {
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }




}
