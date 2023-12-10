package com.project.car_rental.controllers;

// Java Program to Illustrate App File
import com.project.car_rental.entities.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// Main class
public class App {

    // Main driver method
    public static void main(String[] args)
    {
        Song song1 = new Song();

        song1.setId(1);
        song1.setSongName("Broken Angel");
        song1.setArtist("Akon");

        // Create Configuration
        Configuration configuration = new Configuration();
        //configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Song.class);

        // Create Session Factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Initialize Session Object
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // Here we have used
        // save() method of JPA
        session.save(song1);

        session.getTransaction().commit();
    }
}
