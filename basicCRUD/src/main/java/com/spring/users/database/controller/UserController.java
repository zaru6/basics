package com.spring.users.database.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.users.database.model.User;
import com.spring.users.database.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	public User createEmployee(@RequestBody User user) {
        return userRepository.save(user);
    	}
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable long id) {
		return this.userRepository.findById(id);
	}
	
	@PutMapping("/users/{id}")
	public String updateUser(@PathVariable long id, @RequestBody User user) {
		Optional<User> findUser = this.userRepository.findById(id);
		if(findUser.isPresent()) {
			User updatedUser = userRepository.getOne(id); //.getOne returns a reference, best way
			updatedUser.setUsername(user.getUsername());
			userRepository.save(updatedUser);
			return "Updated user with id " + id + ".";
		} else {
			return "User not found.";
		}
	}
	
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
    	try {
    		this.userRepository.deleteById(id);
    		return "Deleted user with id " + id + ".";
    	} catch(Exception e) {
    	    return "User with id " + id + " doesn't exist.";
    	}
    } 
}
