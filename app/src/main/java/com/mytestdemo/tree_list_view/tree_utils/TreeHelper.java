package com.mytestdemo.tree_list_view.tree_utils;

import com.mytestdemo.R;
import com.mytestdemo.tree_list_view.Node;
import com.mytestdemo.tree_list_view.tree_annotation.TreeNodeId;
import com.mytestdemo.tree_list_view.tree_annotation.TreeNodeLabel;
import com.mytestdemo.tree_list_view.tree_annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishuxiang on 2017/10/22.
 */

public class TreeHelper {

    //默认展开的level
    private static int defaultExpandLevel;

    /**
     * 将 数据源，解析成 树状图所需要的类型
     *
     * @param <T>
     * @return
     */
    private static <T> List<T> converDatas2Nodes(List<T> datas) throws IllegalAccessException {
        //通过该方法，将用户提供的数据，转换为Node类型
        List<Node> nodes = new ArrayList<>();
        /**
         * 通过反射，得到想要的字段；或者设置 命名规范
         * 此处 通过反射  +  注解
         */
        for (T t : datas) {
            int id = -1;
            int pId = -1;
            String label = null;

            Node node = null;
            Class c = t.getClass();
            Field[] fields = c.getDeclaredFields();//获得自己声明的属性
            /**
             * 通过遍历，所有属性，得到node所需的
             */
            for (Field field : fields) {
                //自定义注解,通过判断是否有该注解，来判断是否是我们想要的字段
                //使用反射，来取值
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    //如该注解不为空，则说明存在该属性
                    field.setAccessible(true); //获得private
                    id=field.getInt(t);
                }

                if (field.getAnnotation(TreeNodePid.class) != null) {
                    //如该注解不为空，则说明存在该属性
                    field.setAccessible(true); //获得private
                    pId = field.getInt(t);
                }

                if (field.getAnnotation(TreeNodeLabel.class) != null) {
                    //如该注解不为空，则说明存在该属性
                    field.setAccessible(true); //获得private
                    label = (String) field.get(t);
                }
            }
            node = new Node(id, pId, label);
            nodes.add(node);
        }
        /**
         * 设置关联关系 节点关系
         * 分清楚 2 3 级 与上级之间的关系
         * 形成树
         */
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);

                if (m.getpId() == node.getId()) {
                    //如果 m 是 node 的子节点，就把他加到 node 的子队列中
                    node.getChildList().add(m);
                    m.setParent(node);

                } else if (m.getId() == node.getpId()) {
                    m.getChildList().add(node);
                    node.setParent(m);
                }
            }
        }

        /**
         * 为不同展开状态，设置图片
         */
        for (Node n : nodes) {
            setNodeIcon(n);
        }

        return (List<T>) nodes;
    }

    /**
     * 为不同展开状态，设置图片
     * 判断当前节点，是否有子节点，然后设置不同icon
     * @param n
     */
    private static void setNodeIcon(Node n) {
        if (n.getChildList().size() > 0 && n.isExpend()) {
            //有子，且展开
            n.setIcon(R.drawable.ic_arrow_down);
        } else if (n.getChildList().size() > 0 && !n.isExpend()) {
            //有子，且没有展开
            n.setIcon(R.drawable.ic_arrow_right);
        }else {
            // -1 则为没有icon
            n.setIcon(-1);
        }
    }

    /**
     * 得到排序后的 nodes
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> getSortNodes(List<T> datas) throws IllegalAccessException {
        List<Node> result = new ArrayList<>();

        List<Node> nodes = (List<Node>) converDatas2Nodes(datas);

        //获取树的根节点
        List<Node> rootNodes = getRootNodes(nodes);

        for (Node rootNode : rootNodes) {

            addNode(result, rootNode, defaultExpandLevel, 1);

        }
        return result;
    }

    /**
     * 把一个节点的所有的子节点 都放入 result
     * @param result
     * @param node
     * @param defaultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> result, Node node,
                                int defaultExpandLevel, int currentLevel) {

        result.add(node);

        if (defaultExpandLevel >= currentLevel) {
            node.setExpend(true);
        }

        if (node.isLeaf()) {
            //如果是子节点，直接return，否则就遍历
            return;
        }

        for (int i = 0; i < node.getChildList().size(); i++) {
            //递归调用
            addNode(result, node.getChildList().get(i),
                    defaultExpandLevel, currentLevel + 1);
        }
    }

    /**
     * 从所有节点中，获取树的根节点
     * @param nodes
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<>();
        for (Node n : nodes) {
            if (n.isRoot()) {
                root.add(n);
            }
        }
        return root;
    }

    /**
     * 过滤出可见的节点
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<>();

        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpend()) {
                setNodeIcon(node);
                result.add(node);
            }
        }

        return result;
    }
}
