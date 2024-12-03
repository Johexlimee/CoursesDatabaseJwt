package co.edu.ue.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.TypeCourse;
import co.edu.ue.jpa.ITypeCourseJpa;

@Repository
public class TypeCourseDao implements ITypeCourseDao {

	@Autowired
	ITypeCourseJpa jpa;
	
	@Override
	public List<TypeCourse> listTypes() {
		return jpa.findAll();
	}

	@Override
	public TypeCourse searchById(int id) {
		return jpa.findById(id).orElse(null);
	}

	@Override
	public boolean postType(TypeCourse tCourse) {
		if(!jpa.save(tCourse).equals(null)) return true;
		return false;
	}

	@Override
	public TypeCourse updateType(TypeCourse tCourse, int id) {
		TypeCourse newType = jpa.findById(id).get();
		
		if (Objects.nonNull(tCourse.getTypeDescription()) && !"".equalsIgnoreCase(tCourse.getTypeDescription())) {
			newType.setTypeDescription(tCourse.getTypeDescription());
		}
		
		if (Objects.nonNull(tCourse.getStatus())) {
			newType.setStatus(tCourse.getStatus());
		}
		
		return jpa.save(newType);
	}

	@Override
	public TypeCourse deleteType(TypeCourse tCourse, int id) {
		TypeCourse newType = jpa.findById(id).get();
		
		if (newType.getStatus() == true) newType.setStatus(false);
		
		return jpa.save(newType);
	}

}
