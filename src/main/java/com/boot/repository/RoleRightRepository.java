package com.boot.repository;

import com.boot.entity.TRoleUser;
import com.boot.entity.TRoleUserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component("roleRightRepository")
public interface RoleRightRepository extends JpaRepository<TRoleUser, String> {

    @Query(value="select ru from TRoleUser ru ")
    public List<TRoleUser> findAllRoles();

}
