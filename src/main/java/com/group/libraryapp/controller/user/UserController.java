package com.group.libraryapp.controller.user;

import com.group.libraryapp.controller.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {

        //수정하기에 앞서 데이터가 먼저 존재하는지를 id기준으로 판단
        String readSql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        // 1. readSql 실행
        // 2. ?에 request.getId()로 값 가져와서 넣음
        // 3. r(rs, rowNum) -> 0 결과가 하나라도 있으면 0으로 간주
        // 4. 0은 최종적으로 List로 반환됨.
        // isEmpty는 문자열이 빈 값이면 true, 비어있지 않으면 false

        if(isUserNotExist) { //isUserNotExist이 true일때 -> 결과가 없음. 리스트가 비어있음. -> 유저 존재X
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.getName(), request.getId());

    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {

        String readSql = "SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if(isUserNotExist) { //isUserNotExist이 true일때 -> 결과가 없음. 리스트가 비어있음. -> 유저 존재X
            throw new IllegalArgumentException();
        }



        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }




}
