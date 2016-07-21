package com.aebiz.b2b2c.cms.content.outerdata.impl;


import com.aebiz.b2b2c.cms.content.dao.ContentDAO;
import com.aebiz.b2b2c.cms.content.outerdata.OuterDataSource;
import com.aebiz.b2b2c.cms.content.utils.WriteFileUtils;
import com.aebiz.b2b2c.cms.content.vo.ContentModel;
import com.aebiz.b2b2c.cms.content.vo.ContentType;
import com.alibaba.druid.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sunchao on 15/12/23 and { 外部资源 医脉通接口实现 }
 */

@Service("com.aebiz.b2b2c.cms.content.outerData.YmtDataSource")
public class YmtDataSource implements OuterDataSource<ContentModel> {
    /**
     * 医脉通接口地址
     */
    private static String ymtUrl = "http://api.medlive.cn/project/hxq/get_list.php";

    @Autowired
    private ContentDAO contentDAO;

    //业内新闻
    private static String News = "news";
    //最新进展
    private static String Research = "research";

    public List<ContentModel> pullOuterData() {
        List<ContentModel> list = new ArrayList<>();
        list.addAll(pullNews());
        list.addAll(pullResearch());
        return list;
    }

    @Override
    public String getLastTimestamp() {
        String timestamp = WriteFileUtils.loadTimestamp();
        //加1去重
        if(StringUtils.isEmpty(timestamp)) return "";
        return Long.parseLong(timestamp) + 1 + "";
    }

    @Override
    public void saveLastTimestamp(String timestamp) {
        WriteFileUtils.writeTimestamp(timestamp);
    }

    /**
     * 获取业内新闻
     *
     * @return ContentModel list
     */
    public List<ContentModel> pullNews() {
        return pullData(News);
    }

    /**
     * 获取最新进展
     *
     * @return ContentModel list
     */
    public List<ContentModel> pullResearch() {
        return pullData(Research);
    }

    /**
     * 根据类型拉取数据
     *
     * @param key News  Research 两种类型
     * @return ContentModel list
     */
    public List<ContentModel> pullData(String key) {
        List<ContentModel> list = new ArrayList<>();
        String start = getLastTimestamp();
        String param = "";
        System.out.println("============start===========");
        if (StringUtils.isEmpty(start)) {
            param = "?date_end=" + System.currentTimeMillis() / 1000 + "&cat=" + key;
        } else {
            param = "?date_start=" + start + "$date_end=" + System.currentTimeMillis() / 1000 + "&cat=" + key;
        }
        
        System.out.println("============end===========");

        JSONObject back = com.aebiz.b2b2c.cms.content.utils.HttpUtils.httpGet(ymtUrl + param);
        String error = back.getString("err_msg");
        System.out.println("============error========="+error);
        if (StringUtils.isEmpty(error)) {
            JSONArray array = back.getJSONArray("data_list");
            Iterator<JSONObject> iter = array.iterator();
            
            System.out.println("============array.size()========="+array.size());

            //记录最新时间
            if (array.size() > 0) {
                JSONObject last = (JSONObject) array.get(0);
                saveLastTimestamp(last.getString("inputtime"));
            }
            
            System.out.println("============array.size()========="+array.size());

            while (iter.hasNext()) {
                try {
                    System.out.println("============iter开始============");

                    JSONObject obj = iter.next();
                    ContentModel model = new ContentModel();
                    model.setContentTitle(obj.getString("title"));
                    model.setImage(obj.getString("thumb"));
                    model.setUrl(obj.getString("url"));
                    long longTime = Long.parseLong(obj.getString("inputtime") + "000");
                    Date time = new Date(longTime);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    model.setCreateTime(sdf.format(time).toString());
                    model.setIntroduction("<p>" + obj.getString("description") + "</p>");
                    model.setContentNote(obj.getString("description"));
                    model.setState("0");
                    model.setContentCategoryUuid("D");
                    model.setAuthor(obj.getString("copyfrom"));
                    model.setProvenance(obj.getString("copyfrom"));
                    model.setContentType(ContentType.Ymt.getType());
                    list.add(model);
                } catch (Exception e) {
                    e.printStackTrace();
                    //TODO 返回结果 e.getMessage()
                }
            }
        } else {
            //TODO Ymt异常
            System.out.println("============Ymt异常=========");

        }
        return list;
    }
}
