package py.edu.facitec.conf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;

public class TokenFilter extends GenericFilterBean  {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		
		
			HttpServletRequest req = (HttpServletRequest) request;
			
			
			String header = req.getHeader("Authorization");
			
			if (header==null || !header.startsWith("Bearer ")){
				throw new ServletException("Token inexistente o inválido");
			}
				
			
			
			String token = header.substring(7); // Extrayendo solamente el string del Token sin el Bearer
			
			//verificar si el token es valido
			try{						//codigo secreto
				Jwts.parser().setSigningKey("variable").parseClaimsJws(token).getBody();
				chain.doFilter(request, response);
			}catch(Exception e){
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Inválido!");
				//throw new ServletException("Token Inválido");
			}
			
			
	}
}
