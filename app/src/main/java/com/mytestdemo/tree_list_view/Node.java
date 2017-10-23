package com.mytestdemo.tree_list_view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/22.
 *
 * 节点  所需要的类型
 */

public class Node {
    private int id;

    /**
     * 根节点 pId = 0
     */
    private int pId;

    private Node parent;

    private List<Node> childList = new ArrayList<>();

    private String name;
    /**
     * 树的层级
     */
    private int level;

    /**
     * 是否展开 默认 false
     */
    private boolean isExpend = false;

    private int icon;

    public Node() {

    }

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    /**
     * 判断是否是根节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 判断当前父节点，收缩状态
     * @return
     */
    public boolean isParentExpend() {
        if (parent == null) {
            //没父节点，则不为打开状态
            return false;
        }

        return parent.isExpend();
    }

    /**
     * 判断是否是  叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return childList.size() == 0;
    }

    /**
     *
     * @return
     */

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

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildList() {
        return childList;
    }

    public void setChildList(List<Node> childList) {
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 得到当前节点的层级
     * @return
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        isExpend = expend;
        //需要考虑，父节点回收的时候，子节点是否回收
        //如果传入的是false，希望收缩节点，则将下面的节点 都收缩
        if (!isExpend) {
            //使用递归，将当前节点下的子节点，都置为false
            for (Node node : childList) {
                node.setExpend(false);
            }
        }
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
