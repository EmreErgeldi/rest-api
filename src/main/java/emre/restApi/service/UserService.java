package emre.restApi.service;

import emre.restApi.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import emre.restApi.model.User;
import emre.restApi.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(String userName) {
        if (userName == null) {
            return userRepository.findAll();
        } else{
            return userRepository.findByUserName(userName);
        }
    }

    public User createUser(User newUser) {
        setUserID(newUser);
        return userRepository.save(newUser);
    }

    public void deleteUSer(String id) {
        userRepository.deleteById(id);
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public void setUserID(User user){
        user.setId(String.valueOf(userRepository.count() + 1));
    }

    public void updateUser(String id, User user) {
        User oldUser = getUserById(id);
        oldUser.setUserName(user.getUserName());
        oldUser.setLast_Name(user.getLast_Name());

        userRepository.save(oldUser);
    }
}

