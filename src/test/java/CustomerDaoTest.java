import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.Model.dao.OrderDao;
import com.haulmont.testtask.Model.dao.OrderHibernateDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Cok on 17.04.2017.
 */
public class CustomerDaoTest {

    private Dao<Customer> customerHibernateDao;
    private OrderDao orderDao;

    @Before
    public void setUp() throws Exception {
        this.customerHibernateDao = new CustomerHibernateDao();
        this.orderDao = new OrderHibernateDao();
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
    public void oneShouldBeAbleToUpdateUserEntity() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        Long id = customerHibernateDao.create(customer);

        Optional<Customer> optCustomer = customerHibernateDao.findById(id);

        Customer customerFromDB = optCustomer.get();
        customerFromDB.setFirstName("firstName2");
        customerFromDB.setLastName("lastName2");
        customerFromDB.setThirdName("thirdName2");
        customerFromDB.setPhone(111L);

        boolean successUpdate = customerHibernateDao.update(customerFromDB);
        assertTrue(successUpdate);

        //check customer mapping
        assertEquals("firstName2", customerFromDB.getFirstName());
        assertEquals("lastName2", customerFromDB.getLastName());
        assertEquals("thirdName2", customerFromDB.getThirdName());
        assertEquals(Long.valueOf(111L), customerFromDB.getPhone());

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

    @Test
    public void oneShouldReturnFalseWhenUserHasOrder() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        customerHibernateDao.create(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreateDate(new Date(System.currentTimeMillis()));
        order.setDescription("testDesc2");
        order.setCost(0.1D);
        order.setStatus(OrderStatus.ACCEPTED);

        orderDao.create(order);

        boolean successDeleted = customerHibernateDao.delete(customer);
        assertFalse(successDeleted);

    }
}
