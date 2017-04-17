import com.haulmont.testtask.Controller.OrderController;
import com.haulmont.testtask.Controller.OrdersControllerImpl;
import com.haulmont.testtask.Event.FilterOrderEvent;
import com.haulmont.testtask.Event.ShowOrderEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.MainEventBusImpl;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.Model.dao.OrderDao;
import com.haulmont.testtask.Model.dao.OrderHibernateDao;
import com.haulmont.testtask.View.OrdersView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

/**
 * Created by Cok on 18.04.2017.
 */
public class OrderControllerTest {
    private Dao<Customer> mockCustomerDao;
    private OrderDao mockOrderDao;
    private OrdersView mockOrderView;
    private MainEventBus mainEventBus;
    private OrderController orderController;

    @Before
    public void setUp() throws Exception {
        mockCustomerDao = mock(CustomerHibernateDao.class);
        mockOrderDao = mock(OrderHibernateDao.class);
        mockOrderView = mock(OrdersView.class);
        mainEventBus = MainEventBusImpl.getInstance();
        mainEventBus.register(this);
    }

    @Test
    public void controllerShouldShowOrdersByShowEvent() throws Exception {

        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", 89608150590L);
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", 89608150590L);
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", 89608150590L);

        Order order = new Order("testDesc", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order2 = new Order("testDesc2", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order3 = new Order("testDesc3", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        when(mockOrderDao.findAll()).thenReturn(Arrays.asList(order, order2, order3));
        when(mockOrderDao.findById(1L)).thenReturn(Optional.of(order));
        when(mockOrderDao.create(order)).thenReturn(1L);
        when(mockOrderDao.update(order)).thenReturn(true);
        when(mockOrderDao.delete(order)).thenReturn(true);

        orderController = new OrdersControllerImpl(mockOrderDao, mockCustomerDao, mockOrderView, mainEventBus);

        mainEventBus.post(new ShowOrderEvent());

        Mockito.verify(mockOrderDao, times(1)).findAll();
        Mockito.verify(mockOrderView, times(1)).show(Arrays.asList(order, order2, order3));

    }

    @Test
    public void controllerShouldShowFilteredOrdersByCustomerFilter() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", 89608150590L);
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", 89608150590L);
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", 89608150590L);

        Order order = new Order("testDesc", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order2 = new Order("testDesc2", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order3 = new Order("testDesc3", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        when(mockOrderDao.findAll()).thenReturn(Arrays.asList(order, order2, order3));
        when(mockOrderDao.findById(1L)).thenReturn(Optional.of(order));
        when(mockOrderDao.create(order)).thenReturn(1L);
        when(mockOrderDao.update(order)).thenReturn(true);
        when(mockOrderDao.delete(order)).thenReturn(true);
        when(mockOrderDao.findByCustomerId(cust1)).thenReturn(singletonList(order));

        orderController = new OrdersControllerImpl(mockOrderDao, mockCustomerDao, mockOrderView, mainEventBus);

        mainEventBus.post(new FilterOrderEvent(cust1, "", null));

        Mockito.verify(mockOrderDao, times(1)).findByCustomerId(cust1);
        Mockito.verify(mockOrderView, times(1)).show(singletonList(order));
    }

    @Test
    public void controllerShouldShowFilteredOrdersByDescriptionFilter() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", 89608150590L);
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", 89608150590L);
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", 89608150590L);

        Order order = new Order("testDesc", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order2 = new Order("mescDesc2", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order3 = new Order("destDesc3", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        when(mockOrderDao.findAll()).thenReturn(Arrays.asList(order, order2, order3));
        when(mockOrderDao.findById(1L)).thenReturn(Optional.of(order));
        when(mockOrderDao.create(order)).thenReturn(1L);
        when(mockOrderDao.update(order)).thenReturn(true);
        when(mockOrderDao.delete(order)).thenReturn(true);
        when(mockOrderDao.findByCustomerId(cust1)).thenReturn(singletonList(order));

        orderController = new OrdersControllerImpl(mockOrderDao, mockCustomerDao, mockOrderView, mainEventBus);

        mainEventBus.post(new FilterOrderEvent(null, "test", null));

        Mockito.verify(mockOrderDao, times(0)).findByCustomerId(cust1);
        Mockito.verify(mockOrderView, times(1)).show(singletonList(order));
    }

    @Test
    public void controllerShouldShowFilteredOrdersByStatusFilter() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", 89608150590L);
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", 89608150590L);
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", 89608150590L);

        Order order = new Order("testDesc", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order2 = new Order("mescDesc2", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.DONE);
        Order order3 = new Order("destDesc3", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.ACCEPTED);

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        when(mockOrderDao.findAll()).thenReturn(Arrays.asList(order, order2, order3));
        when(mockOrderDao.findById(1L)).thenReturn(Optional.of(order));
        when(mockOrderDao.create(order)).thenReturn(1L);
        when(mockOrderDao.update(order)).thenReturn(true);
        when(mockOrderDao.delete(order)).thenReturn(true);
        when(mockOrderDao.findByCustomerId(cust1)).thenReturn(singletonList(order));

        orderController = new OrdersControllerImpl(mockOrderDao, mockCustomerDao, mockOrderView, mainEventBus);

        mainEventBus.post(new FilterOrderEvent(null, "", OrderStatus.ACCEPTED));

        Mockito.verify(mockOrderDao, times(0)).findByCustomerId(cust1);
        Mockito.verify(mockOrderView, times(1)).show(singletonList(order3));
    }

    @Test
    public void controllerShouldShowAllOrdersByNoneFilter() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", 89608150590L);
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", 89608150590L);
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", 89608150590L);

        Order order = new Order("testDesc", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
        Order order2 = new Order("mescDesc2", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.DONE);
        Order order3 = new Order("destDesc3", cust1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.ACCEPTED);

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        when(mockOrderDao.findAll()).thenReturn(Arrays.asList(order, order2, order3));
        when(mockOrderDao.findById(1L)).thenReturn(Optional.of(order));
        when(mockOrderDao.create(order)).thenReturn(1L);
        when(mockOrderDao.update(order)).thenReturn(true);
        when(mockOrderDao.delete(order)).thenReturn(true);
        when(mockOrderDao.findByCustomerId(cust1)).thenReturn(singletonList(order));

        orderController = new OrdersControllerImpl(mockOrderDao, mockCustomerDao, mockOrderView, mainEventBus);

        //set up none filter
        mainEventBus.post(new FilterOrderEvent(null, "", null));

        Mockito.verify(mockOrderDao, times(0)).findByCustomerId(cust1);
        Mockito.verify(mockOrderView, times(1)).show(Arrays.asList(order, order2, order3));
    }
}
