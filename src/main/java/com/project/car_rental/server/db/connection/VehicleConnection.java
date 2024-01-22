package com.project.car_rental.server.db.connection;

import com.project.car_rental.server.db.models.Vehicle;

import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class for handling database operations related to vehicles in the Car Rental application.
 */
public class VehicleConnection {

    private static final Logger logger = Logger.getLogger(VehicleConnection.class);

    /**
     * Reads the list of vehicles from the database.
     *
     * @return The list of vehicles.
     */
    public List<Vehicle> readVehicleList() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Vehicle.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            return session.createQuery("from Vehicle", Vehicle.class).list();
        } catch (Exception e) {
            logger.error("readVehicleList catch exception");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new vehicle in the database.
     *
     * @param vehicle The vehicle to create.
     */
    public void createVehicle(Vehicle vehicle) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Vehicle.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            session.persist(vehicle);

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("createVehicle catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Updates a vehicle in the database.
     *
     * @param vehicle The vehicle to update.
     */
    public void updateVehicle(Vehicle vehicle) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Vehicle.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Vehicle vehicleToEdit = session.find(Vehicle.class, vehicle.getVehicleID());
            if (vehicleToEdit != null) {
                session.merge(vehicle);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("updateVehicle catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a vehicle from the database.
     *
     * @param vehicle The vehicle to delete.
     */
    public void deleteVehicle(Vehicle vehicle) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Vehicle.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Vehicle vehicleToDelete = session.find(Vehicle.class, vehicle.getVehicleID());
            if (vehicleToDelete != null) {
                session.remove(vehicleToDelete);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("deleteVehicle catch exception");
            e.printStackTrace();
        }
    }

    /**
     * Reads the list of vehicles available on a specific date frame from the database.
     *
     * @param rentalDate The start date of the rental period.
     * @param returnDate The end date of the rental period.
     * @return The list of available vehicles.
     */
    public List<Vehicle> readVehicleListOnDateFrame(Date rentalDate, Date returnDate) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Vehicle.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String rentalDateString = format.format(rentalDate);
            String returnDateString = format.format(returnDate);

            List<Vehicle> vehicles = session.createNativeQuery(
                            "CALL AvailableVehicles(:rentalDate, :returnDate)", Vehicle.class)
                    .setParameter("rentalDate", rentalDateString)
                    .setParameter("returnDate", returnDateString)
                    .getResultList();

            return vehicles;
        } catch (Exception e) {
            logger.error("readVehicleListOnDateFrame catch exception");
            e.printStackTrace();
        }
        return null;
    }
}
