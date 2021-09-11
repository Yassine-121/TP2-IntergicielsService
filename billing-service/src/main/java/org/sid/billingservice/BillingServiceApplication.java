package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.models.Customer;
import org.sid.billingservice.models.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient){
		return args -> {
			Customer customer = customerRestClient.findCustomerById(1L);
			Bill bill1 = billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
			/*System.out.println("-------------------------");
			System.out.println(customer.getId());
			System.out.println(customer.getName());
			System.out.println(customer.getEmail());
			System.out.println("-------------------------");*/
			PagedModel<Product> productPagedModel = productItemRestClient.findAll();
			productPagedModel.forEach(p->{
				ProductItem productItem = new ProductItem();
				productItem.setPrice(p.getPrice());
				productItem.setQuantity(new Random().nextInt(100));
				productItem.setBill(bill1);
				productItem.setProductID(p.getId());
				productItemRepository.save(productItem);
			});
		};
	}
}
