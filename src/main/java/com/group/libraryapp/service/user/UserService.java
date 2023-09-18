package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service //UserService를 스프링 빈으로 등록해줌, 직접 만든 클래스이기때문에 @Service 사용
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUser(){
        return userRepository.getUsers();

    }

    public void updateUser(UserUpdateRequest request){
        if(userRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name){
        if(userRepository.isUserNotExist(name)) { //isUserNotExist이 true일때 -> 결과가 없음. 리스트가 비어있음. -> 유저 존재X
            throw new IllegalArgumentException();
        }

        userRepository.deleteUser(name);
    }

}
