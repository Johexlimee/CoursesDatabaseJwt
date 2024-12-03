package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.ITypeCourseDao;
import co.edu.ue.entity.TypeCourse;

@Service
public class TypeCourseService implements ITypeCourseService {
	
	@Autowired
	ITypeCourseDao dao;

	@Override
	public List<TypeCourse> listAllTypes() {
		return dao.listTypes();
	}

	@Override
	public TypeCourse searchByIdType(int id) {
		return dao.searchById(id);
	}

	@Override
	public boolean postByType(TypeCourse tCourse) {
		return dao.postType(tCourse);
	}

	@Override
	public TypeCourse updateByType(TypeCourse tCourse, int id) {
		return dao.updateType(tCourse, id);
	}

	@Override
	public TypeCourse deleteByIdType(TypeCourse tCourse, int id) {
		return dao.deleteType(tCourse, id);
	}

}
