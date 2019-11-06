/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.repository;

import hu.elte.NewReddit.model.PostContent;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Csalad
 */
public interface PostContentRepository extends CrudRepository<PostContent, Long> {

}
