package com.abhi.reactive.reactivedataapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document
@EqualsAndHashCode
@Data
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;

}
