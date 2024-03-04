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

public class CourseDAO extends DBContext{
    public boolean addCourse(Course course) {
        PreparedStatement ps = null;
        boolean success = false;

        try {
            String sql = "INSERT INTO courses (class_id, name, lecture, year, notes) VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, course.getId());
            ps.setString(2, course.getName());
            ps.setString(3, course.getLecture());
            ps.setInt(4, course.getYear());
            ps.setString(5, course.getNotes());
            int count = ps.executeUpdate();
            if (count > 0) {
                success = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return success;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<Course>();
        String SQL = "SELECT * FROM courses";
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("class_id"));
                course.setName(rs.getString("name"));
                course.setLecture(rs.getString("lecture"));
                course.setYear(rs.getInt("year"));
                course.setNotes(rs.getString("notes"));
                courses.add(course);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return courses;
    }
    
    public List<Course> findByName(String name) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("class_id");
                String courseName = rs.getString("name");
                String lecture = rs.getString("lecture");
                int year = rs.getInt("year");
                String notes = rs.getString("notes");
                courseList.add(new Course(id, courseName, lecture, year, notes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public void deleteCourse(int courseId) throws SQLException {      
        PreparedStatement stmt = null;
        String sql = "DELETE FROM courses WHERE class_id=?";
        stmt = connection.prepareStatement(sql);
        stmt.setInt(1, courseId);
        stmt.executeUpdate();
    }
    
    public void editCourse(Course course) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE courses SET name=?, lecture=?, year=?, notes=? WHERE class_id=?");
            ps.setString(1, course.getName());
            ps.setString(2, course.getLecture());
            ps.setInt(3, course.getYear());          
            ps.setString(4, course.getNotes());
            ps.setInt(5, course.getId());
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course getCourseById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Course course = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM courses WHERE class_id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String lecture = rs.getString("lecture");               
                int year = rs.getInt("year");
                String notes = rs.getString("notes");

                course = new Course(id, name, lecture, year, notes);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return course;
    }

    public List<Course> getCoursesSortedByName() {
        List<Course> courses = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM courses ORDER BY name");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("class_id"));
                course.setName(rs.getString("name"));
                course.setYear(rs.getInt("year"));
                course.setLecture(rs.getString("lecture"));
                course.setNotes(rs.getString("notes"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    public void enrollStudent(int studentId, int classId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO enrollments (student_id, class_id) VALUES (?, ?)");
            ps.setInt(1, studentId);
            ps.setInt(2, classId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Student> getCourseParticipants(int courseId) {
        List<Student> studentList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT students.student_id, students.name, students.grade, students.birthday, students.address, students.notes\n" +
                    "FROM students\n" +
                    "INNER JOIN enrollments ON students.student_id = enrollments.student_id\n" +
                    "WHERE enrollments.class_id = ?");
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getInt("grade"),
                        rs.getDate("birthday"),
                        rs.getString("address"),
                        rs.getString("notes"));
                studentList.add(student);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    public void removeStudentFromCourse(int studentId, int courseId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM enrollments WHERE student_id = ? AND class_id = ?");
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
