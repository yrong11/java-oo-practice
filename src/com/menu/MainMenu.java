package com.menu;

import com.hottopic.AdminHotTop;
import com.hottopic.HotTopic;
import com.hottopic.UserHotTop;
import com.person.Adminstor;
import com.person.Person;
import com.person.PersonDao;
import com.person.User;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    static int id = 1;
    static int uid = 1;
    static int aid = 1;
    static ArrayList<HotTopic> hotTopics = new ArrayList<>();
    static ArrayList<Person> userList = new ArrayList<>();
    static ArrayList<Person> adminList = new ArrayList<>();
    static Scanner console = new Scanner(System.in);
    static PersonDao userDao = new PersonDao(userList);
    static PersonDao adminDao = new PersonDao(adminList);
    static Person person = null;

    public static void startMenu(){

        int order = 0;

        AdminMenu adminMenu = new AdminMenu();
        UserMenu userMenu = new UserMenu();
        UserHotTop userOpe = new UserHotTop(hotTopics);
        AdminHotTop adminOpe = new AdminHotTop(hotTopics);


        while(order != 3){
            System.out.println("----------------------欢迎来到热搜排行榜系统！------------------------------");
            System.out.println("1:以普通用户身份登入热搜排行榜系统。");
            System.out.println("2:以管理员身份登入热搜排行榜系统。");
            System.out.println("3:退出。");
            System.out.print("请回复数字进行操作：");
            order = console.nextInt();

            switch (order){
                case 1:
                    if(userLogin(false)){
                        userMenu.startMenu(userOpe, (User) person);
                        break;
                    }break;
                case 2:
                    if(userLogin(true)){
                        adminMenu.startMenu(adminOpe, (Adminstor) person);
                        break;
                    }break;
                case 3:
                    System.out.println("您已成功退出热搜排行榜系统！");break;
                default:
                    System.out.println("输入错误，请重新输入！");
            }

        }
    }

    public static void loginShow(){
        System.out.println("=============请选择登录或者注册=============");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("3.退出");

    }

    public static boolean userLogin(boolean admin){
        loginShow();
        int order = 0;
        int login = 0;
        boolean flag = true;
        while(order != 3 && login == 0){
            System.out.print("请回复数字进行操作：");
            order = console.nextInt();
            switch (order){
                case 1:
                    if(checkPerson(admin)) login=1;break;
                case 2:
                    addPerson(admin);break;
                case 3:
                    System.out.println("退出登录！");
                    flag=false;
            }
        }
        return flag;
    }

    public static void addPerson(boolean admin){
        System.out.println("请输入用户名：");
        String name = console.next();
        System.out.println("请输入密码：");
        String pwd = console.next();
        if (admin){
            Adminstor a = new Adminstor(uid++, name, pwd);
            if(adminDao.addPerson(a))
                System.out.println("注册成功！");
            else
                System.out.println("注册失败！");

        }else{
            User u = new User(uid++, name, pwd);
            if(userDao.addPerson(u))
                System.out.println("注册成功！");
            else
                System.out.println("注册失败！");
        }

    }

    public static boolean checkPerson(boolean admin){
        System.out.println("请输入用户名：");
        String name = console.next();
        System.out.println("请输入密码：");
        String pwd = console.next();
        boolean flag = false;
        if (admin){
            if(adminDao.checkPerson(name,pwd)){
                System.out.println("登录成功！");
                person = adminDao.getPersonByName(name);
                flag = true;
            }
            else
                System.out.println("登录失败！");

        }else{
            if(userDao.checkPerson(name,pwd)) {
                System.out.println("登录成功！");
                person = userDao.getPersonByName(name);
                flag = true;
            }
            else
                System.out.println("登录失败！");
        }
        return flag;
    }
}
