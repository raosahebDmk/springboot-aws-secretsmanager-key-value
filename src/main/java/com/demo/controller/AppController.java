package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.Config;

@RestController
@RequestMapping("/get")
public class AppController {

	@Autowired
	Config config;
	
	@GetMapping
	public String getValue()
	{
		return config.loadSecretData();
	}
	
}
