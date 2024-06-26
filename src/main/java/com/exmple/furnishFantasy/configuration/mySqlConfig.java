package com.exmple.furnishFantasy.configuration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration	
public class mySqlConfig {
	 @Bean(name="mysqlDatabase")
//	 @ConfigurationProperties(prefix = "database")
	    public DataSource getDataSource() {
	        return DataSourceBuilder.create()
	        .driverClassName("com.mysql.cj.jdbc.Driver")
	        .url("jdbc:mysql://backend-server-01.mysql.database.azure.com:3306/furnishfantasy?useSSL=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
	        .username("backend_developer")
	        .password("Yugb@0409")
	        .build();
	    }
	 @Bean(name="mysqlJdbcTemplate")
	    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDatabase") DataSource dataSource) {
	       return new JdbcTemplate(dataSource);
	    }

}