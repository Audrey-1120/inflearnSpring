package com.group.libraryapp.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; //Integer는 NULL을 표현할 수 있음.

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


}
