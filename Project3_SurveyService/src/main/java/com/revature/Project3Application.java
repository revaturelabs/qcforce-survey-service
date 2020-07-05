package com.revature;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class Project3Application implements CommandLineRunner {

//	@Autowired
//	private FormResponseRepo repository;
//
//	@Autowired
//	private FormRepo formRepository;

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
//		repository.deleteAll();
//
//		FormResponse f1 = new FormResponse();
//		f1.setBatch("Hello");
//		f1.setResponseId(1);
//		f1.setWeek("Week1");
//		FormResponse f2 = new FormResponse();
//		f2.setResponseId(2);
//		f2.setBatch("There");
//		f2.setWeek("Week2");
//		FormResponse f3 = new FormResponse();
//		f3.setResponseId(3);
//		f3.setBatch("There1");
//		f3.setWeek("Week3");
//		// save a couple of customers
//		repository.save(f1);
//		repository.save(f2);
//		repository.save(f3);
//		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (FormResponse customer : repository.findAll()) {
//			System.out.println(customer);
//		}
//		System.out.println();
//
//		// fetch an individual customer
//		System.out.println("Customer found with findByFirstName('Hello'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByBatch("Hello").toString());
//
//		System.out.println("Customer found with findByFirstName('There'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByBatch("There"));
//
//		// fetch an individual customer
//		System.out.println("Found by Week 1");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByWeek("Week1").toString());
//
//		System.out.println("Found by Week 2");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByWeek("Week2"));
//
//		// fetch an individual customer
//		System.out.println("Found batch hello and Week 1");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByBatchAndWeek("Hello", "Week1"));
//
//		System.out.println("Found batch there by Week 2");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByBatchAndWeek("There", "Week2"));
//
//		try {
//			Form f = new Form();
//			f.setId(1);
//			formRepository.save(f);
//			System.out.println("Created Form 1");
//		} catch (Exception e) {
//			System.out.println("Form already exists");
//		}
	}

}
