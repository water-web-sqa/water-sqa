package main.repository.entityManager;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> getUser(Long id) {
        String queryStr = "Select * from user where user.id = : iduser";
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter("iduser", id);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
