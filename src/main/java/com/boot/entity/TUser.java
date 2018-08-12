package com.boot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "test", catalog = "")
public class TUser {
    private String name;
    private Integer age;
    private String sex;
    private String tel;
    private String email;
    private String addr;

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUser tUser = (TUser) o;
        return Objects.equals(name, tUser.name) &&
                Objects.equals(age, tUser.age) &&
                Objects.equals(sex, tUser.sex) &&
                Objects.equals(tel, tUser.tel) &&
                Objects.equals(email, tUser.email) &&
                Objects.equals(addr, tUser.addr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, sex, tel, email, addr);
    }
}
