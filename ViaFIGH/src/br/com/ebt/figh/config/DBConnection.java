package br.com.ebt.figh.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component("dataSource")
public class DBConnection extends DriverManagerDataSource {
	
/*	public DBConnection(){
		this.setDriverClassName("oracle.jdbc.OracleDriver");
		this.setUrl("jdbc:oracle:thin:@148.91.106.33:1521:citi");
		this.setUsername("webrequisitosebt");
		this.setPassword("a1s2d3");
	}*/
	
	public DBConnection(){
		this.setDriverClassName("oracle.jdbc.OracleDriver");
		this.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		this.setUsername("webrequisitosebt");
		this.setPassword("a1s2d3");
	}
	
}
