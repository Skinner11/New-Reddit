package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.Comment;
import hu.elte.NewReddit.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("")
	public ResponseEntity<Iterable<Comment>> getAll() {
		return new ResponseEntity(commentRepository.findAll(), HttpStatus.OK);
	}

}
