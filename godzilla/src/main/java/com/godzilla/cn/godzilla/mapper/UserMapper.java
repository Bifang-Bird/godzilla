package com.godzilla.cn.godzilla.mapper;

import com.godzilla.cn.godzilla.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(count,name,password) VALUES(#{count},#{name},#{password})")
    int add(@Param("count") String title, @Param("name") String detail, @Param("password") String nodes);

    @Update("UPDATE user SET count = #{count}, name = #{name}, password = #{password} WHERE id = #{id}")
    int update(@Param("count") String title, @Param("name") String detail, @Param("password") String nodes, @Param("id") Long id);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int delete(Long id);

    @Select("SELECT id, count, name, password  FROM  user  WHERE id = #{id}")
    User findUserById(@Param("id") Long id);

    @Select("SELECT id, count, name, password FROM user  WHERE count = #{count} and password = #{password}")
    User fingUserbyCountAndPassword(@Param("count") String count,@Param("password") String password);

    @Select("SELECT id, count, name, password FROM user")
    List<User> findUserList();
}
