package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	public List<User> listAll() {
		return repo.findAll();
	}
	
	public void save(User user) {
		user.setEnabled(true);
		user.setFull_name(user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
	}
	
	public User get(User user) {
		return repo.findById(user.getId()).get();
	}

	public User findById(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(User user) {
		repo.deleteById(user.getId());
	}
}
