import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
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

/**
 * Created by Cok on 17.04.2017.
 */
public class OrderDaoTest {
    private OrderDao dao;
    private CustomerHibernateDao customerDao;

    @Before
    public void setUp() throws Exception {
        this.dao = new OrderHibernateDao();
        this.customerDao = new CustomerHibernateDao();
        List<Order> all = dao.findAll();
        all.forEach(order -> dao.delete(order));
    }

    @Test
    public void oneShouldBeAbleToPersistOrderEntity() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("testUser");
        customerDao.create(customer);

        Order order = new Order("testDesc", customer, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        order.setCloseDate(new Date(System.currentTimeMillis()));
        Long id = dao.create(order);

        List<Order> all = dao.findAll();
        Long inputId = all.get(0).getId();

        //check input id from db
        assertEquals(id, inputId);

        Optional<Order> optOrder = dao.findById(inputId);

        Order order2 = optOrder.get();

        //check mapping
        assertEquals(order.getDescription(), order2.getDescription());
        assertEquals(order.getCreateDate(), order2.getCreateDate());
        assertEquals(order.getCustomer().getFirstName(), order2.getCustomer().getFirstName());
        assertEquals(order.getCost(), order2.getCost());
        assertEquals(order.getStatus(), order2.getStatus());
        assertEquals(order.getCloseDate(), order2.getCloseDate());

    }

    @Test
    public void oneShouldBeAbleToUpdateOrderEntity() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("testUser");
        customerDao.create(customer);

        Order order = new Order("testDesc", customer, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        order.setCloseDate(new Date(System.currentTimeMillis()));
        Long id = dao.create(order);

        Optional<Order> optOrder = dao.findById(id);

        Order order2 = optOrder.get();
        order2.setDescription("testDesc2");
        order2.setCreateDate(new Date(System.currentTimeMillis()));
        order2.setCost(0.1D);
        order2.setStatus(OrderStatus.ACCEPTED);
        order2.setCloseDate(new Date(System.currentTimeMillis()));

        boolean suceessUpdate = dao.update(order2);
        assertTrue(suceessUpdate);

        Optional<Order> updatedOptional = dao.findById(id);
        Order updatedOrder = updatedOptional.get();

        //check mapping
        assertEquals(order2.getDescription(), updatedOrder.getDescription());
        assertEquals(order2.getCreateDate(), updatedOrder.getCreateDate());
        assertEquals(order2.getCustomer().getFirstName(), updatedOrder.getCustomer().getFirstName());
        assertEquals(order2.getCost(), updatedOrder.getCost());
        assertEquals(order2.getStatus(), updatedOrder.getStatus());
        assertEquals(order2.getCloseDate(), updatedOrder.getCloseDate());

    }

    @Test
    public void oneShouldBeAbleToGetAllOrdersEntities() throws Exception {

        //prepare test data
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        customerDao.create(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreateDate(new Date(System.currentTimeMillis()));
        order.setDescription("testDesc2");
        order.setCost(0.1D);
        order.setStatus(OrderStatus.ACCEPTED);

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setCreateDate(new Date(System.currentTimeMillis()));
        order2.setDescription("testDesc3");
        order2.setCost(0.1D);
        order2.setStatus(OrderStatus.ACCEPTED);

        dao.create(order);
        dao.create(order2);

        List<Order> all = dao.findAll();

        assertThat(all.size(), is(2));

    }

    @Test
    public void oneShouldBeAbleToDeleteOrderEntity() throws Exception {

        assertThat("Precondition test", dao.findAll().size(), is(0));
        //prepare test data
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        customerDao.create(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreateDate(new Date(System.currentTimeMillis()));
        order.setDescription("testDesc2");
        order.setCost(0.1D);
        order.setStatus(OrderStatus.ACCEPTED);

        Long id = dao.create(order);

        Optional<Order> optOrder = dao.findById(id);
        dao.delete(optOrder.get());

        assertThat("Dao did not delete entity", dao.findAll().size(), is(0));
    }

    @Test
    public void oneShouldBeAbleToGetOrderByCustomerId() throws Exception {
        assertThat("Precondition test", dao.findAll().size(), is(0));
        //prepare test data
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setThirdName("thirdName");
        customer.setPhone(89608150590L);

        Long custId = customerDao.create(customer);

        Order order = new Order();
        order.setCustomer(customer);
        order.setCreateDate(new Date(System.currentTimeMillis()));
        order.setDescription("testDesc2");
        order.setCost(0.1D);
        order.setStatus(OrderStatus.ACCEPTED);

        Long id = dao.create(order);

        List<Order> orders = dao.findByCustomerId(customer);
        assertThat(orders.size(), is(1));
        assertThat(orders.get(0).getId(), is(id));

    }
}
