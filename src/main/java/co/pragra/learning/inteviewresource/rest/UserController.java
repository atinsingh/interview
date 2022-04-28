package co.pragra.learning.inteviewresource.rest;

import co.pragra.learning.inteviewresource.entity.User;
import co.pragra.learning.inteviewresource.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/user")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }
}
