
package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.j4c.ecommerce.inventory.InventoryRepository;
import io.j4c.ecommerce.inventory.ProductDO;

@SpringBootApplication
public class Application {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    CommandLineRunner init(InventoryRepository inventoryRepository) {

        return args -> {

        	/*
        	 * Insert sample data
        	 */
            int count = inventoryRepository.findAll().size();
            if ( count == 0) {
            	logger.info("Insert sample inventory data.");
            	
                inventoryRepository.insert(new ProductDO(5,5));
                inventoryRepository.insert(new ProductDO(10,4));
                inventoryRepository.insert(new ProductDO(13,3));
                inventoryRepository.insert(new ProductDO(11,8));
                inventoryRepository.insert(new ProductDO(1,6));
                inventoryRepository.insert(new ProductDO(8,5));
                inventoryRepository.insert(new ProductDO(2,8));
                inventoryRepository.insert(new ProductDO(4,7));
                inventoryRepository.insert(new ProductDO(15,1));
                inventoryRepository.insert(new ProductDO(7,10));
                inventoryRepository.insert(new ProductDO(6,2));
                inventoryRepository.insert(new ProductDO(9,5));
                inventoryRepository.insert(new ProductDO(14,3));
                inventoryRepository.insert(new ProductDO(12,1));
                inventoryRepository.insert(new ProductDO(3,10));

            } else {
            	logger.info(count + " inventory data already available.");
            }
        };

    }
}
