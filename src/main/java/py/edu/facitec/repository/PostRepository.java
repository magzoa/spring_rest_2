package py.edu.facitec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
