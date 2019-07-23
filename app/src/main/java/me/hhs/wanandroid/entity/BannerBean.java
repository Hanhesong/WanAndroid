package me.hhs.wanandroid.entity;

import java.util.List;

import me.hhs.wanandroid.entity.BaseBean;

/**
 * Created by KevinSong on 2019/7/23
 */
public class BannerBean extends BaseBean {

    /**
     * "data"[
     * "desc": "Android高级进阶直播课免费学习",
     * "id": 23,
     * "imagePath": "https://wanandroid.com/blogimgs/0b712568-6203-4a03-b475-ff55e68d89e8.jpeg",
     * "isVisible": 1,
     * "order": 0,
     * "title": "Android高级进阶直播课免费学习",
     * "type": 0,
     * "url": "https://url.163.com/4bj"]
     *
     * "errorCode": 0,
     * "errorMsg": ""
     */

    private List<BannerDataBean> data;
    private int errorCode;
    private String errorMsg;


    public class BannerDataBean{
        private String desc;
        private int id;
        private String imagePath;
        private int isVisible;
        private int order;
        private String title;
        private int type;
        private String url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public void setIsVisible(int isVisible) {
            this.isVisible = isVisible;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "BannerDataBean{" +
                    "desc='" + desc + '\'' +
                    ", id=" + id +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible=" + isVisible +
                    ", order=" + order +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
    public List<BannerDataBean> getData() {
        return data;
    }

    public void setData(List<BannerDataBean> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
