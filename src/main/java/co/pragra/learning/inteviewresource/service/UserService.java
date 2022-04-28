package co.pragra.learning.inteviewresource.service;

import co.pragra.learning.inteviewresource.entity.User;
import co.pragra.learning.inteviewresource.exceptions.InvalidUserException;
import co.pragra.learning.inteviewresource.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        if (user == null) {
            throw new InvalidUserException("Provided user is null");
        }
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return repository.save(user);
    }

    public User updateUser(User user){
        if (user == null) {
            throw new InvalidUserException("Provided user is null");
        }
        user.setUpdateDate(new Date());
        return repository.save(user);
    }

    public void deleteById(String uuid) {
        repository.deleteById(UUID.fromString(uuid));
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public User getByID(UUID id) {
        return repository.getById(id);
    }
}
