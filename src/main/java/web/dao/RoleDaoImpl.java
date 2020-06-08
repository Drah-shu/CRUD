package web.dao;
import org.springframework.stereotype.Repository;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRole(String name) {
        Role role = (Role) entityManager
                .createQuery("select r from Role r where name = :name")
                .setParameter("name",name).getResultList().get(0);
        return role;
    }


    /*
    private Session session;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRole(String name) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Role.class);
        transaction.commit();
        Role role = ((Role) criteria.add(Restrictions.and(Restrictions.eq("name" , name))).uniqueResult());
        session.close();
        return role;
    }

     */


}
