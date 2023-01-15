package org.curenosm.springcloud.msvc.cursos.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof CursoUsuario curso)) return false;

        return curso.usuarioId.equals(usuarioId);
    }
}
