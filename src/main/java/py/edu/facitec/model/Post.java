package py.edu.facitec.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity				//Aplicación de herencia
//@Table( name = "table_post" ) cambiar nombre de la tabla.
public class Post extends General {
	
	private String titulo;
	private String autor;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	private String texto;
	
	//Uno a muchos.   indicar el objeto Post de la otra clase
		//  Aplicar la bidireccionalidad de la relación
	
							//esta lista es importante visualizar.
	@JsonManagedReference //se visualiza la lista de comentarios
	@OneToMany(mappedBy ="post")
	private List<Comentario> comentarios; //asociación 1p

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Post [titulo=" + titulo + ", autor=" + autor + ", fecha=" + fecha + ", texto=" + texto + ", toString()="
				+ super.toString() + "]";
	}

	
	
	//Getters y Setters

	
}
