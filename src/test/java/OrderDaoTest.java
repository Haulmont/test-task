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
}
