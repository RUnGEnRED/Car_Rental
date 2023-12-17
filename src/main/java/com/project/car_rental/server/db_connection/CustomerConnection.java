package com.project.car_rental.server.db_connection;

import com.project.car_rental.server.db_models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CustomerConnection {

    public List getCustomerTable() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Customer.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            return session.createQuery("from Customer", Customer.class).list();
        }
    }
}