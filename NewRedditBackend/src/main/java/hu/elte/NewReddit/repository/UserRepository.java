/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.NewReddit.repository;

import hu.elte.NewReddit.model.User;
import java.util.Optional;

/**
 *
 * @author Csalad
 */
public interface UserRepository {

	Optional<User> findByUsername(String username);
}
