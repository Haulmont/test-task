package com.haulmont.testtask.View;

/**
 * Represent main view of application
 * <p>It contains 2 options:</p>
 * <pre>
 *     - show orders
 *     - show clients
 * </pre>
 */
public interface MainView {

    /**
     * Show orders table
     * @return orders view
     */
    OrdersView showOrders();

    /**
     * Show clients table
     * @return clients view
     */
    ClientsView showClients();

    /**
     * Show main buttons to show orders and clients table
     */
    void show();
}
