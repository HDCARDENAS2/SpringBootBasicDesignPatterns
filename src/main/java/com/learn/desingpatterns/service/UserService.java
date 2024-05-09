package com.learn.desingpatterns.service;

import java.util.List;

import com.learn.desingpatterns.dto.UserDTO;

public interface UserService {
    UserDTO save(UserDTO userDTO) throws Exception;
    UserDTO findById(Integer id);
    List<UserDTO> findAll();
    List<UserDTO> findUsersCreatedToday();

    List<UserDTO> findUsersCreatedByYear(Integer year);
}