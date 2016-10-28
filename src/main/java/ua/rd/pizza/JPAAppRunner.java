package ua.rd.pizza;

import ua.rd.pizza.domain.customer.Customer;
import ua.rd.pizza.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 18-Oct-16.
 */
public class JPAAppRunner {
    public static void main(String[] args) {
//        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("jpa");
//        EntityManager entityManager=managerFactory.createEntityManager();
//        EntityTransaction entityTransaction=entityManager.getTransaction();
//
//        Pizza pizza=new Pizza(4L,"Bavarian",new BigDecimal("220.00"),Pizza.Type.MEAT);
//        Customer customer=new Customer(1L,"Artur Pirozhkov","Umanskaya st.");
//        Map<Pizza,Integer> pizzas=new HashMap<>();
//        pizzas.put(pizza,2);
//        //Order newOrder=new Order(customer,pizzas);
//
//        entityTransaction.begin();
//        entityManager.persist(pizza);
//        entityManager.persist(customer);
//        //entityManager.persist(newOrder);
//        entityTransaction.commit();
//
//       /* Pizza pizza1=entityManager.find(Pizza.class,2L);
//        System.out.println(pizza1==pizza);
//        System.out.println(pizza1);*/
//
//        entityManager.close();
//        managerFactory.close();
//        //entityManager.find()
    }
}
