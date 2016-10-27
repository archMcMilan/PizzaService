package ua.rd.pizza.repository;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:repoContextForH2.xml"})
@Rollback(false)
public class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests {

}
