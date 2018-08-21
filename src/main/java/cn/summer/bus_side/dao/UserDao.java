package cn.summer.bus_side.dao;

import cn.summer.bus_side.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    @Insert("insert into user(user_id,password) values (#{userId}, #{password})")
    void addUser(User user);

    @Select({"select user_id,password from user where user_id=#{user_id}"})
    User selectById(String user_id);


}
