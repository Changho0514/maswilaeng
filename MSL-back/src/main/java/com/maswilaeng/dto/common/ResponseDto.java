package com.maswilaeng.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {

    private int status;
    private String message;
    private T data;

    public ResponseDto(int status) { this.status = status; }

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto(String Message) {
        this.message = Message;
    }

    public ResponseDto(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> of(String message, T data) { return new ResponseDto<>(message, data); }

    public static ResponseDto<?> of(HttpStatus httpStatus) { return new ResponseDto<>(httpStatus.value()); }

    public static ResponseDto<?> of(String Message) {
        return new ResponseDto<>(Message);
    }

    public static ResponseDto<?> of(HttpStatus httpStatus, String message){
        return new ResponseDto<>(httpStatus.value(), message);
    }

    public static <T> ResponseDto<T> of (HttpStatus httpStatus, T data){
        return new ResponseDto<>(httpStatus.value(), data);
    }
}
