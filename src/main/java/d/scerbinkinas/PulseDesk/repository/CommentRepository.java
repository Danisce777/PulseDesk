package d.scerbinkinas.PulseDesk.repository;

import d.scerbinkinas.PulseDesk.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}