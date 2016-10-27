package ua.rd.pizza.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizza.domain.Pizza;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class JPAPizzaRepositoryIT extends RepositoryTestConfig{

    @Autowired
    private PizzaRepository pizzaRepository;

    @Ignore
    @Test
    public void find() throws Exception {
    }

//    @Rollback
    @Test
    public void saveTest() throws Exception {
        Pizza pizza=new Pizza();
        pizza.setName("Sea");
        pizza.setType(Pizza.Type.SEA);
        pizza.setPrice(new BigDecimal("123.30"));
        pizza=pizzaRepository.save(pizza);
        assertNotNull(pizza.getId());
    }

}