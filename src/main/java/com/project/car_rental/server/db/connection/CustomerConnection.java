package com.project.car_rental.server.db.connection;

import com.project.car_rental.server.client.connection.ConnectionHandler;
import com.project.car_rental.server.db.models.Customer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

/**
 * CustomerConnection is a class that handles the database operations for the Customer model.
 */
public class CustomerConnection {

    private static final Logger logger = Logger.getLogger(ConnectionHandler.class);

    /**
     * Reads the customer list from the database.
     *
     * @return The list of customers.
     */
    public List<Customer> readCustomerList() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Customer.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            return session.createQuery("from Customer", Customer.class).list();
        } catch (Exception e) {
            logger.error("readCustomerList catch exception");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new customer in the database.
     *
     * @param customer The customer to be created.
     */
    public void createCustomer(Customer customer) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Customer.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.persist(customer);

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("createCustomer catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Updates a customer in the database.
     *
     * @param customer The customer to be updated.
     */
    public void updateCustomer(Customer customer) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Customer.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Customer customerToEdit = session.find(Customer.class, customer.getCustomerID());
            if (customerToEdit != null) {
                session.merge(customer);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("updateCustomer catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a customer from the database.
     *
     * @param customer The customer to be deleted.
     */
    public void deleteCustomer(Customer customer) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Customer.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Customer customerToDelete = session.find(Customer.class, customer.getCustomerID());
            if (customerToDelete != null) {
                session.remove(customerToDelete);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("deleteCustomer catch exception");
            e.printStackTrace();
        }
    }
}
