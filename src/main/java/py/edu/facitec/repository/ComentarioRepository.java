package py.edu.facitec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
