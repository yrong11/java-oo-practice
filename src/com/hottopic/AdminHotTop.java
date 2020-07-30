package com.hottopic;

import java.util.ArrayList;

public class AdminHotTop extends HotTopDAOImpl {
    public AdminHotTop(ArrayList<HotTopic> hotTopics) {
        super(hotTopics);
    }

    public void addSuperTopic(String name, int topicID){
        HotTopic topic;
        if (checkTopicExist(name)){
            topic = getTopicFromDescribe(name);
        }else{
            topic = new HotTopic(name, topicID);
            addTopic(topic);
        }
        topic.isSuper = true;
    }
}
