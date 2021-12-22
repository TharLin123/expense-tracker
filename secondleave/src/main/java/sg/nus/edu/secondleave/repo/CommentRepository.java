package sg.nus.edu.secondleave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.edu.secondleave.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
