package ru.gb.hw.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.hw.util.Views;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.MinParams.class)
    private String firstname;

    @JsonView(Views.MinParams.class)
    private String lastname;

    @Column(unique = true, nullable = false)
    @JsonView(Views.MinParams.class)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonView(Views.Full.class)
    private List<Role> roles = new ArrayList<>();

}
