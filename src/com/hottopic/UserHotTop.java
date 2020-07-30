package com.hottopic;


import java.util.ArrayList;
import java.util.Optional;

public class UserHotTop extends HotTopDAOImpl{
    public UserHotTop(ArrayList<HotTopic> hotTopics) {
        super(hotTopics);
    }

    public void voteTopic(String topicName, int voteNums){
        HotTopic topic = getTopicFromDescribe(topicName);
        if (topic == null){
            return;
        }
        topic.voteNum += topic.isSuper? voteNums*2 : voteNums;
        System.out.println("投票成功，目前" + topic.toString());

    }

    public int  buyTopic(int topicID, String name, int position, int price){
        int flag = 1;
        //购买热搜位置超出范围
        if (position < 1 || position > hotTopics.size()+1){
            flag = 0;
            return flag;
        }

        Optional<HotTopic> nowPositionTopic = hotTopics.stream().filter(t -> t.position==position).findFirst();
        if (nowPositionTopic.isPresent() && nowPositionTopic.get().currentPrice> price){
            //购买热搜价格不足
            flag = -1;
        }else{
            if (nowPositionTopic.isPresent())
                hotTopics.remove(nowPositionTopic.get());
            if (checkTopicExist(name)){
                getTopicFromDescribe(name).setBuyTop(position, price, true);
            }else{
                HotTopic top = new HotTopic(name, topicID);
                top.setBuyTop(position,price,true);
                addTopic(top);
            }
        }
        return flag;
    }

}
