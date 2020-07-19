package web.dao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.dto.UserDto;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDto> listUsers() {
        List<User> userList = entityManager.createQuery("From User").getResultList();
        List<UserDto> userDtoList = userList
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void add(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.save(user);

    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);

    }

    @Override
    public User getUserById(Long id) {
        User user = entityManager.find(User.class,id);
        return user;
    }


    @Override
    public User getUserByName(String name) {
        User user = (User) entityManager
                .createQuery("select u from User u where name = :name")
                .setParameter("name",name).getSingleResult();

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = (User) entityManager
                .createQuery("from User where email = :email",User.class)
                .setParameter("email",email).getSingleResult();
        return user;
    }




        /*
    private Session session;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> listUsers() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public User getUserByName(String name) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        transaction.commit();
        User user = ((User) criteria.add(Restrictions.and(Restrictions.eq("name" , name))).uniqueResult());
        session.close();
        return user;
    }

    @Override
    public User getUserById(Long id){
        User user = (User) session.get(User.class, id);
        return user;
    }


    @Override
    public void add(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(getUserById(id));
        transaction.commit();
        session.close();
    }


         */



}
