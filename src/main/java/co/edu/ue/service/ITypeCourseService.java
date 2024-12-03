package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.TypeCourse;

public interface ITypeCourseService {
	
	List<TypeCourse> listAllTypes();
	TypeCourse searchByIdType(int id);
	boolean postByType(TypeCourse tCourse);
	TypeCourse updateByType(TypeCourse tCourse, int id);
	TypeCourse deleteByIdType(TypeCourse tCourse, int id);
	
}
