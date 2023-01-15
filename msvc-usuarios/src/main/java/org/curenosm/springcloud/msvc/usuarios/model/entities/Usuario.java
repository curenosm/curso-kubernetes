package org.curenosm.springcloud.msvc.usuarios.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name="nombre")
    @NotBlank
    @NotEmpty
    @NotNull
    private String nombre;

    @Email
    @Column(unique = true)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;
}
