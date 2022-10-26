package py.edu.facitec.model;

import javax.persistence.Entity;

@Entity
public class Usuario extends General {
	
	
	private String nombre;
	
	private String pass;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	

}
