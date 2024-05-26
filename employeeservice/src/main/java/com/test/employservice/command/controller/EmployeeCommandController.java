	package com.test.employservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.cloud.stream.function.StreamBridge;

//import org.springframework.cloud.stream.annotation.EnableBinding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.employservice.command.command.CreateEmployeeCommand;
import com.test.employservice.command.command.DeleteEmployeeCommand;
import com.test.employservice.command.command.UpdateEmployeeCommand;
import com.test.employservice.command.model.EmployeeRequestModel;

@RestController
@RequestMapping("/api/v1/employees")
//@EnableBinding(Source.class)
public class EmployeeCommandController {
	@Autowired 
	private CommandGateway commandGateway;
	
//	private MessageChannel output;
	
	 @Autowired
	    private StreamBridge streamBridge;
	
	@PostMapping
	public String addEmployee(@RequestBody EmployeeRequestModel model) {
		CreateEmployeeCommand command = 
			new CreateEmployeeCommand(UUID.randomUUID().toString(),
					model.getFirstName(), model.getLastName(), model.getKin(), false);
		commandGateway.sendAndWait(command);
		
		return "emmployee added";
	}
	@PutMapping
	public String updateEmployee(@RequestBody EmployeeRequestModel model) {
		UpdateEmployeeCommand command =
				new UpdateEmployeeCommand(model.getEmployeeId(),
						model.getFirstName(),model.getLastName(),model.getKin(),model.getIsDisciplined());
		commandGateway.sendAndWait(command);
		return "employee updated";
	}
	@DeleteMapping("/{employeeId}")
	public String deleteEmployee(@PathVariable String employeeId) {
		DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
		commandGateway.sendAndWait(command);
		return "emmployee deleted";
	}
//	@PostMapping("/sendMessage")
//	public void SendMessage(@RequestBody String message) {
//		try {
//			
//			ObjectMapper mapper = new ObjectMapper();
//			String json = mapper.writeValueAsString(message);
//			output.send(MessageBuilder.withPayload(json).build());
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	 @PostMapping("/sendMessage")
	    public void sendMessage(@RequestBody String message) {
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            String json = mapper.writeValueAsString(message);
	            streamBridge.send("sendMessage-out-0", json);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	    }
}
