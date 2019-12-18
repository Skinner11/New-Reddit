/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.Subreddit;
import hu.elte.NewReddit.repository.SubredditRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Csalad
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/subreddits")
public class SubredditController {

	@Autowired
	private SubredditRepository subredditRepository;

	@GetMapping(value = "")
	public ResponseEntity<Iterable<Subreddit>> getAllSubreddits() {
		return new ResponseEntity(subredditRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Subreddit> getSubredditById(@PathVariable Long id) {
		Optional<Subreddit> res = subredditRepository.findById(id);
		if (res.isPresent()) {
			return new ResponseEntity(res, HttpStatus.OK);
		}
		return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}
}
