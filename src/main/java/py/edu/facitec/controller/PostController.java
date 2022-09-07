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

import py.edu.facitec.model.Post;
import py.edu.facitec.repository.PostRepository;


@RestController //Arquitectura Rest

@RequestMapping("/posts")//servidor 
				//url que afecta a todos los metodos
public class PostController {
	
	@Autowired //inicializa dentro del contexto de Spring
	private PostRepository postRepository;
	
	
	@GetMapping //Respondera al verbo   GET.
								// url  /posts	
	public ResponseEntity<List<Post>> getAll(){
		
	//realizamos la consulta y cargamos el objeto posts
		List<Post> posts=postRepository.findAll();
		
	//retornamos la lista con el status.
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		
	}
	//				/posts
	//Verbo 		Post  						//Indicar que los datos viajan dentro del request
	@PostMapping							//Datos que vienen del cliente es el objeto request
	public ResponseEntity<Post> create(@RequestBody Post postLlega){
		
		System.out.println(postLlega.toString());
		
		try {
			
			Post postRegistrado=postRepository.save(postLlega);
			
			System.out.println(postRegistrado.toString());
			
			return new ResponseEntity<Post>(postRegistrado, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		//POSTMAN
		
		
		
	}
	
	
						//recibimos el codigo
	@GetMapping(value="/{codigo}")			//Cargamos la variable
	public ResponseEntity<Post> getOne(@PathVariable Long codigo){
		
	//ayuda para trabajar con valores nulos					//consulta por codigo
		Optional<Post> postConsulta=postRepository.findById(codigo);
		
		if(postConsulta.isPresent()) {
			
			return new ResponseEntity<Post>(postConsulta.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	//Un objeto contenedor que puede contener o no un valor no nulo. Si un valor está presente, isPresent()regresará true y get()devolverá el valor.
	
	
	//Verbo 		DELETE
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Post>  deleteById(@PathVariable Long codigo){
		
		//logica para eliminar a traves de una tabla pre cargada.
		try {
			postRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			
			
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	
	
	
	
	
	

}
