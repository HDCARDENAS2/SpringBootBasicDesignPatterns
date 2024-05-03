package com.learn.desingpatterns.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.desingpatterns.dto.UserDTO;
import com.learn.desingpatterns.entity.UserEntity;
import com.learn.desingpatterns.event.UserCreatedEvent;
import com.learn.desingpatterns.mapper.UserMapper;
import com.learn.desingpatterns.repository.UserRepository;
import com.learn.desingpatterns.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service("userService")
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {
	
    private final UserMapper userMapper;
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final JmsMessagingTemplate jmsMessagingTemplate;
    @Value("${queue.new.user}")
    private String newUserQueue;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDTO save(UserDTO userDTO) throws Exception {
        log.info("Entering save method with userDTO: {}", userDTO);
        UserEntity userEntity = userMapper.toEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserDTO savedUserDTO = userMapper.toDTO(savedUserEntity);
        jmsMessagingTemplate.convertAndSend(newUserQueue, savedUserDTO.getId());
        eventPublisher.publishEvent(new UserCreatedEvent(this, savedUserDTO));
        log.info("Exiting save method with savedUserDTO: {}", savedUserDTO);
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
    
    @Override
    public List<UserDTO> findUsersCreatedByYear(Integer year) {
        log.info("Entering findUsersCreatedByYear method with year: {}", year);
        List<UserEntity> usersCreatedByYear = userRepository.findUsersCreatedByYear(year);
        List<UserDTO> userDTOs = userMapper.toDTOList(usersCreatedByYear);
        log.info("Exiting findUsersCreatedByYear method with userDTOs: {}", userDTOs);
        return userDTOs;
    }
}