package dev.hardeep.productservice.advices;

import dev.hardeep.productservice.dtos.ErrorDto;
import dev.hardeep.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

    }
}
