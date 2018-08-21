package com.boot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_role_user", schema = "test", catalog = "")
@IdClass(TRoleUserPK.class)
public class TRoleUser {
    private int id;
    private int roleId;
    private int userId;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRoleUser tRoleUser = (TRoleUser) o;
        return id == tRoleUser.id &&
                roleId == tRoleUser.roleId &&
                userId == tRoleUser.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, userId);
    }
}
