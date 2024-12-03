package co.edu.ue.dao;

import java.util.List;

import co.edu.ue.entity.TypeCourse;

public interface ITypeCourseDao {
	
	List<TypeCourse> listTypes();
	TypeCourse searchById(int id);
	boolean postType(TypeCourse tCourse);
	TypeCourse updateType(TypeCourse tCourse, int id);
	TypeCourse deleteType(TypeCourse tCourse, int id);
	
}
