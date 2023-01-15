package org.curenosm.springcloud.msvc.cursos.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Join table to establish the relationship between cursos and usuarios tables.
 *
 * @version 1.0.0
 * @author Misael Cureño
 */
@Getter
@Setter
@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof CursoUsuario curso)) return false;

        return curso.usuarioId.equals(usuarioId);
    }
}
