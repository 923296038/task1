import com.jnshu.student.Student;
import com.jnshu.studentMapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

public class TestMybatis {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;
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

    @Test
    public void addUser() {
        Student student = new Student();
        student.setWish("place");
        student.setName("test_add");
        student.setOnline_id(303);
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
    @Test
    public void updateUser() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session
                    .getMapper(StudentMapper.class);
            Student student = userOperation.selectUserByID(29);
            if (student != null) {
                student.setWish("A new place");
                userOperation.updateUser(student);
                session.commit();
            }
        } finally {
            session.close();
        }
    }
    @Test
    public void getUserByID() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session.getMapper(StudentMapper.class);
            //session.getMapper()的作用.已知Mapper为映射器.
            Student student = userOperation.selectUserByID(30);
            if (student != null) {
                System.out.println(student.getId() + ":" + student.getName()
                        + ":" + student.getMajor());
            }
        } finally {
            session.close();
        }
    }
    @Test
    public void getUserByName(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session.getMapper(StudentMapper.class);
            //session.getMapper()的作用.已知Mapper为映射器.
            Student student = userOperation.selectUsersByName("test_add");
            if (student != null) {
                System.out.println(student.getId() + ":" + student.getName()
                        + ":" + student.getMajor());
            }
        } finally {
            session.close();
        }
    }
    @Test
    public void deleteUser() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            StudentMapper userOperation = session
                    .getMapper(StudentMapper.class);
            userOperation.deleteUser(29);
            session.commit();
        } finally {
            session.close();
        }
    }
}
