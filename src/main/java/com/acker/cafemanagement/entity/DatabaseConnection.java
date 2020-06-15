package com.acker.cafemanagement.entity;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp2.datasources.SharedPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acker.cafemanagement.service.DatabaseConnectionService;

public class DatabaseConnection {
	final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
		
		private DataSource getDataSource(){
			DataSource dataSource;
			DriverAdapterCPDS driver = new DriverAdapterCPDS();
			try {
			driver.setDriver("org.h2.Driver");
			}catch(ClassNotFoundException clnotex) {
				logger.error("{}",clnotex);
			}
			driver.setUrl("jdbc:h2:mem:testdb");
			driver.setUser("sa");
			driver.setPassword("");
			
			SharedPoolDataSource sharedPoolDS = new SharedPoolDataSource();
			sharedPoolDS.setConnectionPoolDataSource(driver);
			sharedPoolDS.setMaxConnLifetimeMillis(100);
			sharedPoolDS.setValidationQuery("SELECT 'OK'");
			sharedPoolDS.setDefaultTestWhileIdle(true);
			dataSource = sharedPoolDS;
			
			return dataSource;
		}
		
		public Connection getConnection() {
			Connection conn = null;
			DataSource dataSource = getDataSource();
			try {
			conn = dataSource.getConnection(); 
			}catch(SQLException sqlex) {
				logger.error("{}",sqlex);
			}
			return conn;
		}
}
