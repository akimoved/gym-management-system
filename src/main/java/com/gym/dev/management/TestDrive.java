package com.gym.dev.management;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TestDrive {
	public static void main(String args[]) {
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
