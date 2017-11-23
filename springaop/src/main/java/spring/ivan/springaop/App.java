package spring.ivan.springaop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class App {

	public static void main(String[] args) {
		new SpringApplicationBuilder(App.class).run(args);
	}
}
