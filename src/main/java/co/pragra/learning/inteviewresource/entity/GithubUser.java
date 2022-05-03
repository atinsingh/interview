package co.pragra.learning.inteviewresource.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubUser {
    private String company;
    private int followers;

    private String email;

    @JsonProperty("avatar_url")
    private String avtarUrl;
}
