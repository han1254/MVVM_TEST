package com.example.mvvm_test.bussiness.test.mvvm.model;

import com.example.mvvm_test.base.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class CalendarModel extends BaseModel {


    private String calendarName;
    private String calendarPicture;
    private int calendarId;
    private List<GoodBean> good;
    private List<BadBean> bad;
    private List<RecommendBean> recommend;
    private int order;

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getCalendarPicture() {
        return calendarPicture;
    }

    public void setCalendarPicture(String calendarPicture) {
        this.calendarPicture = calendarPicture;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public List<GoodBean> getGood() {
        return good;
    }

    public void setGood(List<GoodBean> good) {
        this.good = good;
    }

    public List<BadBean> getBad() {
        return bad;
    }

    public void setBad(List<BadBean> bad) {
        this.bad = bad;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class GoodBean implements Serializable {
        /**
         * title : 玩韩国欧巴
         * description : 隐身偷人
         */

        private String title;
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class BadBean implements Serializable{
        /**
         * title : 玩人质局
         * description : 打进攻队友选fuze
         */

        private String title;
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class RecommendBean implements Serializable{
        /**
         * name : …………
         * items : ["\u2026\u2026"]
         */

        private String name;
        private List<String> items;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }


    }

    public int getOrder(){
        return order;
    }

    public void setOrder(int order){
        this.order = order;
    }
}
