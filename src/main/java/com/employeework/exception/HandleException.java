package com.employeework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//ControllerAdvice use this then it will become the global class
//global class such that wherever exception occur in the porject that
// exception will automatically come in this class
//wherever exception occur in the porject
//  spring boot will automatically sent the exception to this class
@ControllerAdvice
public class HandleException {
    //    when the exception is occur is the type of ResourceNotFound automatically comes into this block
//    this will return back the message error , or which type of error are ocurr
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<String> handleExceptionEmployeeNotFoundException(
//            ResourceNotFound e
//    ){
//        // create the object of error details
//        ErrorDetails errorDetails = new ErrorDetails(
//               new Date(),
//                e.getMessage()
//        );
//        return new ResponseEntity<>("record was not found", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleExceptionEmployeeNotFoundException(
            ResourceNotFound e,
            WebRequest request

    ) {
        // create the object of error details
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                // request.getDescription(true)it will give the client information//this will give the error description  is return type in string format and its also give the url
                request.getDescription(false) //false dont give the client information only giv the url details
                //url is for debugging beacuse which url is occurr the error then it find the error easily way
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    any exception occur in my project i should be able to handle the exception in this method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalException(
            Exception e,
            WebRequest request

    ) {
        // create the object of error details
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                // request.getDescription(true)it will give the client information//this will give the error description  is return type in string format and its also give the url
                request.getDescription(false) //false dont give the client information only giv the url details
                //url is for debugging beacuse which url is occurr the error then it find the error easily way
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }



//    all other type of exception handle the Exception
}
