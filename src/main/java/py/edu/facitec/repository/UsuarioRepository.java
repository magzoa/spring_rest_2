package py.edu.facitec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import py.edu.facitec.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value="Select u from Usuario u where u.nombre=:pnombre")
	public Usuario buscarPorNombre(@Param("pnombre") String nombre);
	
	
	
}

