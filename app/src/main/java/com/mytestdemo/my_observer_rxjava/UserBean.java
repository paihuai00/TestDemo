package com.mytestdemo.my_observer_rxjava;

/**
 * Created by cuishuxiang on 2017/6/6.
 */

public class UserBean {


    public UserBean(String status, String msg, DataBean data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * status : 1
     * msg : 成功
     * data : {"id":"ysclass","name":"辅导员1"}
     */

    private String status;
    private String msg;
    public DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean(String id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * id : ysclass
         * name : 辅导员1
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
