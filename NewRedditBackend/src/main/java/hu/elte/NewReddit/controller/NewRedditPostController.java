/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maffia
 */
@RestController
public class NewRedditPostController {

	@GetMapping(value = "")
	public ResponseEntity<String> root() {
		return new ResponseEntity(new String("Epstein Didn't Kill Himself"), HttpStatus.OK);
	}
}
