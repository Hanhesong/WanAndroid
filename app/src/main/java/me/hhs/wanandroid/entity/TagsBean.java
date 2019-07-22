package me.hhs.wanandroid.entity;

public class TagsBean extends BaseBean {
    /**
     *              "name": "问答",
     *              "url": "/article/list/0?cid=440"
     */
     private String name;
     private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
