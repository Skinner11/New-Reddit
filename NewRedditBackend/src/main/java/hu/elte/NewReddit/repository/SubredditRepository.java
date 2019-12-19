package hu.elte.NewReddit.repository;

import hu.elte.NewReddit.model.Subreddit;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Csalad
 */
public interface SubredditRepository extends CrudRepository<Subreddit, Long> {

	Optional<Subreddit> findByName(String name);
}
