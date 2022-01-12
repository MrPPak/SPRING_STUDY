package com.mrppak.spring_project01.springboot.config.auth.dto;

import com.mrppak.spring_project01.springboot.domain.user.User;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
