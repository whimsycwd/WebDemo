import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import demo.whimsy.entity.User;
import demo.whimsy.service.RegisterMgr;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by whimsy on 1/30/15.
 */


@ContextConfiguration(locations = "classpath:applicationContext-test-MYSQL.xml")
@TransactionConfiguration(transactionManager = "transactionManagerTest", defaultRollback = true)
public class RegisterTest extends AbstractTransactionalTestNGSpringContextTests {


    @Autowired
    RegisterMgr registerMgr;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("David");

        registerMgr.save(user);

    }

    @Test
    public void listAll() {
        List<User> list = registerMgr.list();

        assertThat(list.size()).isEqualTo(2);
    }
}
