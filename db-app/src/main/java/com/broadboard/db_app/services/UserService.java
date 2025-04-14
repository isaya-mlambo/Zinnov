package com.broadboard.db_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.broadboard.db_app.Repositories.UserRepository;
import com.broadboard.db_app.models.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User updateUser(Long id, User updatedUser) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser != null) {
			//Update fields
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setEmail(updatedUser.getEmail());
			return userRepository.save(existingUser);
		}
		return null;
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
