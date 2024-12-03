package co.edu.ue.dao;

import java.util.List;

import co.edu.ue.entity.Course;

public interface ICourseDao {

	List<Course> listCourses();
	Course searchById(int id);
	boolean postCourse(Course course);
	Course updateCourse(Course course, int id);
	void deleteCourse(int id);
	Course deleteCourse(Course course, int id);
	
}
