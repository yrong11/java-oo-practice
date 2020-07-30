package com.menu;

import com.hottopic.AdminHotTop;
import com.hottopic.HotTopic;
import com.person.Adminstor;

import java.util.Scanner;

public class AdminMenu {

    Scanner console = new Scanner(System.in);
    public void startMenu(AdminHotTop admin, Adminstor adminstor) {

        showMenu();
        int order = 0;
        while (order != 4) {
            System.out.print("请回复数字进行操作(如需查看管理员菜单请回复5)：");
            order = console.nextInt();
            switch (order) {
                case 1: showHotTop(admin);break;
                case 2: addTopic(admin);break;
                case 3: addSuper(admin);break;
                case 5: showMenu();break;
                case 4: System.out.println("您已退出管理员热搜排行榜菜单！");break;
                default: System.out.println("输入错误，请重新输入！");
            }
        }
    }

    public void showMenu(){
        System.out.println("--------------------管理员您好，欢迎来到热搜排行榜系统!----------------------");
        System.out.println("1:查看热搜排行榜。");
        System.out.println("2:添加热搜。");
        System.out.println("3:添加超级热搜。");
        System.out.println("4:退出！");
    }


    /**
     * 查看热搜排行榜
     * @param admin
     */
    public void showHotTop(AdminHotTop admin){
        admin.showHotTopic();
    }

    /**
     * 添加热搜
     * @param admin
     */
    public void addTopic(AdminHotTop admin){
        System.out.println("请输入需要添加的热搜名称：");
        String name = console.next();
        if(admin.checkTopicExist(name))
            System.out.println("该热搜已存在，请核实后重新添加！");
        else{
            HotTopic topic = new HotTopic(name, MainMenu.id++);
            admin.addTopic(topic);
        }


    }

    /**
     * 添加超级热搜
     * @param admin
     */
    public void addSuper(AdminHotTop admin){
        System.out.println("请输入需要添加的热搜名称：");
        String name = console.next();
        admin.addSuperTopic(name, MainMenu.id++);
        System.out.println("恭喜您，已成功添加热搜！");
    }
}
