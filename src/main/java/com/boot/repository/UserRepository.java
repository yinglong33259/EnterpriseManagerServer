package com.boot.repository;

import com.boot.entity.TRight;
import com.boot.entity.TRole;
import com.boot.entity.TRoleUser;
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

    @Query(value="select c from TUser c where "
            + "c.loginId = :loginId and c.password = :password ")
    public TUser findUserByLoginIdAndPwd(@Param("loginId")String loginId, @Param("password")String password);

//    @Query(value="select ru from TRoleUser ru ")
//    public List<TRoleUser> findAllRoles();

    @Query(value="select r from TRole r, TRoleUser ru where "
            + " ru.pk.userId = :userId and ru.pk.roleId = r.id ")
    public List<TRole> findUserRoles(@Param("userId")java.lang.Integer userId);

    @Query(value="select ri from TRight ri, TRole ro, TRoleUser ru, TRoleRight rr where "
            + " ru.pk.userId = :userId and ru.pk.roleId = ro.id and rr.roleId = ro.id and ri.id = rr.rightId ")
    public List<TRight> findUserRights(@Param("userId")java.lang.Integer userId);

}
