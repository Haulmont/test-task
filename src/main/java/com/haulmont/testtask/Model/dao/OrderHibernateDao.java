package com.haulmont.testtask.Model.dao;

import com.haulmont.testtask.Model.Order;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by Cok on 09.04.2017.
 */
public class OrderHibernateDao implements Dao<Order> {

    private Exception lastException = null;

    private final EntityManager manager = Persistence
            .createEntityManagerFactory("org.hibernate.unit")
            .createEntityManager();

    @Override
    public Optional<Order> findById(Long id) {
        Order order = manager.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> findAll = manager.createNamedQuery("Order.findAll", Order.class);
        return findAll.getResultList();
    }

    @Override
    public Long create(Order object) {
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
    public boolean update(Order object) {
        try {
            create(object);
            return true;
        } catch (Exception e) {
            lastException = e;
            return false;
        }
    }

    @Override
    public boolean delete(Order object) {
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
