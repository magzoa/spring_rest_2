package py.edu.facitec.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import py.edu.facitec.model.Usuario;
import py.edu.facitec.repository.UsuarioRepository;

@RestController
public class LoginController {

	@Autowired
	private UsuarioRepository usuarioService;
	
	@RequestMapping(value="/autenticar", consumes=MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody  Usuario usuario) throws ServletException{
		System.out.println("LLego usuario "+usuario );
		if (usuario.getNombre() ==null || usuario.getPass()==null){
			throw new ServletException("Nombre y pass obligatorio");
		}
		
		//Consulta en la bd
		Usuario usuAutenticado = usuarioService.buscarPorNombre(usuario.getNombre());
		
		System.out.println("Usuario "+usuAutenticado);
		
		if (usuAutenticado==null){
			throw new ServletException("Usuario no encontrado");
		}
		
		if (!usuAutenticado.getPass().equals(usuario.getPass())){
			throw new ServletException("Usuario o seña invalido");
		}
		
		String token=  Jwts.builder()
				.setSubject(usuAutenticado.getNombre())
				.signWith(SignatureAlgorithm.HS512, "variable")
				.setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 1000))//cinco minutos
				.compact();
		
		
		return	new LoginResponse(token);
	}		
	
	private class LoginResponse{
		public String token;
		 
		public LoginResponse(String token ){
			this.token=token; 
		 }
		
	}
}

