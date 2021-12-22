package sg.nus.edu.secondleave.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.edu.secondleave.model.Comment;
import sg.nus.edu.secondleave.repo.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepo;

	@Override
	public void saveComment(Comment comment) {
		commentRepo.save(comment);
	}

}
