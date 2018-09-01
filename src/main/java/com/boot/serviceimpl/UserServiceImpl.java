package com.boot.serviceimpl;

import com.boot.entity.Mean;
import com.boot.entity.TUser;
import com.boot.repository.UserRepository;
import com.boot.service.UserService;
import com.system.core.bean.FrameworkPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<TUser> findByCondition(TUser user){
        List<TUser> list = userRepository.findByCondition(
                user.getName(),
                user.getAge(),
                user.getSex(),
                user.getTel(),
                user.getEmail(),
                user.getAddr());
        return list;
    }

    @Override
    public Page<TUser> findByCondition(TUser user, FrameworkPageable fpa, Map[] sortMap) {

        return userRepository.findByCondition(
                user.getName(),
                user.getAge(),
                user.getSex(),
                user.getTel(),
                user.getEmail(),
                user.getAddr(),
                PageRequest.of(fpa.getPageNo() - 1, fpa.getPageSize(), sortHandler(sortMap)));
    }

    @Override
    public List<TUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String getRedisData() {
        return stringRedisTemplate.opsForValue().get("test");
    }


    public Sort sortHandler(Map[] sortMap) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
//		for (Map map : sortMap) {
//			Order order = new Order("");
//			orders.add(order);
//		}
//		map.get("direction") == null || "asc".equals(map.get("direction")) ? Direction.ASC : Direction.DESC,
//				map.get("property") == null ? "status" : map.get("property").toString()
//		orders.add(new Order(Direction.DESC, "operDate"));
//        orders.add(new Sort.Order(Sort.Direction.DESC, "btTradeId"));
        return new Sort(orders);
    }

    public List<Mean> getMeans(){
        List<Mean> list = new ArrayList<>();
        Mean a = new Mean("第一章","",new ArrayList<>());
        Mean a1 = new Mean("第一节","users",null);
        Mean a2 = new Mean("第二节","",null);
        a.getSubMean().add(a1);
        a.getSubMean().add(a2);
        Mean b = new Mean("第二章","",new ArrayList<>());
        Mean b1 = new Mean("第一节","",null);
        Mean b2 = new Mean("第二节","",null);
        b.getSubMean().add(b1);
        b.getSubMean().add(b2);

        list.add(a);
        list.add(b);
        return list;
    }

    public boolean addUser(TUser user){
        userRepository.save(user);
        return true;
    }

}
