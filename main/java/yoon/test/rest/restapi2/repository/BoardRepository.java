package yoon.test.rest.restapi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.rest.restapi2.domain.Post;

@Repository
public interface BoardRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
}
