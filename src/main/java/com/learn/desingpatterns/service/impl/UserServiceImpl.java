package com.learn.desingpatterns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.desingpatterns.dto.UserDTO;
import com.learn.desingpatterns.entity.UserEntity;
import com.learn.desingpatterns.event.UserCreatedEvent;
import com.learn.desingpatterns.factory.JmsMessagingCustom;
import com.learn.desingpatterns.mapper.UserMapper;
import com.learn.desingpatterns.repository.UserRepository;
import com.learn.desingpatterns.service.UserService;

import lombok.extern.log4j.Log4j2;

@Service("userService")
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {
	
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    @Autowired
    private JmsProducer jmsProducer;
    
    public UserServiceImpl(
    		UserMapper userMapper,
    		@Qualifier("userRepository") UserRepository userRepository,
    		ApplicationEventPublisher eventPublisher,
    		JmsMessagingCustom jmsMessagingCustom
    		) {
        this.userMapper = userMapper;
		this.userRepository = userRepository;
		this.eventPublisher = eventPublisher;
	
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO save(UserDTO userDTO) throws Exception {
        log.info("Entering save method with userDTO: {}", userDTO);
        UserEntity userEntity = userMapper.toEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserDTO savedUserDTO = userMapper.toDTO(savedUserEntity);
        //TODO send JMS
        eventPublisher.publishEvent(new UserCreatedEvent(this, savedUserDTO));
        log.info("Exiting save method with savedUserDTO: {}", savedUserDTO);
        jmsProducer.sendMessage("Parece que esto funciona.");
        return savedUserDTO;
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
    
     //TODO create method findUsersCreatedByYear(Integer year)
    @Override
    public List<UserDTO> findUsersCreatedByYear(Integer year) {
    	 log.info("Entering findUsersCreatedByYear method");
        List<UserEntity> findUsersCreatedByYear = userRepository.findUsersCreatedByYear(year);
        List<UserDTO> userDTOs = userMapper.toDTOList(findUsersCreatedByYear);
        log.info("Exiting findUsersCreatedByYear method with userDTOs: {}", userDTOs);
        return userDTOs;
    }
}