package com.employeework.controller;

import com.employeework.entity.Employee;
import com.employeework.payload.EmployeeDto;
import com.employeework.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
//    I create url when i click on sent then is json vai this url will come to the backend code
//backend code will extracrt the jjson content and that json content it is save it to database
//    http://localhost:8080/api/v1/employee/addEmployee this is url calling the method
//    postman extract the json object with the help of @RequestBody
//    @PostMapping("/addEmployee")
//   public String addEmployee(
//
//  ){
//    return "hello";//its return back hello in the postman
//   }

//    @PostMapping("/addEmployee")
//    public String addEmployee(
//  @RequestBody Employee employee
//    ){
//       System.out.println(employee.getName());// when I click url the send in the postman then all data in the backend console show
//        System.out.println(employee.getEmailId());// this url is capable of supplying the json object to the backend code directly
//        System.out.println(employee.getMobile());
//        return "hello";//its return back hello in the postman
//    }

//    @PostMapping("/addEmployee")
//    public ResponseEntity<String> addEmployee(
//            @RequestBody Employee employee
//    ){
//        employeeService.addEmployee(employee);
//        return new ResponseEntity<>("saved", HttpStatus.CREATED);
//    }


//  this time whatever record I am saving in the databasse ,the saved record is given back as a response to postman
//    never back return as String we will give the ResponseEintity
//    @PostMapping("/addEmployee")
//    public ResponseEntity<Employee> addEmployee(
//            @RequestBody Employee employee
//    ){
//        Employee emp = employeeService.addEmployee(employee);
//        return new ResponseEntity<>(emp, HttpStatus.CREATED);
//    }

    //   copy the json data to dto
//    from postman Iam taking conten to dto and from dto i am putting that entity into save it
//  then the entity again convert to dto given back to controller and controller return dto back
//    i would be capture an error used to BindingResult  this bindingResutl is a special class which can automatically capture the error into it
//    EmployeeDto dto if any error occur in field it will automatically go to the BindingResult is an interface  spring boot take care of that
//    @PostMapping("/addEmployee")
//    public ResponseEntity<EmployeeDto> addEmployee(
//       @Valid @RequestBody EmployeeDto dto,
////       i would be capture an error used to Binding result
//       BindingResult result
//    ) {
//
//        EmployeeDto employeeDto = employeeService.addEmployee(dto);
//        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
//    }


    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto dto,
//       i would be capture an error used to Binding result
            BindingResult result
    ) {
//        working of valid annotation in fields
//        can i added the entity in validation yes
        //check error are happened or not happend use the hasError(). 1 added  valid dependency  ,  2 add valid annotation in Dto ,
        // then 3 added @Valid annotation ,then i 4 added BindingResult then added the 5 if condition inside use hasError()
        //BindingResult help us to capture the error inside the BindingResult has take hasError() method it will help us to chek the error is happend or not


        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto employeeDto = employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }


//    http://localhost:8080/api/v1/employee?id=1 this is the query parameter based on id then delete
//    this the second api this api can take the id number as input and it can delete the record in the backend
//    this url is an api because this is an interface to which the frontend framework will get connected angular react android app get connected the api

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(
            @RequestParam Long id
    ) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    //update the record based on id number and also requestBody
    //    http://localhost:8080/api/v1/employee?id=1 @RequestBody Employee employee <= i would supply to this the data to be updated in this requestbody object called employee
    // based on id number this record should be updated ,but the data to be updated is present here =>"employee"
    //but i will not take the data into this Employee entity class , rather I will created a dto that is good practise
    // create a another pacakages called as payload

    //EmployeeDto this EmployeeDto i will use for receving the data from the postman and the data is about updating a record
    //EmployeeDto and Employee entity class have same field
//    @PutMapping
//    public ResponseEntity<String> updateEmployee(
//            @RequestParam Long id,
//            @RequestBody EmployeeDto dto
//    ){
//        employeeService.updateEmployee(id,dto);
//        return new ResponseEntity<>("updated",HttpStatus.OK);
//    }

    //    the controller has return back updated employeeDto record
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(
            @RequestParam Long id,
            @RequestBody EmployeeDto dto
    ) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
//    read all the data , this getEmployee will return back list of employee object called List<Employee>, return type aslo will List of Employee object List<Employee>
//   in CRUD operation save is onle 201 ,and all the record is ok


//    @GetMapping
//    public ResponseEntity<List<Employee>> getEmployee(
//
//    ){
//     List<Employee> employees=  employeeService.getEmployee();
//        return new ResponseEntity<>(employees,HttpStatus.OK);
//    }

    //    one object is  convert to list of dto then not problem
//    how to convert the list of entity to list of dto
//    I have to convert to several object to dto
//    this method returning back list of employee but I want to return back list of dto
//   i need to copy 100 of entity to 100 of dto and 100 of dto to convert 100 of entity
// then stream api will come into this picture
//    List<Employee> this is the data structure
//    employees=> what is the consist of then it consist of list of employee object address List<Employee>


    //    getAllTheRecord
    //using the pagination and sotring  strat work using the jpa repository and not used curd repository beacuses crud repository does not support pagination and sorting
    //    during the getMapping is  i should be able to get limited recorded per page
//            I want  ot get only 5 record perpage
//    if there are 15 record total number of pages will be 3 ,each page give 5 record pageSize  pageSize=5,
//    and used required =flase ,defaultValue =5
//    use @RequestParam inside firstly give name=
//    http://localhost:8080/api/v1/employee?pageSize=5 perPage maximum record that see in 5

//    http://localhost:8080/api/v1/employee
//    required = false, if i dont give the url pageSize=5  then still is going to work
//    because required is false but it will take then default value 5"defaultValue =5"
    //    http://localhost:8080/api/v1/employee
//    if i dont speicified anything atutomaticallly like pass the the any paramtere in the url pageSize will set to 5
//    defaultValue = "5"  suppose when we not passed any request parmeter then it will be show 5 record
    //    http://localhost:8080/api/v1/employee?pageSize=5
//    defaultValue = "5" but it will then take the default 5 automatically pageSize will be 5 , because requried is false
//    the in other word 5 will be stored in the String pageSize


    //    http://localhost:8080/api/v1/employee?pageSize=3
//    but suppose if i write a ? question mark then you can give the pageSize whatevere you want i give the pageSize =3
//    then it will not consider the pageSize is 5 buecaue it will default size is , the value in the variable in pageSize =3 then perPage =3 record


//    same way another paramter add in page number
//            @RequestParam name="pageNo",
//        requried =false
//    default=0 the first page will be represent with index =0,second page willbe represent the index =1 continue to till =5
//    http://localhost:8080/api/v1/employee
//    if i dont give any thing in the url it will automatically refer to   page one or indrex 0 because required is false


    //    http://localhost:8080/api/v1/employee?pageOne=1&pageSize=3
//    but supposse i will give mpercent & and give pageOne=1 then give me the secod page and page size it will be going to 3
//    when i call the get method then i will supply  pageNumber and pageSize   is always is int value
//    http://localhost:8080/api/v1/employee

//    defaultValue = "0"  and defaultValue = "5" first page me 5 record show kare jab hum url me koi parameter pass nhii kage tab
//            required = false   ka matlab hota hiii ki url me koi parameter pass nhii kage still url properly work kare ga
// pagination work only
//    @GetMapping
//    public ResponseEntity<List<EmployeeDto>> getEmployee(
//
//            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
//            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
//
//    ) {
//        List<EmployeeDto> listOfEmployeeDto = employeeService.getEmployee(pageNo, pageSize);
//        return new ResponseEntity<>(listOfEmployeeDto, HttpStatus.OK);
//    }




//    I should be able to sorting the record in the table based on title, name,eamilId , mobile number
//    how do igive that  sorting features on a table
//    http://localhost:8080/api/v1/employee?pageOne=1&pageSize=3&sortBy=email
//     i can supply one more thing in the url  i can sort by based on email
//    RequestParam() iside the give the parameter name= "sortBy"
//    http://localhost:8080/api/v1/employee?pageNo=0&pageSize=4&sortBy=emailId
//    sort this based on email
//    emailId= this is the column name or variable name in our object
//    the sorting is happening in ascending order ,this sorting is default by ascending order
//    @GetMapping
//    public ResponseEntity<List<EmployeeDto>> getEmployee(
//
//            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
//            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
//            @RequestParam(name = "sortBy", required = false, defaultValue = "0") String sortBy
//
//    ) {
//        List<EmployeeDto> listOfEmployeeDto = employeeService.getEmployee(pageNo, pageSize,sortBy);
//        return new ResponseEntity<>(listOfEmployeeDto, HttpStatus.OK);
//    }



//    sort the record in ascending and desecending order
//    take another variable in sortDir
//@RequestParam(name = "sortBy", required = false, defaultValue = "0") String sortBy,
//@RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
//    defaultValue = "0"  the default value are not passed 0
//    defaultValue = "name"  default sort by name

//    @RequestParam(name = "sortDir", required = false, defaultValue = "0") String sortDir
//@RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir
//    sorting be ascending order

//    http://localhost:8080/api/v1/employee?pageNo=0&pageSize=4&sortBy=emailId&sortDir=asc
//    sortBy=emailId  <=if i dont give the url sortBy=emailId then the sorting will automatically happend based on name
//    sorting in the ascending


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(

            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir

    ) {
        List<EmployeeDto> listOfEmployeeDto = employeeService.getEmployee(pageNo, pageSize,sortBy ,sortDir);
        return new ResponseEntity<>(listOfEmployeeDto, HttpStatus.OK);
    }




    //  i want to get the record with a particular employeeid
//    http://localhost:8080/api/v1/employee/employeeId/{empId}1

    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getSingleEmployeeById(
            @PathVariable long empId
    ) {
        EmployeeDto singleEmployeeByIdDto = employeeService.getSingleEmployeeById(empId);
        return new ResponseEntity<>(singleEmployeeByIdDto, HttpStatus.OK);
    }
}
