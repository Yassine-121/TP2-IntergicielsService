package org.id.customerservice;

import org.id.customerservice.entities.Customer;
import org.id.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer(null,"Rober","Rober@gmail.com"));
			customerRepository.save(new Customer(null,"Manny","Manny@gmail.com"));
			customerRepository.save(new Customer(null,"Grant","Grant@gmail.com"));
			customerRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});
		};
	}
}
