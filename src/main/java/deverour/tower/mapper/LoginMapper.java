package deverour.tower.mapper;

import deverour.tower.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginMapper {

    @Select("select * from users where username = #{username} and password = #{password}")
    public User findUser(User user);

    @Update("update users set password =#{newpassword} where username = #{username} and password = #{password}")
    public int changepassword(User user);
}
