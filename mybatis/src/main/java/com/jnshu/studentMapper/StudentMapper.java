package com.jnshu.studentMapper;

import com.jnshu.student.Student;

import java.util.List;

public interface StudentMapper {

    public Student selectUserByID(int id);

    public Student selectUsersByName(String name);
    public Student selectUsersById(int id);

    public void addUser(Student student);

    public void updateUser(Student student);

    public void deleteUser(int id);

}