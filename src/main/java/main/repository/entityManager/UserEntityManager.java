package main.repository.entityManager;

import main.entity.Bill;
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

    public void saveBill(Bill bill) {
        String queryStr = "INSERT INTO bill (id_staff, id_water_money, sum_money) VALUES (:idStaff, :idWaterMoney, :sumMoney)";
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter("idStaff", bill.getIdStaff());
            query.setParameter("idWaterMoney", bill.getIdWaterMoney());
            query.setParameter("sumMoney", bill.getSumMoney());
            query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
