package emre.restApi.controller;

import emre.restApi.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import emre.restApi.model.User;
import emre.restApi.service.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Getting User by Id used at getUser() and updateUser()
    private User getUserById(String id) {
        return userService.getUserById(id);
    }

    // Get all Users
    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String userName){
        return new ResponseEntity<>(userService.getUsers(userName), OK);
    }

    // Get one user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        return new ResponseEntity<>(getUserById(id), OK);
    }

    // Create new User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        return new ResponseEntity<>(userService.createUser(newUser), CREATED);
    }

    // Update user by Id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User newUser, @PathVariable String id){
        userService.updateUser(id, newUser);
        return new ResponseEntity<>(OK);
    }

    //Delete User by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUSer(id);
        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
}
