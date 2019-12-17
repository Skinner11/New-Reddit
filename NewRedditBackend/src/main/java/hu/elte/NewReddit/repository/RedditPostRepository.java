/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.repository;

import hu.elte.NewReddit.model.RedditPost;
import hu.elte.NewReddit.model.Subreddit;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Csalad
 */
public interface RedditPostRepository extends CrudRepository<RedditPost, Long> {

	Iterable<RedditPost> findAllBySubreddit(Subreddit subreddit);
}
