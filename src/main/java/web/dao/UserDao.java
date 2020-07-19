package web.dao;
import web.dto.UserDto;
import web.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);
    List<UserDto> listUsers();
    User getUserById(Long id);
    void update(User user);
    User getUserByName(String name);
    void delete(Long id);
    User getUserByEmail(String email);
}