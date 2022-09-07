package py.edu.facitec.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass //Indicar Herencia.
public abstract class General {
	
@Id				//recurre al auto incrementable de la bd
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

@Override
public String toString() {
	return "General [id=" + id + "]";
}



}
