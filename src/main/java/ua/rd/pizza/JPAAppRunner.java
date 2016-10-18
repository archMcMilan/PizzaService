package ua.rd.pizza;

import ua.rd.pizza.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 18-Oct-16.
 */
public class JPAAppRunner {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager=managerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();

        Pizza pizza=new Pizza(2L,"Bavarian",new BigDecimal("200.00"),Pizza.Type.MEAT);

        entityTransaction.begin();
        entityManager.persist(pizza);
        entityTransaction.commit();

        Pizza pizza1=entityManager.find(Pizza.class,2L);
        System.out.println(pizza1==pizza);
        System.out.println(pizza1);
        entityManager.close();
        managerFactory.close();
        //entityManager.find()
    }
}
