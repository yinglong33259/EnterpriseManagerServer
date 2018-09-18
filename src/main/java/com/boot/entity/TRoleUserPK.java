package com.boot.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TRoleUserPK implements Serializable {
    private int roleId;
    private int userId;

    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

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
        TRoleUserPK that = (TRoleUserPK) o;
        return roleId == that.roleId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }
}
