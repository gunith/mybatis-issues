
package test;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import mapper.RoleMapper;
import mapper.UserMapper;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@MapperScan("mapper")
public class Application {

  @Bean
  SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    return factoryBean;
  }

  @Bean
  EmbeddedDatabaseFactoryBean dataSource() {
    EmbeddedDatabaseFactoryBean factoryBean = new EmbeddedDatabaseFactoryBean();
    factoryBean.setDatabaseType(EmbeddedDatabaseType.HSQL);
    factoryBean.setDatabaseName("mybatisissues");
    factoryBean.setDatabasePopulator(
        new ResourceDatabasePopulator(new PathResource("src/test/resources/test/CreateDB.sql")));
    return factoryBean;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	  return new CommandLineRunner() {
		
		@Override
		public void run(String... arg0) throws Exception {
			// TODO Auto-generated method stub
			System.out.println("Run Begin");
			
			RoleMapper roleMapper = ctx.getBean(RoleMapper.class);
			UserMapper userMapper = ctx.getBean(UserMapper.class);
			
			Role role = roleMapper.getRole(1);
			
			User newUser = new User();
			newUser.setId(3);
			newUser.setName("Foo");
			newUser.setGender(Gender.MALE);
			newUser.setStatus(Status.ACTIVE);
			newUser.setRole(role);
			userMapper.insertUser(newUser);
			
			User user1 = userMapper.getUser(1);
			System.out.println(user1);
			User user2 = userMapper.getUser(2);
			System.out.println(user2);
			
			System.out.println("Run End");
		}
	};
  }
}
