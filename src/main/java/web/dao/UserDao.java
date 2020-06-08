package web.dao;
import web.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUserById(Long id);
    public void update(User user);
    User getUserByName(String name);
    public void delete(Long id);
}