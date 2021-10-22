package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from users", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.saveOrUpdate(user);
    }

    @Override
    public User getUser(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        User user = session.get(User.class, id);
//        return user;
        return null;
    }

    @Override
    public void deleteUser(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("delete from User where id = :userId");
//        query.setParameter("userId", id);
//        query.executeUpdate();
    }
}
