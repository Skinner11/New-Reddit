/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.repository;

import hu.elte.NewReddit.model.Comment;
import hu.elte.NewReddit.model.RedditPost;
import hu.elte.NewReddit.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Csalad
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

	Iterable<Comment> findAllByPost(RedditPost redditPost);

	Iterable<Comment> findAllByUser(User user);
}
