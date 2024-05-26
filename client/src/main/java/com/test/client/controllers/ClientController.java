package com.test.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
public class ClientController {
	
	@Autowired
	private EurekaClient client;
	
	@Autowired
	private RestTemplateBuilder templateBuilder;
	
	@RequestMapping("/")
	public String callService() {
		InstanceInfo instance = client.getNextServerFromEureka("service", false);
		String url = instance.getHomePageUrl();
		
		RestTemplate restTemplate = templateBuilder.build();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return response.getBody();
		
	}
}
