package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import web.dto.UserDto;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestControllerApi {


    private UserService userService;

    @Autowired
    public RestControllerApi(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDto user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> read() {
        List<UserDto> userList = userService.listUsers();

        return !userList.isEmpty() || userList != null ?
                new ResponseEntity<>(userList,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> read(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUserById(id);

        return  userDto != null ?
                new ResponseEntity<>(userDto,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/auth")
    public ResponseEntity<UserDto> getAuthUser(Authentication auth) {
        UserDto user = new UserDto((User) auth.getPrincipal());

        return  user != null ?
                new ResponseEntity<>(user,HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDto user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }







}
