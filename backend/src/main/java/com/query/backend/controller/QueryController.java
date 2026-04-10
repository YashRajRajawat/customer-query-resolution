package com.query.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/query")
@CrossOrigin("*")
public class QueryController {

    @PostMapping
    public String getData(@RequestBody Map<String, String> data) {
        System.out.println(data);
        return data.toString();
    }
}
