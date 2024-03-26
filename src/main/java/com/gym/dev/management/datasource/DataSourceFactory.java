package com.gym.dev.management.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceFactory {
	
	public static DataSource getDataSource(String dbType) {
		Properties properties = new Properties();
		BasicDataSource ds = new BasicDataSource();
		
		try (FileInputStream fis = new FileInputStream("D:\\Java\\GymManagementSystem\\management\\src\\main\\java\\com\\gym\\dev\\management\\datasource\\db.properties")) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		if("postgresql".equals(dbType)) {
			ds.setDriverClassName(properties.getProperty("POSTGRESQL_DB_DRIVER_CLASS"));
			ds.setUrl(properties.getProperty("POSTGRESQL_DB_URL"));
            ds.setUsername(properties.getProperty("POSTGRESQL_DB_USERNAME"));
            ds.setPassword(properties.getProperty("POSTGRESQL_DB_PASSWORD"));
		} else {
			return null;
		}
		
		return ds;
	}
}
