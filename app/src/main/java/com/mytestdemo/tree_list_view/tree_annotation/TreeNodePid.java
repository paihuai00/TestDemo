package com.mytestdemo.tree_list_view.tree_annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by cuishuxiang on 2017/10/22.
 * <p>
 * 树形图，注解  通常需要添加 target  retention 两个
 */
@Target(ElementType.FIELD) //该注解声明在属性上(也可以类上....)
@Retention(RetentionPolicy.RUNTIME)//该注解 什么时候可见，运行时可见
public @interface TreeNodePid {
}
