package ua.rd.pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizza.domain.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;
import ua.rd.pizza.repository.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 25-Oct-16.
 */
public class SpringJPAAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext=new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext appContext=new ClassPathXmlApplicationContext(new String[]{"appContext.xml"},
                repoContext);

        PizzaRepository pizzaRepository= (PizzaRepository) appContext.getBean("pizzaRepository");
        Pizza pizza=new Pizza();
        pizza.setName("Sea");
        pizza.setType(Pizza.Type.SEA);
        pizza.setPrice(new BigDecimal("123.30"));
        //pizza=pizzaRepository.save(pizza);
//        Long id=pizzaRepository.save(pizza).getId();
//        pizza=pizzaRepository.find(id);

//        System.out.println(pizza);
//        Pizza pizza=pizzaRepository.find(1L);
        Customer customer=new Customer();
        customer.setName("Anton Pirozhkov");
        customer.setAddress("Umanskaya st. 2b");
        customer.setAccumulativeCard(new BigDecimal("0.00"));

        Map<Pizza,Integer> pizzas=new HashMap<>();
        pizzas.put(pizza,2);
        Order newOrder=new Order();
        //newOrder.setCustomer();

        EntityManagerFactory managerFactory= Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager=managerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(pizza);
        entityManager.persist(customer);
        //entityManager.persist(newOrder);
        entityTransaction.commit();
        entityManager.close();
        managerFactory.close();

        repoContext.close();
        appContext.close();
    }
}
