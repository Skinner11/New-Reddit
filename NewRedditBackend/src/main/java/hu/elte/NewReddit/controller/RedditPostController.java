package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.RedditPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.elte.NewReddit.repository.RedditPostRepository;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/posts")
public class RedditPostController {

	@Autowired
	private RedditPostRepository redditPostRepository;

	@GetMapping("")
	public ResponseEntity<Iterable<RedditPost>> getAll() {
		return new ResponseEntity(redditPostRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RedditPost> getById(@PathVariable Long id) {
		Optional<RedditPost> item = redditPostRepository.findById(id);
		if (!item.isPresent()) {
			return new ResponseEntity(item, HttpStatus.OK);
		}
		return new ResponseEntity(item, HttpStatus.NOT_FOUND);
	}

}
