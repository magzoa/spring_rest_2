package py.edu.facitec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;


@RestController //Arquitectura Rest

@RequestMapping("/comentarios")//servidor 
				//url que afecta a todos los metodos
public class ComentarioController {
	
	@Autowired //inicializa dentro del contexto de Spring
	private ComentarioRepository comentarioRepository;
	
	
	@GetMapping //Respondera al verbo   GET.
								// url  /comentarios	
	public ResponseEntity<List<Comentario>> getAll(){
		
	//realizamos la consulta y cargamos el objeto comentarios
		List<Comentario> comentarios=comentarioRepository.findAll();
		
	//retornamos la lista con el status.
		
		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);
		
	}
	//				/comentarios
	//Verbo 		Post  						//Indicar que los datos viajan dentro del request
	@PostMapping							//Datos que vienen del cliente es el objeto request
	public ResponseEntity<Comentario> create(@RequestBody Comentario comentarioLlega){
		
		System.out.println(comentarioLlega.toString());
		
		try {
			
			Comentario comentarioRegistrado=comentarioRepository.save(comentarioLlega);
			
			System.out.println(comentarioRegistrado.toString());
			
			return new ResponseEntity<Comentario>(comentarioRegistrado, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		//POSTMAN
		
		
		
	}
	
	
						//recibimos el codigo
	@GetMapping(value="/{codigo}")			//Cargamos la variable
	public ResponseEntity<Comentario> getOne(@PathVariable Long codigo){
		
	//ayuda para trabajar con valores nulos					//consulta por codigo
		Optional<Comentario> comentarioConsulta=comentarioRepository.findById(codigo);
		
		if(comentarioConsulta.isPresent()) {
			
			return new ResponseEntity<Comentario>(comentarioConsulta.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	//Un objeto contenedor que puede contener o no un valor no nulo. Si un valor está presente, isPresent()regresará true y get()devolverá el valor.
	
	
	//Verbo 		DELETE
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Comentario>  deleteById(@PathVariable Long codigo){
		
		//logica para eliminar a traves de una tabla pre cargada.
		try {
			comentarioRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			
			
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	
	
	
	
	
	

}
