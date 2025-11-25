package com.learn.desingpatterns.service.impl;

import java.util.List;
/*import java.util.stream.Collectors;*/

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.desingpatterns.custom.UserServiceException;
import com.learn.desingpatterns.dto.UserDTO;
import com.learn.desingpatterns.entity.UserEntity;
import com.learn.desingpatterns.event.UserCreatedEvent;
import com.learn.desingpatterns.factory.JmsMessagingCustom;
import com.learn.desingpatterns.mapper.UserMapper;
import com.learn.desingpatterns.repository.UserRepository;
import com.learn.desingpatterns.service.JmsProducer;
import com.learn.desingpatterns.service.UserService;

import lombok.extern.log4j.Log4j2;

@Service("userService")
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {

    private final JmsMessagingCustom jmsMessagingCustom;

    private final JmsProducer jmsProducer;
	
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    
    public UserServiceImpl(
    		UserMapper userMapper,
    		@Qualifier("userRepository") UserRepository userRepository,
    		ApplicationEventPublisher eventPublisher,
    		  JmsMessagingCustom jmsMessagingCustom
              ,
    		  JmsProducer jmsProducer) {
        this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.eventPublisher = eventPublisher;
		this.jmsProducer = jmsProducer;
		this.jmsMessagingCustom = jmsMessagingCustom;
	
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO save(UserDTO userDTO) throws UserServiceException {
        log.info("Entering save method with userDTO: {}", userDTO);
        try{
        UserEntity userEntity = userMapper.toEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserDTO savedUserDTO = userMapper.toDTO(savedUserEntity);
        //Implemetar una funcionalidad que permita crear un JMS con una (DI), en el mensaje JMS se enviara el id creado del usuario.
        jmsMessagingCustom.send("User created with ID: " + savedUserDTO.getId());
        jmsProducer .sendMessage("User created with ID: " + savedUserDTO.getId());
        
        eventPublisher.publishEvent(new UserCreatedEvent(this, savedUserDTO));
        log.info("Exiting save method with savedUserDTO: {}", savedUserDTO);
        return savedUserDTO;
        } catch (Exception e) {
            log.error("Error occurred while saving user: {}", e.getMessage());
            throw new UserServiceException("Failed to save user", e);
        }
    }

    @Override
    public UserDTO findById(Integer id) {
        log.info("Entering findById method with id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        log.info("Exiting findById method with userDTO: {}", userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        log.info("Entering findAll method");
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = userMapper.toDTOList(userEntities);
        log.info("Exiting findAll method with userDTOs: {}", userDTOs);
        return userDTOs;
    }

    @Override
    public List<UserDTO> findUsersCreatedToday() {
    	 log.info("Entering findUsersCreatedToday method");
        List<UserEntity> usersCreatedToday = userRepository.findUsersCreatedToday();
        List<UserDTO> userDTOs = userMapper.toDTOList(usersCreatedToday);
        log.info("Exiting findUsersCreatedToday method with userDTOs: {}", userDTOs);
        return userDTOs;
    }
    
     //create method findUsersCreatedByYear(Integer year)
    @Override
    public List<UserDTO> findUsersCreatedByYear(Integer year) {
   	 log.info("Entering findUsersCreatedByYear method with year: {}", year);
       List<UserEntity> usersCreatedByYear = userRepository.findUsersCreatedByYear(year);
       List<UserDTO> userDTOs = userMapper.toDTOList(usersCreatedByYear);
       log.info("Exiting findUsersCreatedByYear method with userDTOs: {}", userDTOs);
       return userDTOs;
    }
}