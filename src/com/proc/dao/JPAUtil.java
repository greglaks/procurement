package com.proc.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf = null;
	
	public static EntityManagerFactory getEntityManagerFactory(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("requisition");
		}
		return emf;
	}
}
