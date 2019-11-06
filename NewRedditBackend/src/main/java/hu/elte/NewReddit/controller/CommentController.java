package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.Comment;
import hu.elte.NewReddit.repository.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping("/{postId}}")
	public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
		return new ResponseEntity(commentRepository.findAllByPost_id(postId), HttpStatus.OK);
	}

}
