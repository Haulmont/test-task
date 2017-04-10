package com.haulmont.testtask.Model.dao;

import com.haulmont.testtask.Model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * CustomerHibernateDao
 */
public class CustomerHibernateDao implements Dao<Customer> {

    private Exception lastException = null;

    private final EntityManager manager = Persistence
            .createEntityManagerFactory("org.hibernate.unit")
            .createEntityManager();

    @Override
    public Optional<Customer> findById(Long id) {
        Customer customer = manager.find(Customer.class, id);
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> findAll = manager.createNamedQuery("Customer.findAll", Customer.class);
        return findAll.getResultList();
    }

    @Override
    public Long create(Customer object) {
        try {
            manager.getTransaction().begin();
            object = manager.merge(object);
            manager.getTransaction().commit();
            return object.getID();
        } catch (Exception e) {
            lastException = e;
            throw e;
        }
    }

    @Override
    public boolean update(Customer object) {
        try {
            create(object);
            return true;
        } catch (Exception e) {
            lastException = e;
            return false;
        }
    }

    @Override
    public boolean delete(Customer object) {
        try {
            manager.getTransaction().begin();
            manager.remove(object);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            lastException = e;
            return false;
        }
    }

    @Override
    public Optional<Exception> getException() {
        return Optional.ofNullable(lastException);
    }
}
