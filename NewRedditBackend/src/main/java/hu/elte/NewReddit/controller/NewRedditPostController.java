/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.RedditPost;
import hu.elte.NewReddit.model.Subreddit;
import hu.elte.NewReddit.repository.RedditPostRepository;
import hu.elte.NewReddit.repository.SubredditRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Maffia
 */
@RestController
@RequestMapping("/posts")
public class NewRedditPostController {

	@Autowired
	private RedditPostRepository redditPostRepository;

	@Autowired
	private SubredditRepository subredditRepository;

	@GetMapping(value = "")

	public ResponseEntity<Iterable<RedditPost>> getAllPosts() {
		return new ResponseEntity(redditPostRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<RedditPost> getPostById(@PathVariable Long id) {
		Optional<RedditPost> res = redditPostRepository.findById(id);
		if (res.isPresent()) {
			return new ResponseEntity(res, HttpStatus.OK);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/subreddit/{subbredditId}")
	public ResponseEntity<Iterable<RedditPost>> getPostsBySubbreddit(@PathVariable Long subbredditId) {
		Optional<Subreddit> subred = subredditRepository.findById(subbredditId);
		if (subred.isPresent()) {
			return new ResponseEntity(redditPostRepository.findAllBySubreddit(subred.get()), HttpStatus.OK);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}
