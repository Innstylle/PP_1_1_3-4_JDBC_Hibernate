package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args){
        /*UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("Таблица пользователей создана!");

        User user1 = new User("Kirill", "Astakhov", (byte) 25);
        User user2 = new User("Oleg", "Kabanov", (byte) 30);
        User user3 = new User("Alina", "Demina", (byte) 17);
        User user4 = new User("Dima", "Katelevsky", (byte) 19);

        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        System.out.println("User с именем - " + user1.getName() + " добавлен в базу данных");
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        System.out.println("User с именем - " + user2.getName() + " добавлен в базу данных");
        userService.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        System.out.println("User с именем - " + user3.getName() + " добавлен в базу данных");
        userService.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        System.out.println("User с именем - " + user4.getName() + " добавлен в базу данных");

        List<User> userList = userService.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }

        userService.cleanUsersTable();
        System.out.println("Таблица очищена");
        userService.dropUsersTable();
        System.out.println("Таблица удалена");

        Util.close();*/

        User user1 = new User("Kirill", "Astakhov", (byte) 25);
        User user2 = new User("Oleg", "Kabanov", (byte) 30);
        User user3 = new User("Alina", "Demina", (byte) 17);
        User user4 = new User("Dima", "Katelevsky", (byte) 19);

        try (SessionFactory sessionFactory = Util.getSessionFactory();
                Session session = sessionFactory.openSession()){
            session.beginTransaction();

            session.save(user1);
            session.save(user3);
            session.getTransaction().commit();
        }

    }
}
