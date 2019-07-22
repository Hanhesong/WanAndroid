package me.hhs.wanandroid.entity;

import java.util.List;

public class HomePageBean extends BaseBean {
    /**
     * "curPage": 1,
     * "datas"
     * "offset": 0,
     * "over": false,
     * "pageCount": 337,
     * "size": 20,
     * "total": 6731
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int toatl;
    private List<ArticleBean> datas;

    @Override
    public String toString() {
        return "HomePageBean{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", toatl=" + toatl +
                ", datas=" + datas +
                '}';
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getToatl() {
        return toatl;
    }

    public void setToatl(int toatl) {
        this.toatl = toatl;
    }

    public List<ArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleBean> datas) {
        this.datas = datas;
    }
}
