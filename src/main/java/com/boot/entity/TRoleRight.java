package com.boot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_role_right", schema = "test", catalog = "")
public class TRoleRight {
    private int id;
    private int roleId;
    private int rightId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "right_id")
    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRoleRight that = (TRoleRight) o;
        return id == that.id &&
                roleId == that.roleId &&
                rightId == that.rightId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, rightId);
    }
}
