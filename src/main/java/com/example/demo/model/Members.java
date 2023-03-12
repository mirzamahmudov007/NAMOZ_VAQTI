package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Members {

    @Id
    private Long id;

    private Long membersNumber;
}
