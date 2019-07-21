package com.jnshu.studentMapper;

import com.jnshu.student.Student;

import java.util.List;

public interface StudentMapper {

    public Student selectUserByID(int id);

    public List<Student> selectUsersByName(String userName);

    public void addUser(Student student);

    public void updateUser(Student student);

    public void deleteUser(int id);

}