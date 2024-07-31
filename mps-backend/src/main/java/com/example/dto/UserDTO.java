package com.example.dto;

import com.example.entity.Users;

public class UserDTO {
    private Long id;
    private String name;
    private String telno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public UserDTO setDto(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setTelno(user.getTelno());
        userDTO.setName(user.getName());

        return userDTO;
    }
}
