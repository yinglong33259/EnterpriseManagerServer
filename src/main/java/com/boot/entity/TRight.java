package com.boot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_right", schema = "test", catalog = "")
public class TRight {
    private int id;
    private String type;
    private String rightName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "right_name")
    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TRight tRight = (TRight) o;
        return id == tRight.id &&
                Objects.equals(type, tRight.type) &&
                Objects.equals(rightName, tRight.rightName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, rightName);
    }
}
