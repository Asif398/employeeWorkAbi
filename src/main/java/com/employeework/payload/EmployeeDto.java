package com.employeework.payload;


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.util.Date;

public class EmployeeDto {

    //  private Long id;i dont return back a id number then comment the id number and also
//    go to mapToDto and mapToEntity comment the id number as well

//    validation will happen only the dto
//    @NotNull if use not null then only null value are not allowed
//   @NotEmpty  use not empty then ensure that field is not null and not empty
//    @NotBlank ensure that a string is not null not is not allowed and not  empty are not allowed
//    and not  whitespace  allowed  all the annotation comes from jakarta this a part of Hibernate
//    @Size(min=,max=)Specfic a size is constraints for the collection ,array , string

// @Min(value),@Max(value = )ensure the number is within the specfied range  this is work only number not work string
//@Pattern(regexp=) ensure the string matches a specific regular expression
    //mobile is a string dont use min use here
    private Long id;

    @NotBlank
    @Size(min = 3,message = "atleast 3 chars requires")
    private String name;

    @Email
    private String emailId;

    @Size(min=10 , max = 10 , message = "mobile number is should be 10 digit")
    private String mobile;


//    add extra thing in the response
//    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
}

