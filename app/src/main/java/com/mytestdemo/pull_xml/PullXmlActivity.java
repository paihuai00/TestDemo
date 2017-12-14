package com.mytestdemo.pull_xml;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Xml;
import android.widget.Button;
import android.widget.TextView;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author cuishuxiang
 * @date 2017/11/9.
 */

public class PullXmlActivity extends BaseActivity {
    private static final String TAG = "PullXmlActivity";
    @BindView(R.id.start_pull)
    Button startPull;
    @BindView(R.id.show_xml)
    TextView showXml;

    StringBuilder stringBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_xml);
        ButterKnife.bind(this);

        stringBuilder = new StringBuilder();

        try {

            List<Person> personList = readXML(getResources().getAssets().open("Person.xml"));
            for (int i = 0; i < personList.size(); i++) {
                Person person = personList.get(i);
                stringBuilder.append("第-" + i + "-条记录：\n");
                stringBuilder.append("getName--" + person.getName() + "\n");
                stringBuilder.append("getAge--" + person.getAge() + "\n");
                stringBuilder.append("getId--" + person.getId() + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @OnClick(R.id.start_pull)
    public void onViewClicked() {
        showXml.setText(stringBuilder);
    }

    public static List<Person> readXML(InputStream inStream) {
        /**
         * 使用Pull 解析xml
         * 获取XmlPullParser 实例，可以通过下面的方式
         * 也可通过pull解析器工场得到实例方法==》
         * XmlPullParserFactory.newInstance().newPullParser();
         */
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inStream, "UTF-8");// 设置数据源编码
            int eventType = parser.getEventType();// 获取事件类型
            Person currentPerson = null;
            List<Person> persons = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
                        persons = new ArrayList<Person>();// 实例化集合类
                        break;
                    case XmlPullParser.START_TAG://开始读取某个标签
                        /**
                         * getName判断读到哪个标签
                         *
                         * nextText()获取文本节点值
                         *
                         * getAttributeValue(i)获取属性节点值
                         */
                        String name = parser.getName();
                        Log.d(TAG, "readXML: getName判断读到哪个标签:" + name);
                        if (name.equalsIgnoreCase("person")) {
                            currentPerson = new Person();
                            currentPerson.setId(new Integer(parser.getAttributeValue(null, "id")));
                        } else if (currentPerson != null) {
                            if (name.equalsIgnoreCase("name")) {
                                currentPerson.setName(parser.nextText());// 如果后面是Text元素,即返回它的值
                            } else if (name.equalsIgnoreCase("age")) {
                                currentPerson.setAge(new Short(parser.nextText()));
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:// 结束元素事件
                        //读完一个Person，可以将其添加到集合类中
                        if (parser.getName().equalsIgnoreCase("person") && currentPerson != null) {
                            persons.add(currentPerson);
                            currentPerson = null;
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            inStream.close();
            return persons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
