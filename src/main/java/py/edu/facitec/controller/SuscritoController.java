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

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;


@RestController //Arquitectura Rest

@RequestMapping("/suscritos")//servidor 
				//url que afecta a todos los metodos
public class SuscritoController {
	
	@Autowired //inicializa dentro del contexto de Spring
	private SuscritoRepository suscritoRepository;
	
	
	@GetMapping //Respondera al verbo   GET.
								// url  /suscritos	
	public ResponseEntity<List<Suscrito>> getAll(){
		
	//realizamos la consulta y cargamos el objeto suscritos
		List<Suscrito> suscritos=suscritoRepository.findAll();
		
	//retornamos la lista con el status.
		
		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
		
	}
	//				/suscritos
	//Verbo 		Post  						//Indicar que los datos viajan dentro del request
	@PostMapping							//Datos que vienen del cliente es el objeto request
	public ResponseEntity<Suscrito> create(@RequestBody Suscrito suscritoLlega){
		
		System.out.println(suscritoLlega.toString());
		
		try {
			
			Suscrito suscritoRegistrado=suscritoRepository.save(suscritoLlega);
			
			System.out.println(suscritoRegistrado.toString());
			
			return new ResponseEntity<Suscrito>(suscritoRegistrado, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		//POSTMAN
		
		
		
	}
	
	
						//recibimos el codigo
	@GetMapping(value="/{codigo}")			//Cargamos la variable
	public ResponseEntity<Suscrito> getOne(@PathVariable Long codigo){
		
	//ayuda para trabajar con valores nulos					//consulta por codigo
		Optional<Suscrito> suscritoConsulta=suscritoRepository.findById(codigo);
		
		if(suscritoConsulta.isPresent()) {
			
			return new ResponseEntity<Suscrito>(suscritoConsulta.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	//Un objeto contenedor que puede contener o no un valor no nulo. Si un valor está presente, isPresent()regresará true y get()devolverá el valor.
	
	
	//Verbo 		DELETE
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Suscrito>  deleteById(@PathVariable Long codigo){
		
		//logica para eliminar a traves de una tabla pre cargada.
		try {
			suscritoRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			
			
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	
	
	
	
	
	

}
