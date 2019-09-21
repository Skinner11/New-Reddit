/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.controller;

import hu.elte.NewReddit.model.NewRedditPost;
import java.util.concurrent.atomic.AtomicLong;
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

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/")
    public ResponseEntity<NewRedditPost> root() {
        return new ResponseEntity<>((new NewRedditPost(counter.incrementAndGet(), "Jian Yang", "Eric Bachman is dead")), HttpStatus.OK);
    }
}
