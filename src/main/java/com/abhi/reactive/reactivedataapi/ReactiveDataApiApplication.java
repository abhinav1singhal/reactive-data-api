package com.abhi.reactive.reactivedataapi;

import com.abhi.reactive.reactivedataapi.model.Employee;
import com.abhi.reactive.reactivedataapi.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveDataApiApplication {

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository){

		return args->{
			employeeRepository.deleteAll()
					.subscribe(null,null,()->{

						Stream.of(new Employee(UUID.randomUUID().toString(),"Sam","hazel"),
								new Employee(UUID.randomUUID().toString(),"Maxim","jackson"),
								new Employee(UUID.randomUUID().toString(),"Suki","Take")
								)
								.forEach(empl->{
												employeeRepository.save(empl)
												.subscribe(
														System.out::println
												);
										});

					});
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(ReactiveDataApiApplication.class, args);
	}

}
