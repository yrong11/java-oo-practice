package com.menu;
import com.hottopic.HotTopic;
import com.hottopic.UserHotTop;
import com.person.Person;
import com.person.User;

import java.util.Scanner;

public class UserMenu {
    Scanner console = new Scanner(System.in);

    public void startMenu(UserHotTop userOpe, User user){
        showMenu();
        int order = 0;
        while (order != 5){
            System.out.print("请回复数字进行操作，(如需查看菜单请回复6)：");
            order = console.nextInt();
            switch (order){
                case 1:  showHotTop(userOpe);break;
                case 2: addTopic(userOpe);break;
                case 3: voteTopic(userOpe,user);break;
                case 4: buy(userOpe);break;
                case 6: showMenu();break;
                case 5:
                    System.out.println("您已退出用户热搜排行榜菜单！");
                    break;
                default:
                    System.out.println("输入错误，请重新输入！");
            }
        }

    }

    public void showMenu(){
        System.out.println("--------------------用户您好，欢迎来到热搜排行榜系统！----------------------");
        System.out.println("1:查看热搜排行榜。");
        System.out.println("2:添加热搜。");
        System.out.println("3:给热搜事件投票。");
        System.out.println("4:购买热搜。");
        System.out.println("5:退出！");
    }

    public void showHotTop(UserHotTop userOpe){
        userOpe.showHotTopic();
    }

    public void addTopic(UserHotTop userOpe){
        System.out.println("请输入需要添加的热搜名称：");
        String name = console.next();
        if(userOpe.checkTopicExist(name))
            System.out.println("该热搜已存在，请核实后重新添加！");
        else{
            HotTopic topic = new HotTopic(name, MainMenu.id++);
            userOpe.addTopic(topic);
        }


    }

    /**
     * 给热搜投票
     * @param userOpe
     * @param user
     */
    public void voteTopic(UserHotTop userOpe, User user){
        System.out.println("请输入需要添加的热搜名称：");
        String name = console.next();
        if(!userOpe.checkTopicExist(name)){
            System.out.println("投票失败！热搜不存在。");
            return;
        }
        System.out.println("请输入投票的数量：");
        int voteNum = console.nextInt();
        if (user.canVote(voteNum)){
            user.vote -= voteNum;
            userOpe.voteTopic(name, voteNum);
            System.out.println("恭喜您，投票成功！您的票数剩余" + user.vote + "张。");
        }else{
            System.out.println("投票失败，您的票数剩余" + user.vote + "张。");
        }

    }

    /**
     * 买热搜
     * @param userOpe
     */
    public void buy(UserHotTop userOpe){
        System.out.println("请输入所需购买的热搜名称：");
        String name = console.next();
        System.out.println("请输入购买热搜的位置：");
        int position = console.nextInt();
        System.out.println("请输入购买热搜金额：");
        int price = console.nextInt();
        int flag = userOpe.buyTopic(MainMenu.id++, name, position, price);
        switch (flag){
            case 1:
                System.out.println("购买热搜成功！");
                break;
            case 0:
                System.out.println("购买热搜位置范围错误，请查证后重新购买！");
                break;
            case -1:
                System.out.println("购买热搜金额不足！");
        }
    }
}


