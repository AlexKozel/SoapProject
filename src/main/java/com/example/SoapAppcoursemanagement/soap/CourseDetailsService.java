package com.example.SoapAppcoursemanagement.soap;

import com.example.SoapAppcoursemanagement.bean.Course;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * getCourse
 * getAll
 * deleteCourse
 */


@Component
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static {
        Course course1 = new Course(1, "Spring", "10 steps");
        courses.add(course1);

        Course course2 = new Course(2, "Spring MVC", "10 examples");
        courses.add(course2);

        Course course3 = new Course(3, "Spring Boot", "10 students");
        courses.add(course3);

    }

    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
            return course;}
        } return null;
    }

    public List<Course> findAll(){
        return courses;
    }

    public int deleteById(int id) {
       Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()){
            Course course = iterator.next();
            if (course.getId() == id){
            iterator.remove();
            return 1;
            }
        }return 0;
    }
}
