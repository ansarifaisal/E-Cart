package com.fsl.ecart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource(value = { "classpath:db.properties" }) // -> property file name
@ComponentScan(basePackages = "com.fsl.ecart") // -> package to scan
@EnableTransactionManagement // -> enable database transaction
public class HibernateConfig {
	// -> Start Region Hibernate Properties
	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${hibernate.show_sql}")
	private String showSql;

	@Value("${hibernate.format_sql}")
	private String formatSql;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddl;

	@Value("${hibernate.package.scan}")
	private String packageToScan;
	// -> End Region Hibernate Properties
	// -> Start Region DB Properties
	@Value("${db.oracle.url}")
	private String url;

	@Value("${db.oracle.username}")
	private String userName;

	@Value("${db.oracle.password}")
	private String password;

	@Value("${db.oracle.driver.class}")
	private String driver;
	// -> End Region DB Properties
	// -> Start Region Hikari Properties
	@Value("${hikari.maxPoolSize}")
	private int maxPoolSize;

	@Value("${hikari.minIdleTime}")
	private int minIdleTime;

	@Value("${hikari.cachePrepStmts}")
	private boolean cachePrepareStmt;

	@Value("${hikari.prepStmtCacheSize}")
	private int prepareStmtCacheSize;

	@Value("${hikari.prepStmtCacheSqlLimit}")
	private int prepareStmtCacheSqlLimit;

	@Value("${hikari.useServerPrepStmts}")
	private boolean useServerPrepareStmt;

	// -> End Region Hikari Properties
	@Bean
	public DataSource getDataSource() {
		final HikariDataSource dataSource = new HikariDataSource();
		/*
		 * The Following is the Oracle Database Configuration With Hikari Datasource
		 */
		dataSource.setMaximumPoolSize(maxPoolSize);
		dataSource.setMinimumIdle(minIdleTime);
		dataSource.setDriverClassName(driver);
		dataSource.setJdbcUrl(url);
		dataSource.addDataSourceProperty("user", userName);
		dataSource.addDataSourceProperty("password", password);
		dataSource.addDataSourceProperty("cachePrepStmts", cachePrepareStmt);
		dataSource.addDataSourceProperty("prepStmtCacheSize", prepareStmtCacheSize);
		dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", prepareStmtCacheSqlLimit);
		dataSource.addDataSourceProperty("useServerPrepStmts", useServerPrepareStmt);
		return dataSource;
	}

	// -> creating session factory.
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		// -> getting the properties for hibernate
		sessionBuilder.addProperties(getHibernateProperties());
		// -> fully qualified name of the package that contains entity
		sessionBuilder.scanPackages(packageToScan);
		return sessionBuilder.buildSessionFactory();
	}

	// -> specifying hibernate properties.
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect); // -> database specific property
		// -> this will allow us to see the sql query executed.
		properties.put("hibernate.show_sql", showSql);
		// -> this will format the sql query.
		properties.put("hibernate.format_sql", formatSql);
		// -> this will allow us to automatically create, update or drop the table in
		// the database.
		// properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
		return properties;
	}

	// -> Returning the HibernateTransactionManager Object.
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
}
