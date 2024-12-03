package co.edu.ue.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Course;
import co.edu.ue.jpa.ICourseJpa;

@Repository
public class CourseDao implements ICourseDao{
	
	@Autowired
	ICourseJpa jpa;

	@Override
	public List<Course> listCourses() {
		return jpa.findAll();
	}

	@Override
	public Course searchById(int id) {
		return jpa.findById(id).orElse(null);
	}

	@Override
	public boolean postCourse(Course course) {
		if(!jpa.save(course).equals(null)) return true;
		return false;
	}

	@Override
	public Course updateCourse(Course course, int id) {
		Course newCourse = jpa.findById(id).get();
		
		if (Objects.nonNull(course.getCouDescription()) && !"".equalsIgnoreCase(course.getCouDescription())) {
			newCourse.setCouDescription(course.getCouDescription());
		}
		
		if (Objects.nonNull(course.getCouName()) && !"".equalsIgnoreCase(course.getCouName())) {
			newCourse.setCouName(course.getCouName());
		}
		
		if (Objects.nonNull(course.getCouPrice())) {
			newCourse.setCouPrice(course.getCouPrice());
		}
		
		if (Objects.nonNull(course.getStatus())) {
			newCourse.setStatus(course.getStatus());
		}
		
		if (Objects.nonNull(course.getTypeCourse())) {
			newCourse.setTypeCourse(course.getTypeCourse());
		}
		
		return jpa.save(newCourse);
	}

	@Override
	public void deleteCourse(int id) {
		if(jpa.existsById(id)) jpa.deleteById(id);
	}

	@Override
	public Course deleteCourse(Course course, int id) {
		Course newCourse = jpa.findById(id).get();
		
		if (newCourse.getStatus() == true) newCourse.setStatus(false);
		
		return jpa.save(newCourse);
	}

}
