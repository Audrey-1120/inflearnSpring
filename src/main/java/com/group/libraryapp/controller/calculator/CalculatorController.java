package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//Controller : API의 입구
@RestController //이 클래스를 API의 진입 지점으로 만들어줌. 주어진 Class를 Controller로 등록한다.
public class CalculatorController {

    @GetMapping("/add") //아래 함수를 HTTP Method가 GET이고, HTTP path가 /add인 API로 지정함.
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
        return request.getNumber1()*request.getNumber2();
    }
}
