package com.group.libraryapp.controller.user;

import com.group.libraryapp.controller.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate; //Java DB 커넥터에 대한 클래스.

    public UserController(JdbcTemplate jdbcTemplate){ //JdbcTemplate를 받아서 설정해주는 생성자.
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);


            } //mapRow : jdbcTemplate가 sql query를 실행하면 유저정보가 담기고, 이 정보를 내가 선언한 타입인 UserResponse형태로 바꿔줌
        }); //RowMapper는 쿼리의 결과를 받아, 객체를 반환함.
    }


}
