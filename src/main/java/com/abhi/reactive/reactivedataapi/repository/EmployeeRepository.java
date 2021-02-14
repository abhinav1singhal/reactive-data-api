package com.abhi.reactive.reactivedataapi.repository;

import com.abhi.reactive.reactivedataapi.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String> {
}
