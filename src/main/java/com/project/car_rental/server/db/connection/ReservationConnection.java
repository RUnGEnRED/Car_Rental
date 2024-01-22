package com.project.car_rental.server.db.connection;

import com.project.car_rental.server.db.models.Reservation;

import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * This class handles the database connection for reservations.
 */
public class ReservationConnection {

    private static final Logger logger = Logger.getLogger(ReservationConnection.class);

    /**
     * Reads the list of reservations from the database.
     *
     * @return The list of reservations.
     */
    public List<Reservation> readReservationList() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Reservation.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            List<Reservation> reservations = session.createNativeQuery(
                            "CALL GetReservations()", Reservation.class)
                    .getResultList();

            return reservations;
        } catch (Exception e) {
            logger.error("readReservationList catch exception");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new reservation in the database.
     *
     * @param reservation The reservation to create.
     */
    public void createReservation(Reservation reservation) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Reservation.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.persist(reservation);

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("createReservation catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Updates a reservation in the database.
     *
     * @param reservation The reservation to update.
     */
    public void updateReservation(Reservation reservation) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Reservation.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Reservation reservationToEdit = session.find(Reservation.class, reservation.getReservationID());
            if (reservationToEdit != null) {
                session.merge(reservation);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("updateReservation catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a reservation from the database.
     *
     * @param reservation The reservation to delete.
     */
    public void deleteReservation(Reservation reservation) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Reservation.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Reservation reservationToDelete = session.find(Reservation.class, reservation.getReservationID());
            if (reservationToDelete != null) {
                session.remove(reservationToDelete);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("deleteReservation catch exception");
            e.printStackTrace();
        }
    }
}
