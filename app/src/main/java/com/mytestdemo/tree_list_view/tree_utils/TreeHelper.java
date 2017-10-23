package com.mytestdemo.tree_list_view.tree_utils;

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

    /**
     * 将 数据源，解析成 树状图所需要的类型
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> converDatas2Nodes(List<T> datas) throws IllegalAccessException {
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

                node = new Node(id, pId, label);
                nodes.add(node);

            }

        }

        return (List<T>) nodes;
    }

}
