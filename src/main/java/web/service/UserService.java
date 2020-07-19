package web.service;
import web.dto.UserDto;
import web.model.User;
import java.util.List;

public interface UserService {
    boolean add(UserDto user);
    List<UserDto> listUsers();
    UserDto getUserById(long id);
    public void update(UserDto user);
    public void delete(long id);
}
