package com.boot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_role_user", schema = "test", catalog = "")
public class TRoleUser {

    @Column(name = "id")
    private int id;
    @EmbeddedId
    private TRoleUserPK pk;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TRoleUserPK getPk() {
        return pk;
    }

    public void setPk(TRoleUserPK pk) {
        this.pk = pk;
    }
}
