package com.myproject.cloud.controller;

import com.myproject.cloud.domain.Features;
import com.myproject.cloud.domain.User;
import com.myproject.cloud.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by user on 12/06/17.
 */

@RefreshScope
@RestController
public class ClientController {

    @Value("${foo}")
    private String foo;

    @Autowired
    private User user;
    @Autowired
    private Features features;

    @RequestMapping(
            value = "/foo",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String foo() {
        return String.format("The value of foo is >>> '%s'...\n", foo);


    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> user() {


        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);


    }

    @RequestMapping(
            value = "/features",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> features() {

        return new ResponseEntity<Map<String, Object>>(features.getFeaturesMap(), HttpStatus.OK);

    }
}
