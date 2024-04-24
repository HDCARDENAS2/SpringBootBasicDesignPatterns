package com.learn.desingpatterns.event;

import org.springframework.context.ApplicationEvent;

import com.learn.desingpatterns.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatedEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092894459554202813L;
	
	private UserDTO user;
	
    public UserCreatedEvent(Object source, UserDTO user) {
        super(source);
        this.user = user;
    }
    
    
}
