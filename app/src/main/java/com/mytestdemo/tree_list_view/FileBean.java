package com.mytestdemo.tree_list_view;

import com.mytestdemo.tree_list_view.tree_annotation.TreeNodeId;
import com.mytestdemo.tree_list_view.tree_annotation.TreeNodeLabel;
import com.mytestdemo.tree_list_view.tree_annotation.TreeNodePid;

/**
 * Created by cuishuxiang on 2017/10/22.
 *
 * 该类是模拟，服务器返回的数据，通过给该bean  添加自定义的注解，
 * 获得node想要的字段
 *
 */

public class FileBean {

    @TreeNodeId
    private int id;

    @TreeNodePid
    private int pId; //父节点

    @TreeNodeLabel
    private String label;

    private String describe;

    public FileBean(int id, int pId, String label) {
        this.id = id;
        this.pId = pId;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
