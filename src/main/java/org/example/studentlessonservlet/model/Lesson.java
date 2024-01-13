package org.example.studentlessonservlet.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private int id;
    private String name;
    private double duration;
    private String lectureName;
    private double price;
}
