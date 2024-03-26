package com.gym.dev.management;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.gym.dev.management.datasource.DataSourceFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TestDrive {
	
	public static void main(String args[]) {
		testDataSource("postgresql");
	}
	
	public static void testDataSource(String dbType) {
		DataSource ds = DataSourceFactory.getDataSource("postgresql");
		
		try (Connection con = ds.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM gym");
			
			System.out.println("Gym ID\tName");
			while (rs.next()) {
				System.out.println(rs.getInt("gym_id") + "\t" + rs.getString("gym_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void testHibernate() {
EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientPU");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		@SuppressWarnings("unchecked")
		List<Client> clients = em.createQuery("SELECT c FROM Client c").getResultList();
		
		tx.commit();
		em.close();
		
		for ( Client client : clients) {
			System.out.println(client.getFirstName() + " " + client.getLastName());
		}
	}
}
