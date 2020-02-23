package com.example.SoapAppcoursemanagement.soap;


import com.example.SoapAppcoursemanagement.bean.Course;
import com.example.course.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    /**
     * method with
     * input - GetCourseDetailsRequest
     * output - GetCourseDetailsResponse
     *
     * "http://example.com/course" - namePlace
     * GetCourseDetailsResponse - name of namePlace
     */

    @PayloadRoot( namespace = "http://example.com/course",
            localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

        Course course = service.findById(request.getId());

        return mapCourse(course);

    }

    @PayloadRoot( namespace = "http://example.com/course",
            localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {

        List<Course> courses = service.findAll();

        return mapCourse(courses);

    }

    private GetCourseDetailsResponse mapCourse(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        response.setCourseDetails(courseDetails);
        return response;
    }

    private GetAllCourseDetailsResponse mapCourse(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course course:courses) {
            CourseDetails courseDetails = new CourseDetails();
            courseDetails.setId(course.getId());
            courseDetails.setName(course.getName());
            courseDetails.setDescription(course.getDescription());
            response.getCourseDetails().add(courseDetails);
        }

        return response;
    }



}
