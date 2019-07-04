import com.haulmont.testtask.Controller.CustomerController;
import com.haulmont.testtask.Controller.CustomerControllerImpl;
import com.haulmont.testtask.Event.AcceptDeleteEvent;
import com.haulmont.testtask.Event.SaveCustomerEvent;
import com.haulmont.testtask.Event.SaveNewCustomerEvent;
import com.haulmont.testtask.Event.ShowCustomerEvent;
import com.haulmont.testtask.MainEventBus;
import com.haulmont.testtask.MainEventBusImpl;
import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.Dao;
import com.haulmont.testtask.View.CustomerView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by Cok on 17.04.2017.
 */
public class CustomerControllerTest {
    private CustomerController customerController;
    private CustomerView mockCustomerView;
    private Dao<Customer> mockCustomerDao;
    private MainEventBus mainEventBus;

    @Before
    public void setUp() throws Exception {
        mockCustomerDao = mock(CustomerHibernateDao.class);
        mockCustomerView = mock(CustomerView.class);
        mainEventBus = MainEventBusImpl.getInstance();
        mainEventBus.register(this);
    }

    @Test
    public void controllerShouldCallViewToShowCustomersWhenEventIsComing() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", "89678180792");
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", "89678180792");
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", "89678180792");

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        this.customerController = new CustomerControllerImpl(mockCustomerDao, mockCustomerView, mainEventBus);

        mainEventBus.post(new ShowCustomerEvent());
        Mockito.verify(mockCustomerView, times(1)).show(Arrays.asList(cust1, cust2, cust3));
        Mockito.verify(mockCustomerDao, times(1)).findAll();
    }

    @Test
    public void controllerShouldCreateNewUserAndShowHimInTable() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", "89678180792");
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", "89678180792");
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", "89678180792");

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        this.customerController = new CustomerControllerImpl(mockCustomerDao, mockCustomerView, mainEventBus);

        mainEventBus.post(new SaveNewCustomerEvent(cust1));
        Mockito.verify(mockCustomerView, times(1)).show(Arrays.asList(cust1, cust2, cust3));
        Mockito.verify(mockCustomerDao, times(1)).create(cust1);
    }

    @Test
    public void controllerShouldUpdateCustomer() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", "89678180792");
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", "89678180792");
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", "89678180792");

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        this.customerController = new CustomerControllerImpl(mockCustomerDao, mockCustomerView, mainEventBus);

        mainEventBus.post(new SaveCustomerEvent(cust1));
        Mockito.verify(mockCustomerView, times(1)).show(Arrays.asList(cust1, cust2, cust3));
        Mockito.verify(mockCustomerDao, times(1)).update(cust1);
    }

    @Test
    public void controlerShouldDeleteUser() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", "89678180792");
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", "89678180792");
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", "89678180792");

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(true);

        this.customerController = new CustomerControllerImpl(mockCustomerDao, mockCustomerView, mainEventBus);

        mainEventBus.post(new AcceptDeleteEvent(cust1));
        Mockito.verify(mockCustomerView, times(1)).show(Arrays.asList(cust1, cust2, cust3));
        Mockito.verify(mockCustomerDao, times(1)).delete(cust1);
    }

    @Test
    public void controllerShouldShowDeleteErrorWhenCustomerHasOrders() throws Exception {
        Customer cust1 = new Customer(1L, "Nikita", "Sal", "Aleks", "89678180792");
        Customer cust2 = new Customer(2L, "Nikita2", "Sal", "Aleks", "89678180792");
        Customer cust3 = new Customer(3L, "Nikita3", "Sal", "Aleks", "89678180792");

        when(mockCustomerDao.findAll()).thenReturn(Arrays.asList(cust1, cust2, cust3));
        when(mockCustomerDao.findById(1L)).thenReturn(Optional.of(cust1));
        when(mockCustomerDao.create(cust1)).thenReturn(1L);
        when(mockCustomerDao.update(cust1)).thenReturn(true);
        when(mockCustomerDao.delete(cust1)).thenReturn(false);

        this.customerController = new CustomerControllerImpl(mockCustomerDao, mockCustomerView, mainEventBus);

        mainEventBus.post(new AcceptDeleteEvent(cust1));
        Mockito.verify(mockCustomerView, times(1)).show(Arrays.asList(cust1, cust2, cust3));
        Mockito.verify(mockCustomerDao, times(1)).delete(cust1);
        Mockito.verify(mockCustomerView, times(1)).showDeleteError();
    }
}
