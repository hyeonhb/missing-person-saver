package com.example.controller;

import com.example.dto.ChatRoomDTO;
import com.example.dto.UserDTO;
import com.example.entity.MissingPerson;
import com.example.entity.Users;
import com.example.service.ChatRoomService;
import com.example.service.MissingPersonService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MissingPersonService missingPersonService;

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/home")
    public String home(@RequestParam("test") String test) {
        return test; // admin_home.jsp
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/one")
    public Users getUser(@RequestBody Map userMap) { return userService.getUser(userMap); }

    @PostMapping("/login")
    public UUID getUser(@RequestParam Long mpId, @RequestBody Map userMap) {
        Users user = userService.getUser(userMap);
        MissingPerson missingPerson = missingPersonService.getMissingPerson(mpId);

        return chatRoomService.getChatRoom(user, missingPerson);
    }
}
