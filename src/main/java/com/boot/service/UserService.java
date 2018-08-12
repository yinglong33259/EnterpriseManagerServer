package com.boot.service;

import com.boot.entity.TUser;
import com.system.core.bean.FrameworkPageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<TUser> getUser(TUser user);

    public Page<TUser> findByCondition(TUser user, FrameworkPageable fpa, Map[] sortMap);

}