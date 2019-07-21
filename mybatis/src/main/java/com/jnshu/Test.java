package com.jnshu;

import java.io.Reader;
import java.util.List;

import com.jnshu.student.Student;
import com.jnshu.studentMapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
    //reader有什么作用

    static {
        try {
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public void getUserByID(int userID) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session.getMapper(StudentMapper.class);
            //session.getMapper()的作用.已知Mapper为映射器.
            Student student = userOperation.selectUserByID(userID);
            if (student != null) {
                System.out.println(student.getId() + ":" + student.getName()
                        + ":" + student.getMajor());
            }
        } finally {
            session.close();
        }
    }

    public void getUserList(String userName) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session
                    .getMapper(StudentMapper.class);
            List<Student> students = userOperation.selectUsersByName(userName);
            for (Student student : students) {
                System.out.println(student.getId() + ":" + student.getName()
                        + ":" + student.getMajor());
            }
        } finally {
            session.close();
        }
    }

    /**
     * 增加后要commit
     */
    public void addUser() {
        Student student = new Student();
        student.setWish("place");
        student.setName("test_add");
        student.setOnline_id(30);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session
                    .getMapper(StudentMapper.class);
            userOperation.addUser(student);
            session.commit();
            System.out.println("新增用户ID：" + student.getId());
        } finally {
            session.close();
        }
    }

    /**
     * 修改后要commit
     */
    public void updateUser() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session
                    .getMapper(StudentMapper.class);
            Student student = userOperation.selectUserByID(1);
            if (student != null) {
                student.setWish("A new place");
                userOperation.updateUser(student);
                session.commit();
            }
        } finally {
            session.close();
        }
    }

    /**
     * 删除后要commit.
     *
     * @param id
     */
    public void deleteUser(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session
                    .getMapper(StudentMapper.class);
            userOperation.deleteUser(id);
            session.commit();
        } finally {
            session.close();
        }
    }



}