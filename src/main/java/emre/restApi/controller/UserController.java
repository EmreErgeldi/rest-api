package emre.restApi.controller;

import emre.restApi.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import emre.restApi.model.User;
import emre.restApi.service.UserService;

import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String userName, HttpServletResponse response){
        response.addHeader("access-control-allow-methods", "GET, PUSH, PUT, OPTIONS, DELETE");
        response.addHeader("access-control-allow-headers", "Content-Type");
        response.addHeader("access-control-allow-origin", "*");
        return new ResponseEntity<>(userService.getUsers(userName), OK);
    }

    /*@RequestMapping(method = RequestMethod.OPTIONS, value="/**")
    public String manageOptions(HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Methods", "GET, PUSH, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000/");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        response.setHeader("Access-Control-Expose-Headers", "*");
        return "Response with header using HttpServletResponse";
    }*/ // Calismiyo

    // Get one user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id, HttpServletResponse response){
        response.addHeader("access-control-allow-methods", "GET, PUSH, PUT, OPTIONS, DELETE");
        response.addHeader("access-control-allow-headers", "Content-Type");
        response.addHeader("access-control-allow-origin", "*");
        return new ResponseEntity<>(getUserById(id), OK);
    }

    // Create new User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser, HttpServletResponse response){
        response.addHeader("access-control-allow-methods", "GET, PUSH, PUT, OPTIONS, DELETE");
        response.addHeader("access-control-allow-headers", "Content-Type");
        response.addHeader("access-control-allow-origin", "*");
        return new ResponseEntity<>(userService.createUser(newUser), CREATED);
    }

    // Update user by Id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User newUser, @PathVariable String id, HttpServletResponse response){
        userService.updateUser(id, newUser);
        response.addHeader("access-control-allow-methods", "GET, PUSH, PUT, OPTIONS, DELETE");
        response.addHeader("access-control-allow-headers", "Content-Type");
        response.addHeader("access-control-allow-origin", "*");
        return new ResponseEntity<>(OK);
    }

    //Delete User by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id, HttpServletResponse response){
        userService.deleteUSer(id);
        response.addHeader("access-control-allow-methods", "GET, PUSH, PUT, OPTIONS, DELETE");
        response.addHeader("access-control-allow-headers", "Content-Type");
        response.addHeader("access-control-allow-origin", "*");
        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
}
