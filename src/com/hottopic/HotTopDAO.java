package com.hottopic;

public interface HotTopDAO {
    void showHotTopic();
    void addTopic(HotTopic topic);
    boolean checkTopicExist(HotTopic topic);
    boolean checkTopicExist(String name);
    HotTopic getTopicFromDescribe(String name);
    void deleteTopic();
}
