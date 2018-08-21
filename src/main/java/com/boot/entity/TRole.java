package com.boot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_role", schema = "test", catalog = "")
public class TRole {
    private int id;
    private String roleName;
    private String remark;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRole tRole = (TRole) o;
        return id == tRole.id &&
                Objects.equals(roleName, tRole.roleName) &&
                Objects.equals(remark, tRole.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, remark);
    }
}
