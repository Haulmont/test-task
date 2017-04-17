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
            a1.setFirstName("Никита");
            a1.setLastName("Саломатин");
            a1.setThirdName("Александрович");
            a1.setPhone(89608150590L);
            Customer a2 = new Customer();
            a2.setFirstName("Ник");
            a2.setLastName("Саломатин");
            a2.setThirdName("Александрович");
            a2.setPhone(89608150590L);
            Customer a3 = new Customer();
            a3.setFirstName("Click");
            a3.setLastName("Саломатин");
            a3.setThirdName("Александрович");
            a3.setPhone(89608150590L);
            Customer a4 = new Customer();
            a4.setFirstName("Fip");
            a4.setLastName("Александрович");
            a4.setThirdName("Александрович");
            a4.setPhone(89608150590L);

            Arrays.asList(a1, a2, a3, a4).forEach(customerDao::create);

            Order b1 = new Order("Движок", a1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);
            Order b2 = new Order("Коробка", a2, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.DONE);
            Order b3 = new Order("Ступица", a1, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.ACCEPTED);
            Order b4 = new Order("Граната", a2, new Date(System.currentTimeMillis()), 0.0d, OrderStatus.PLANNED);

            Arrays.asList(b1, b2, b3, b4).forEach(orderDao::create);
        }

    }
}
