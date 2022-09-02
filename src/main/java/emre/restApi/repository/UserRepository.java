package emre.restApi.repository;

import emre.restApi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUserName(String userName);

}
