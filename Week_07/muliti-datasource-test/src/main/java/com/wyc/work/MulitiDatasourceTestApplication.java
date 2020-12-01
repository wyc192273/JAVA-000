package com.wyc.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(exclude = { JdbcTemplateAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class MulitiDatasourceTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulitiDatasourceTestApplication.class, args);
	}

}
