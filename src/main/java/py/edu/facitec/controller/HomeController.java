package py.edu.facitec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Va servir para responder a una determinada URL,
//con una página como respuesta.

@Controller
public class HomeController {
	
	//Recibir una URL como configuración
	@RequestMapping("/")
	public String home() {
		
		System.out.println("Ingrese a la página de inicio");
		
		//Retornar una página
		return "usuario/login";
	}
	
	@RequestMapping("/form")
	public String formSuscrito() {
		
		System.out.println("Cargando la página de Suscrito");
		
		return "suscrito/form";
	}
	
	

}
