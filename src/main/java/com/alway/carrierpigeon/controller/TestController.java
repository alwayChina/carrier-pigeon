package com.alway.carrierpigeon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangwei
 * @date 2020-01-13 15:20
 * @desc
 **/

@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring Boot!";
	}
}
