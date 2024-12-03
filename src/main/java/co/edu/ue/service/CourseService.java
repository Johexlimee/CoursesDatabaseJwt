package co.edu.ue.service;

import java.util.List;
/*import java.util.stream.Collectors;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.ICourseDao;
import co.edu.ue.entity.Course;

@Service
public class CourseService implements ICourseService {
	
	@Autowired
	ICourseDao dao;
	
	//final int IVA_19 = 19;

	@Override
	public List<Course> listAllCourses() {
		return dao.listCourses();
	}

	@Override
	public List<Course> listAllCoursesIVA() {
		/*return dao.listCourses().stream().map(
				c->{
					//casteo de double a long
					int value = (c.getCouPrice() + (Math.multiplyExact(IVA_19, IVA_19) c.getCouPrice()*IVA_19));
					Course course = new Course(c.getName(), c.getDescripcion(), c.getDuration(), value);
					return course;
				}).collect(Collectors.toList());*/
		return null;
	}

	@Override
	public Course searchByIdCourse(int id) {
		return dao.searchById(id);
	}

	@Override
	public boolean postByCourse(Course course) {
		return dao.postCourse(course);
	}

	@Override
	public Course updateByCourse(Course course, int id) {
		return dao.updateCourse(course, id);
	}

	@Override
	public void deleteByIdCourse(int id) {
		dao.deleteCourse(id);
	}

	@Override
	public Course deleteByIdCourse(Course course, int id) {
		return dao.deleteCourse(course, id);
	}

}
