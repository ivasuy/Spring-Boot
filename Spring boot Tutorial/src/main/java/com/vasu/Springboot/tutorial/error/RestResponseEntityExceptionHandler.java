package com.vasu.Springboot.tutorial.error;

import com.vasu.Springboot.tutorial.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*This class is responsible for sending the response back,
upon what is the exception that is thrown*/
@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}

/*In this class we created a method departmentNotFoundException which is handling
Department Not Found Exception over here it takes DepartmentNotFoundException as a parameter over here,
plus request as a input parameter.

ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
we created this particular error message and in this error message we are passing http status not found ,
and whatever the message it is. And then we return this message wrapped around in Response Entity
*/

/*Whenever a request comes to DepartmentController and DepartmentController will throw that department not found
Exception it will come around to RestResponseEntityExceptionHandler because we added the @ControllerAdvice,
And this particular controller will return the message as response status */