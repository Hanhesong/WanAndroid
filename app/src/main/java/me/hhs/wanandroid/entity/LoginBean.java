package me.hhs.wanandroid.entity;

import java.util.List;

public class LoginBean extends BaseBean {
    /**
     * "data": {
     * "admin": false,
     * "chapterTops": [],
     * "collectIds": [8658],
     * "email": "",
     * "icon": "",
     * "id": 5787,
     * "nickname": "PromiseSong",
     * "password": "",
     * "publicName": "PromiseSong",
     * "token": "",
     * "type": 0,
     * "username": "PromiseSong"
     * },
     * "errorCode": 0,
     * "errorMsg": ""
     */
    private int errorCode;
    private String errorMsg;
    private LoginBeanData data;

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

    public LoginBeanData getData() {
        return data;
    }

    public void setData(LoginBeanData data) {
        this.data = data;
    }

    public class LoginBeanData {
        private boolean admin;
        private List<?> chapterTops;
        private List<Integer> collectIds;
        private String email;
        private String icon;
        private int id;
        private String nickname;
        private String password;
        private String publicName;
        private String token;
        private int type;
        private String username;

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<?> getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(List<?> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPublicName() {
            return publicName;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


}
