package com.test.employservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.test.employservice.command.command.CreateEmployeeCommand;
import com.test.employservice.command.command.DeleteEmployeeCommand;
import com.test.employservice.command.command.UpdateEmployeeCommand;
import com.test.employservice.command.event.EmployeeCreatedEvent;
import com.test.employservice.command.event.EmployeeDeletedEvent;
import com.test.employservice.command.event.EmployeeUpdatedEvent;

@Aggregate
public class EmployeeAggregate {
	@AggregateIdentifier
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private Boolean isDisciplined;
	
	public EmployeeAggregate() {}
	
	@CommandHandler
	public EmployeeAggregate(CreateEmployeeCommand command) {
		EmployeeCreatedEvent event = new EmployeeCreatedEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	@CommandHandler
	public void handle(UpdateEmployeeCommand command) {
		EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}
	@CommandHandler
	public void handle(DeleteEmployeeCommand command) {
		EmployeeDeletedEvent event = new EmployeeDeletedEvent();
		event.setEmployeeId(command.getEmployeeId());
		AggregateLifecycle.apply(event);
	}
	@EventSourcingHandler
	public void on(EmployeeCreatedEvent event) {
		this.employeeId = event.getEmployeeId();
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.kin = event.getKin();
		this.isDisciplined = event.getIsDisciplined();
	}
	@EventSourcingHandler
	public void on(EmployeeUpdatedEvent event) {
		this.employeeId = event.getEmployeeId();
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.kin = event.getKin();
		this.isDisciplined = event.getIsDisciplined();
	}
	@EventSourcingHandler
	public void on(EmployeeDeletedEvent event) {
		this.employeeId = event.getEmployeeId();
	}
}
