package com.hottopic;

import java.util.Objects;

public class HotTopic implements Comparable<HotTopic> {
    // 热搜名称； 热搜描述； 热搜票数热度；
    int topicID;
    String name;
    int voteNum = 0;
    boolean isBuy = false;
    boolean isSuper = false;
    int currentPrice = 0;

    public int getPosition() {
        return position;
    }

    int position = 0;

    public HotTopic(String name, int topicID){
        this.name = name;
        this.topicID = topicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotTopic)) return false;
        HotTopic hotTopic = (HotTopic) o;
        return name.equals(hotTopic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        String s = isSuper ? " (SuperHotTopic)" : "" ;
        return "热搜："+ name + "  热度：" + voteNum + s;
    }

    @Override
    public int compareTo(HotTopic o) {
        return o.voteNum - this.voteNum;
    }

    public void setBuyTop(int position, int currentPrice, boolean isBuy){
        this.position = position;
        this.isBuy = isBuy;
        this.currentPrice = currentPrice;
    }
}
