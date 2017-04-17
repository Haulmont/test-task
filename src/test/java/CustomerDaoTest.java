import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.Dao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by Cok on 17.04.2017.
 */
public class CustomerDaoTest {

    private Dao<Customer> customerHibernateDao;

    @Before
    public void setUp() throws Exception {
        this.customerHibernateDao = new CustomerHibernateDao();
        List<Customer> all = customerHibernateDao.findAll();
        all.forEach(customer -> customerHibernateDao.delete(customer));
    }

    @Test
    public void oneShouldBeAbleToPersistUserEntity() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        Long id = customerHibernateDao.create(customer);

        List<Customer> all = customerHibernateDao.findAll();
        Long inputId = all.get(0).getId();

        //check that id from create equals actual id from object
        assertEquals(id, inputId);

        Optional<Customer> optCustomer = customerHibernateDao.findById(inputId);

        Customer customerFromDB = optCustomer.get();
        //check customer mapping
        assertEquals("firstName", customerFromDB.getFirstName());
        assertEquals("lastName", customerFromDB.getLastName());
        assertEquals("thirdName", customerFromDB.getThirdName());
        assertEquals(Long.valueOf(89608150590L), customerFromDB.getPhone());

    }

    @Test
    public void oneShouldBeAbleGetAllUsersFromDB() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        Customer customer2 = new Customer();
        customer2.setFirstName("firstName2");
        customer2.setLastName("lastName2");
        customer2.setThirdName("thirdName2");
        customer2.setPhone(89608150592L);


        customerHibernateDao.create(customer);
        customerHibernateDao.create(customer2);

        List<Customer> all = customerHibernateDao.findAll();

        assertThat(all.size(), is(2));
    }

    @Test
    public void oneShouldBeAbleToDeleteUserEntity() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        Customer customer2 = new Customer();
        customer2.setFirstName("firstName2");
        customer2.setLastName("lastName2");
        customer2.setThirdName("thirdName2");
        customer2.setPhone(89608150592L);


        customerHibernateDao.create(customer);
        customerHibernateDao.create(customer2);

        List<Customer> all = customerHibernateDao.findAll();
        assertThat(all.size(), is(2));

        boolean successDeleted = customerHibernateDao.delete(customer2);
        assertTrue(successDeleted);


        assertThat(customerHibernateDao.findAll().size(), is(1));
    }
}
