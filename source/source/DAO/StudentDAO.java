package dal;

import dal.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Course;

public class StudentDAO extends DBContext{
    public boolean addStudent(Student student) {
        PreparedStatement ps = null;
        boolean success = false;

        try {
            String sql = "INSERT INTO students (student_id, name, grade, birthday, address, notes) VALUES (?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setDouble(3, student.getGrade());
            ps.setDate(4, new java.sql.Date(student.getBirthday().getTime()));
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getNotes());
            int count = ps.executeUpdate();
            if (count > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        String SQL = "SELECT * FROM students";
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getInt("grade"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddress(rs.getString("address"));
                student.setNotes(rs.getString("notes"));
                students.add(student);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return students;
    }
    
    public List<Student> findByName(String name) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String studentName = rs.getString("name");
                int grade = rs.getInt("grade");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                String notes = rs.getString("notes");
                studentList.add(new Student(id, studentName, grade, birthday, address, notes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void deleteStudent(int studentId) throws SQLException {      
        PreparedStatement stmt = null;
        String sql = "DELETE FROM students WHERE student_id=?";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, studentId);
        stmt.executeUpdate();

    }
    
    public void editStudent(Student student) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE students SET name=?, grade=?, birthday=?, address=?, notes=? WHERE student_id=?");
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGrade());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ps.setDate(3, new java.sql.Date(student.getBirthday().getTime()));
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getNotes());
            ps.setInt(6, student.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM students WHERE student_id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int grade = rs.getInt("grade");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                String notes = rs.getString("notes");

                student = new Student(id, name, grade, birthday, address, notes);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return student;
    }

    public List<Student> getStudentsSortedByName() {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students ORDER BY name");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getInt("grade"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddress(rs.getString("address"));
                student.setNotes(rs.getString("notes"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
    public List<Student> getStudentsSortedByGrade() {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM students ORDER BY grade");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setGrade(rs.getInt("grade"));
                student.setBirthday(rs.getDate("birthday"));
                student.setAddress(rs.getString("address"));
                student.setNotes(rs.getString("notes"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }    
    
    public List<Course> getCourseListByYear(int studentId, int year) {
        List<Course> courseList = new ArrayList<>();
        try (
            PreparedStatement stmt = connection.prepareStatement("SELECT courses.* \n" +
                                                                "FROM courses, students, enrollments\n" +
                                                                "WHERE students.student_id = ?\n" +
                                                                "AND students.student_id = enrollments.student_id\n" +
                                                                "AND enrollments.class_id = courses.class_id\n" +
                                                                "AND year = ?")) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("class_id"));
                course.setName(rs.getString("name"));
                course.setLecture(rs.getString("lecture"));
                course.setYear(rs.getInt("year"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }
}
