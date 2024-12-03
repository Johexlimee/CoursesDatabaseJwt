package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



/**
 * The persistent class for the type_course database table.
 * 
 */
@Entity
@Table(name="type_course")
@NamedQuery(name="TypeCourse.findAll", query="SELECT t FROM TypeCourse t")
public class TypeCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="type_id")
	private int typeId;

	@NotNull
	@NotEmpty
	@Pattern(regexp = "[a-zA-Z0-9 ñÑüÜ.,;\"()]", message = "El campo contiene errores, verificar los datos e intente de nuevo")
	@Size(min=1, max=250)
	@Column(name="type_description")
	private String typeDescription;
	
	private boolean status;

	public TypeCourse() {
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	
	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}