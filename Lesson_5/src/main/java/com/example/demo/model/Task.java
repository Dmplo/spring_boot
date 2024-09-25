package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String Description;

    @Enumerated(EnumType.STRING)
    private State state;

    @CreationTimestamp
    private LocalDate createdAt;

    public Task(String description) {
        Description = description;
        this.state = State.NOT_STARTED;
    }
}