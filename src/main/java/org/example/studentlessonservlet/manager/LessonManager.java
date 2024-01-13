package org.example.studentlessonservlet.manager;

import org.example.studentlessonservlet.db.DBConnectionProvider;
import org.example.studentlessonservlet.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Lesson get(int id) {
        String query = "SELECT * FROM lesson WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getDouble("duration"))
                        .lectureName(resultSet.getString("lecturerName"))
                        .price(resultSet.getDouble("price"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getAll() {
        String query = "SELECT * FROM lesson";
        List<Lesson> lessons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getDouble("duration"))
                        .lectureName(resultSet.getString("lecturerName"))
                        .price(resultSet.getDouble("price"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public void add(Lesson lesson) {
        String query = "INSERT INTO lesson(name,duration,lecturerName, price) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setDouble(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLectureName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM lesson WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
