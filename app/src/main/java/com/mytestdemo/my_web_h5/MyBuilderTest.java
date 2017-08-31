package com.mytestdemo.my_web_h5;

import android.content.Context;

/**
 * Created by cuishuxiang on 2017/7/8.
 */

public class MyBuilderTest {
    private String name;
    private String sex;
    private int age;
    private String school;

    private MyBuilderTest() {
        //私有构造方法
    }

    static class Builder {
        private Context context;
        private MyBuilderTest myBuilderTest = new MyBuilderTest();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setName(String name) {
            myBuilderTest.name = name;
            return this;
        }

        public Builder setSex(String sex) {
            myBuilderTest.sex = sex;
            return this;
        }

        public Builder setAge(int age) {
            myBuilderTest.age = age;
            return this;
        }

        public Builder setSchool(String school) {
            myBuilderTest.school = school;
            return this;
        }

        public MyBuilderTest create() {
            return myBuilderTest;
        }

    }

    @Override
    public String toString() {
        return "MyBuilderTest{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                '}';
    }
}
