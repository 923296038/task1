package com.jnshu;

public class Test01 {
    public static void main(String[] args) {
        try {
            Test test = new Test();
            test.getUserByID(2);
            test.getUserList("test1");
            test.addUser();
            test.updateUser();
            test.deleteUser(3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
