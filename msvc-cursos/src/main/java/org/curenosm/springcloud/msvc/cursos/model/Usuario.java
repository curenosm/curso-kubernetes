package org.curenosm.springcloud.msvc.cursos.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String password;

}
