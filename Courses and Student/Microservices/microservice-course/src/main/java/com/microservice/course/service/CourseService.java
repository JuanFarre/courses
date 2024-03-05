package com.microservice.course.service;

import com.microservice.course.dto.StudentDTO;
import com.microservice.course.model.Course;
import com.microservice.course.dto.StudentByCourseResponse;
import com.microservice.course.repository.ICourseRepository;
import com.microservice.course.repository.StudentAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private StudentAPI studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {

        StudentByCourseResponse  estudiante = new StudentByCourseResponse();
        // Consultar el curso
        Course course = courseRepository.findById(idCourse).orElse(new Course());

        // Obtener los estudiantes
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);

        estudiante.setCourseName(course.getName());
        estudiante.setTeacher(course.getTeacher());
        estudiante.setStudentDTOList(studentDTOList);


        return estudiante;
    }
}
