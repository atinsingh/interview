package co.pragra.learning.inteviewresource.rest;

import co.pragra.learning.inteviewresource.entity.GithubUser;
import co.pragra.learning.inteviewresource.entity.User;
import co.pragra.learning.inteviewresource.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final RestTemplate restTemplate;



    @GetMapping(value = "/user")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

   // https://api.github.com/users/defunkt

    @GetMapping("/user/github/{login}")
    public ResponseEntity<GithubUser> getGithubUser(@PathVariable("login") String login){
        return restTemplate.getForEntity("https://api.github.com/users/{login}", GithubUser.class,login);
    }

}
