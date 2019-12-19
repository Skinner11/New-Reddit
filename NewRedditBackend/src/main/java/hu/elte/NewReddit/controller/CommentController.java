package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.Comment;
import hu.elte.NewReddit.model.User;
import hu.elte.NewReddit.repository.CommentRepository;
import hu.elte.NewReddit.repository.RedditPostRepository;
import hu.elte.NewReddit.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RedditPostRepository redditpostRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public ResponseEntity<Iterable<Comment>> getAll() {
		return new ResponseEntity(commentRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<Iterable<Comment>> getAllCommentsByPostId(@PathVariable Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isPresent()) {
			return new ResponseEntity(comment.get(), HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Iterable<Comment>> getAllCommentsByUser(@PathVariable Long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return new ResponseEntity<>(commentRepository.findAllByUser(user.get()), HttpStatus.OK);
		}

		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity deleteComment(@PathVariable Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isPresent()) {
			commentRepository.delete(comment.get());
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@PatchMapping("/{commentId}")
	public ResponseEntity modifyComment(@PathVariable Long commentId, @RequestBody Comment reqComment) {
		Optional<Comment> comment = commentRepository.findById(commentId);

		if (comment.isPresent()) {
			Comment newComment = new Comment(comment.get());
			commentRepository.delete(comment.get());
			newComment.setText(reqComment.getText());
			commentRepository.save(newComment);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
}
