package org.curenosm.springcloud.msvc.auth.models;

import lombok.*;

/**
 * Class that models a user, this is a common class which should not remain since the update in one
 * project should be enough to
 *
 * @author Misael Cureño
 * @version 1.0.0
 */
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
