package py.edu.facitec.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comentario extends General{
	
	private String texto;
	private Integer estrellas;
	
	//Muchos a Uno
		
			//lo dejamos invisible
			//o ver el objeto post deja de ser importante.
	@JsonBackReference 
	@ManyToOne
	private Post post;//aplicación de asociación 1p
	
	
	@ManyToOne //1p
	private Suscrito suscrito; //1p

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Integer getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(Integer estrellas) {
		this.estrellas = estrellas;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Suscrito getSuscrito() {
		return suscrito;
	}

	public void setSuscrito(Suscrito suscrito) {
		this.suscrito = suscrito;
	}
	
	//getters y setters
	
	

}
