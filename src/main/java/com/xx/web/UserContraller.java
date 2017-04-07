package com.xx.web;

import com.xx.constant.MediaTypes;
import com.xx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jishu02 on 2017/4/6.
 */
@RestController
@RequestMapping("/user")
@Configuration
@EnableAutoConfiguration
public class UserContraller {

    @Autowired
    private UserRepository repository;

    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    Object get(@PathVariable Long id) {
        return id == null ? repository.findAll() : repository.findOne(id);
    }


}
