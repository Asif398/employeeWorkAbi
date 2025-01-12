package com.employeework.exception;


public class ResourceNotFound extends RuntimeException{
//    super keyword is doing calling the constructor RuntimeException
//    RuntimeException will Exception , Exception will call Throwable that will automatically take all the message and reflect that in postman
//    super keyword is help us to give the message to the postman
//    all configuration class will load first then only other part of the project will run
//    configuration class loaded before controller ,service class
//    custom exception handle the in esay lot of time not write the exception just one exception
//     and handle the exception but try catch block lot of time use the handle the exception
    //yhaan se exception throw kar rha hii

   public  ResourceNotFound(String msg){
       super(msg);
   }
}
