package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.Course;

public interface ICourseService {

	List<Course> listAllCourses();
	List<Course> listAllCoursesIVA();
	Course searchByIdCourse(int id);
	boolean postByCourse(Course course);
	Course updateByCourse(Course course, int id);
	void deleteByIdCourse(int id);
	Course deleteByIdCourse(Course course, int id);
	
}
