package web.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dto.UserDto;
import web.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }


    @Override
    public boolean add(UserDto userDto) {
        User user = new User(userDto);
        user.setRoles(Arrays.stream(userDto.getRoles()).map(roleService::getRole).collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        return true;
    }


    @Override
    public List<UserDto> listUsers() {
        return userDao.listUsers();
    }


    @Override
    public UserDto getUserById(long id) {
        User user = userDao.getUserById(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDto userDto = new UserDto(user);
        return userDto;
    }


    @Override
    public void update(UserDto userDto) {
        User user = new User(userDto);
        user.setRoles(Arrays.stream(userDto.getRoles()).map(roleService::getRole).collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
    }


    @Override
    public void delete(long id) {
        userDao.delete(id);
    }
}
