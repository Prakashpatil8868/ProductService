package com.project.productservice.controlleradvices;


import com.project.productservice.DTO.ErrorResponseDTO;
import com.project.productservice.Exceptions.ProductNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handlerArithmeticException(){
ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
errorResponseDTO.setMessage("arithmetic problem occured");
ResponseEntity responseEntity=new ResponseEntity<>(
        errorResponseDTO,
        HttpStatus.INTERNAL_SERVER_ERROR
);
return responseEntity;
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handlerNullPointerException(){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
        errorResponseDTO.setMessage("null pointer occured");
        ResponseEntity responseEntity=new ResponseEntity<>(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFoundExceptions.class)
    public ResponseEntity<ErrorResponseDTO> handlerProductNotFoundException(        ProductNotFoundExceptions productNotFoundExceptions
    ){
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
        errorResponseDTO.setMessage(productNotFoundExceptions.getMessage()+"response generate at global level");
        ResponseEntity responseEntity=new ResponseEntity<>(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

}
