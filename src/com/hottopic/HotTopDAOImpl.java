package com.hottopic;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HotTopDAOImpl implements HotTopDAO{

    ArrayList<HotTopic> hotTopics;
    int maxSize = 5;

    public HotTopDAOImpl(ArrayList<HotTopic> hotTopics){
        this.hotTopics = hotTopics;
    }

    /**
     * 查看热搜
     * @return
     */
    @Override
    public void showHotTopic() {
        if (hotTopics.size() == 0)
            System.out.println("热搜排行榜为空，欢迎添加热搜！");
        else{
            System.out.println("===========热搜排行榜===========");
            sort();
            hotTopics.stream().forEach(topic -> System.out.println(topic));
        }

    }

    /**
     * 添加热搜
     * @param topic
     */
    @Override
    public void addTopic(HotTopic topic) {
        if (hotTopics.size() >= maxSize){
            ArrayList<HotTopic> list = (ArrayList<HotTopic>) hotTopics.stream().filter(top -> top.isBuy==false).sorted().collect(Collectors.toList());
            HotTopic lastTop = list.get(list.size()-1);
            hotTopics.remove(lastTop);
        }
        if (checkTopicExist(topic.name)){
            System.out.println("该热搜已存在，请核实后重新添加！");
            return;
        }
        hotTopics.add(topic);
        System.out.println(topic.toString() + "添加成功！");
    }

    @Override
    public boolean checkTopicExist(HotTopic topic) {
        return hotTopics.contains(topic);
    }

    @Override
    public boolean checkTopicExist(String name) {
        return hotTopics.stream().filter(item -> item.name.equals(name)).findFirst().isPresent();
    }

    @Override
    public HotTopic getTopicFromDescribe(String name) {
        if (checkTopicExist(name))
            return hotTopics.stream().filter(item -> item.name.equals(name)).findFirst().get();
        else{
            System.out.println("热搜不存在，请查证后继续操作");
            return null;
        }

    }

    @Override
    public void deleteTopic() {
        if (hotTopics.size() > maxSize){

        }
    }

    public void sort(){
        ArrayList<HotTopic> noBuyTopics = (ArrayList<HotTopic>) hotTopics.stream()
                .filter(t -> t.isBuy==false)
                .sorted()
                .collect(Collectors.toList());
        Map<Integer, HotTopic> isBuyTopics = hotTopics.stream()
                .filter(t-> t.isBuy == true)
                .collect(Collectors.toMap(HotTopic::getPosition, t-> t));
        ArrayList<HotTopic> result = new ArrayList<>();
        int noBuy = 0;
        for (int i = 0; i < hotTopics.size(); i++){
            if(isBuyTopics.containsKey(i+1)){
                result.add(isBuyTopics.get(i+1));
            }else{
                result.add(noBuyTopics.get(noBuy));
                noBuy += 1;
            }
        }
        hotTopics.clear();
        hotTopics.addAll(result);
    }

}
