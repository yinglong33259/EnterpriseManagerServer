package com.boot.repository;

import com.boot.entity.TUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component("userRepository")
public interface UserRepository extends JpaRepository<TUser, String> {
    @Query("from TUser u where u.name=:name")
    TUser findUser(@Param("name") String name);

    @Query(value="select c from TUser c where "
            + "(c.name = :name or :name is null) and "
            + "(c.age = :age or :age is null) and "
            + "(c.sex = :sex or :sex is null) and "
            + "(c.tel = :tel or :tel is null) and "
            + "(c.email = :email or :email is null) and "
            + "(c.addr = :addr or :addr is null)"
            +"order by c.name asc ")
    public List<TUser> findByCondition(
            @Param("name") java.lang.String name ,
            @Param("age") java.lang.Integer age ,
            @Param("sex") java.lang.String sex ,
            @Param("tel") java.lang.String tel ,
            @Param("email") java.lang.String email ,
            @Param("addr") java.lang.String addr);

    @Query(value="select c from TUser c where "
            + "(c.name = :name or :name is null) and "
            + "(c.age = :age or :age is null) and "
            + "(c.sex = :sex or :sex is null) and "
            + "(c.tel = :tel or :tel is null) and "
            + "(c.email = :email or :email is null) and "
            + "(c.addr = :addr or :addr is null)"
            +"order by c.name asc ")
    public Page<TUser> findByCondition(
            @Param("name") java.lang.String name ,
            @Param("age") java.lang.Integer age ,
            @Param("sex") java.lang.String sex ,
            @Param("tel") java.lang.String tel ,
            @Param("email") java.lang.String email ,
            @Param("addr") java.lang.String addr ,
            Pageable pageable);
}
