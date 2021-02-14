package com.abhi.reactive.reactivedataapi.resource;

import com.abhi.reactive.reactivedataapi.model.Employee;
import com.abhi.reactive.reactivedataapi.model.EmployeeEvent;
import com.abhi.reactive.reactivedataapi.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;


@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    private EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/all")
    public Flux<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmployee(@PathVariable("id") final String id){
        return employeeRepository.findById(id);
    }

    @GetMapping("/{id}/events")
    public Flux<EmployeeEvent> getEmployeeEvents(@PathVariable("id") final String id ){

        return employeeRepository.findById(id)
                .flatMapMany(employee -> {
                     Flux<Long> interval=Flux.interval(Duration.ofSeconds(2));//FLux of events which runs every two seconds

                    //Secod Flux of Events that retrun employee
                    Flux<EmployeeEvent> employeeEventFlux=Flux.fromStream(
                            Stream.generate(()->new EmployeeEvent(employee, new Date()))
                    );

                      return    Flux.zip(interval,employeeEventFlux) //merging the Two flux into one
                                    .map(Tuple2::getT2); //Getting only Employee Event thats why GetT2

                });
    }
}
