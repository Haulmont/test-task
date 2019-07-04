package com.haulmont.testtask;

import com.haulmont.testtask.Model.Customer;
import com.haulmont.testtask.Model.Order;
import com.haulmont.testtask.Model.OrderStatus;
import com.haulmont.testtask.Model.dao.CustomerHibernateDao;
import com.haulmont.testtask.Model.dao.OrderHibernateDao;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Cok on 17.04.2017.
 */
public class TestUtil {


    private static TestUtil instance = new TestUtil(true);
    private boolean firstTime;

    private TestUtil(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public static TestUtil getInstance() {
        return instance;
    }

    public void loadTestData() {

        if (firstTime) {
            firstTime = false;
            CustomerHibernateDao customerDao = new CustomerHibernateDao();
            OrderHibernateDao orderDao = new OrderHibernateDao();

            Customer a1 = new Customer();
            a1.setFirstName("Первый клиент");
            a1.setLastName("Первый клиентовый");
            a1.setThirdName("Первый клиентович");
            a1.setPhone("89678155687");
            Customer a2 = new Customer();
            a2.setFirstName("Второй");
            a2.setLastName("Второвый");
            a2.setThirdName("Вторович");
            a2.setPhone("+78652347654");
            Customer a3 = new Customer();
            a3.setFirstName("Третий");
            a3.setLastName("Третивой");
            a3.setThirdName("Третивович");
            a3.setPhone("+78945678745");
            Customer a4 = new Customer();
            a4.setFirstName("Fifth");
            a4.setLastName("Fifthov");
            a4.setThirdName("Fifthovich");
            a4.setPhone("+75556667865");

            Arrays.asList(a1, a2, a3, a4).forEach(customerDao::create);

            Order b1 = new Order("Починить дверь", a1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
            Order b2 = new Order("Поменять масло", a2, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.DONE);
            Order b3 = new Order("Осмотр ходовой", a1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.ACCEPTED);
            Order b4 = new Order("Замена чего-то", a2, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);

            Arrays.asList(b1, b2, b3, b4).forEach(orderDao::create);
        }

    }
}
