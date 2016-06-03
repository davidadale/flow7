package users.dao;


import com.google.inject.Inject;
import com.google.inject.Provider;
import content.models.Domain;
import models.Article;
import users.models.User;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {

    @Inject
    Provider<EntityManager> entityManagerProvider;


    public List<User> retrieveUsers( Domain domain ){

        EntityManager entityManager = entityManagerProvider.get();

        TypedQuery<User> query= entityManager.createQuery("SELECT x FROM users.models.User x", User.class);
        query.setFirstResult( 0 );
        query.setMaxResults( 10 );
        return  query.getResultList();

    }

    public User retrieve(Long id){

        EntityManager entityManager = entityManagerProvider.get();
        Query q = entityManager.createQuery("SELECT x FROM DemoUser x WHERE x.id = :idParam");
        return  (User) q.setParameter("idParam", id).getSingleResult();

    }


    public void save(User user ){
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.persist( user );
    }

    public void delete(Long id ){
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.remove( entityManager.find( User.class, id ) );
    }


}
