package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.Lesson;
import org.example.studentlessonservlet.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final LessonManager lessonManager = new LessonManager();

    public List<Student> getAll() {
        String query = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonManager.get(resultSet.getInt("lesson_id")))
                        .photoName(resultSet.getString("photo_name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void add(Student student) {
        String query = "INSERT INTO student(name,surname,email,age,lesson_id, photo_name) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setString(6, student.getPhotoName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student get(int id) {
        String query = "SELECT * FROM student WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonManager.get(resultSet.getInt("lesson_id")))
                        .photoName(resultSet.getString("photo_name"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {
        String query = "DELETE FROM student WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Student student) {
        String query = "UPDATE student name=?, surname=?, email=?, age=?, lesson_id=?, photo_name=? WHERE id=" + student.getId();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setString(6, student.getPhotoName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
