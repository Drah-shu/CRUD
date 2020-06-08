package web.service;
import web.model.User;
import java.util.List;

public interface UserService {
    boolean add(User user);
    List<User> listUsers();
    User getUserById(long id);
    public void update(User user);
    public void delete(long id);
}
