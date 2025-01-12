package com.employeework.service;

import com.employeework.entity.Employee;
import com.employeework.exception.ResourceNotFound;
import com.employeework.payload.EmployeeDto;
import com.employeework.repostiory.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    // return back of save method is generic means anything return
//    once the save the record whatever is saved in database it will return that information back to this employee object or content return back into controller know
//    save() method is not void type save() method return baack the content saved in the database
    public EmployeeDto addEmployee(EmployeeDto dto) {
//        convert dto to entity
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        //        before returning back in the response i want to add date
//        employeeDto.setDate(new Date());
        return employeeDto;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

//    based on the id I will get the object and get optional object
//    based on the id number i am getting the actual record from the database
    // optional object convert to the employee object using get() method
//Optional<Employee> opEmployee = employeeRepository.findById(id);
//    Employee employee = opEmployee.get(); this is the  actual record coming  from the database
//EmployeeDto dto  <= this is the one that has a record to be updated
//    record is updated use employee.setName(dto.getName) after the update the record then save the record in the database using save() method
//    public void updateEmployee(Long id, EmployeeDto dto) {
//        Optional<Employee> opEmployee = employeeRepository.findById(id);
//        Employee employee = opEmployee.get();
////        employee.setId(dto.getId()); humlog id modified nhii karna chahye
//        employee.setName(dto.getName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setMobile(dto.getMobile());
//
//
//        employeeRepository.save(employee);
//
//    }

    //    work with dto to update the record
    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
//        when the saving the record do not forget to set the id
//        firstly set the id then save the record
//        because this id is one that will tell  updated the record for this id
        employee.setId(id);
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updatedEmployee);
        return employeeDto;
    }

    //    how will get all the employees
//    employeeRepository.findAll()
//    public List<Employee> getEmployee() {
//       return employeeRepository.findAll();
//    }

    //    employees.stream() stream will do it will take the first object address,it will take the first object address
//    stream() will do copy the object address one by one from the list i am not filtering the record , i am converting the all use map()
//    now map() use "e" is the object address,I will take the object address and i use to the method mapToDto because the put the object address mapToDto
//    employees.stream().map(e-> mapToDto(e)).collect(Collectors.toList());
//    stream will take the first object address cll map() method ,stream will take the second object address cll map() to dto
//    and all the object put into new list
//    this will give us a list of dto
//    I am able to convert all list of entity into single dto with help of stream method
//    stream api it will help us to reduce the code
//    in practical implementation used stream api in our project in the getmethod when the get all the list of entity to convert list of  dto
//    in our project java 8 features used called  stream api is used one  of the placed  it is to convert all  the entity to dto used to stream api
//i get all the record
    //  stream api was not there then i used to loop


//    here i used the concept of pagination  i am not fecth the record based on directly findAll
//    i have to fecth the record or get the record based on pageNo and pageSize then used to PageRequest.of() inside the method pass pageNo and pageSize
//String pegeSize they are not string value , int  pageSize this is a int value
//    PageRequest.of(pageSize, pageNo); this will do it return back an object of the type Pageable
//    page= this object has the detalis about pageSize and pageNo
//    employeeRepository.findAll(page); the return type of this not list this is the return type is page
//    findAll(pagable) its return back as Page its not return back as list
//    Page<Employee> = page of employee this is not store as a list of employee , its is going to be stored as a page of employee
//    all the page of employee is convert to list of employee  using the method "getContent()" then it will convert into a list
//findAll(page)  it take pageable object refrence
//    http://localhost:8080/api/v1/employee?pageNo=0&pageSize=3
//    pageNo=0 pageSize=3=>  (0 index page  me , 3 data show kare ga  )
    //    http://localhost:8080/api/v1/employee?pageNo=1&pageSize=4
//    when i change the pagenumber 1 then and its pageSize is 4 then it will give the 3 record then inside the pageNumber 1 it will show
//    only 4 record
//   pagination work
//    pageNo=1 pageSize=4=>  ( 1 index  page me , 4 data show kare ga  )
//    public List<EmployeeDto> getEmployee(int pageNo, int pageSize) {
//      Pageable page=   PageRequest.of(pageNo,pageSize);//this will take the pageNum and pageSize
//        Page<Employee> all = employeeRepository.findAll(page);
//        List<Employee> employees = all.getContent();
//        List<EmployeeDto> listOfDto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
//        return listOfDto;
//    }


//sorting ,work
//    PageRequest.of  it show many overloaded method PageRequest.of(int pageNumber, int pageSize, Sort sort)
//it will take a Sort object as input
//PageRequest.of(pageNo,pageSize,sortBy);sortBy this cannot take a String it should object  question arises
//how to convert the String to sort object  this method cannot take String argument here is argument mismatch here
//how to convert the String to sort object  used "Sort 1"class ".2"dot "3by" or  called Sort.by()  this method can take a String and
// it can convert that sort object
//    Sort.by(sortBy)  this will to convert string to sort object
//    the record should be sorted  in ascending order
//    of()=> this of()method it take first
//
//    public List<EmployeeDto> getEmployee(int pageNo, int pageSize,String sortBy) {
//        Pageable page=   PageRequest.of(pageNo,pageSize, Sort.by(sortBy));//this will take the pageNum and pageSize
//        Page<Employee> all = employeeRepository.findAll(page);
//        List<Employee> employees = all.getContent();
//        List<EmployeeDto> listOfDto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
//        return listOfDto;
//    }

//    of()=> this of(pageNo,pageSize, Sort.by(sortBy))method it take first integer value ,
//    then it take again integer value and then it take sort object
//    of()method this of method take int , int and sort object , but firstly string is convert into sort object using the Sort.by
//  Sort.by= convert String to  sort object using Sort.by()
//    http://localhost:8080/api/v1/employee?pageNo=1&pageSize=4&sortBy=email&sortDir=desc
//    sortDir=desc then it do a sorting in desecending order

    // i should be able to sort asceinding and desecending order
//    using a concept of ternary operator  and also used on if else condition
//    use two important thing here
//    Sort.by(sortBy).ascending(); this is will return back an object which will result in sorting in   ascending order
//    Sort.by(sortBy).descending();this is will return back an object which will result in sorting in descending order
//String sortDir  => I will have to take the value present init "sorDir"
//    sorDir based on true or false  i will decide whether to run this ascending order and desecending order ;
//    if conditon is true then run the ascending order and
//    if condition is false then run the desecending order help of ternary operator
//    Sort sort =     sortDir.equalsIgnoreCase("asc") (this is true this is supply  asc)  ?(then this is run) Sort.by(sortBy).ascending():Sort.by(sortDir).descending();
//    Sort sort =     sortDir.equalsIgnoreCase("asc") (this is false this is supply dsc)  ?Sort.by(sortBy).ascending():(this is run)   Sort.by(sortDir).descending();
//    sort  = this sort object can have ascending and desecending order
//    this sorting based on alphabetically




//    http://localhost:8080/api/v1/employee?pageNo=1&pageSize=4&sortBy=email&sortDir=desc
//    http://localhost:8080/api/v1/employee?pageNo=0&pageSize=4&sortBy=emailId&sortDir=asc
    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);//this will take the pageNum and pageSize
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();  //  page to convert list by using the getContent()
        List<EmployeeDto> listOfDto = employees.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return listOfDto;
    }


//    opEmp the convert optional to object use get()
//    bad  input is given application thorws an exception that exception is handle
//    handle the exception it should retrun back an appropritae a message
//    message return back record was not found an id with date and time
//    there are so many place exception it might occur
    //spring boot give the centralizedSolution means wherever exception is occur in
    //our project all that excepton will go one place
//    the appliction is throwing an exception i need to create one universal class or global class that can handle the exception
//    to handle the exception to create another class in the payload called as Exception
//  user another java8 features use her
//  use orElseThrow
//    public EmployeeDto getSingleEmployeeById(long empId) {
//        Optional<Employee> opEmp = employeeRepository.findById(empId);
//        Employee employee = opEmp.get();
//        EmployeeDto employeeDto = mapToDto(employee);
//        return employeeDto;
//    }

    //    user another java8 features use her
//    use orElseThrow in id if the record is found then it will take record and put that into employee object
//in case record is not found then automatically go inside the orElseThrow where i will write for throwing an exception
//orElseThrow was introduced in Java 9,
    //orElseThrow inside the use supplier ,suppler does it produces an output it will not take any input
    public EmployeeDto getSingleEmployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Record was not found with id " + empId)
        );
//    object is found then will take mapToDto()
        EmployeeDto employeeDto = mapToDto(employee);
        return employeeDto;
    }

    ;

//    EmployeeDto mapToDto(Employee employee) {
//        EmployeeDto dto = new EmployeeDto();
////       dto.setId(employee.getId());dont return back the id number in the postman
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
//        return dto;
//    }

    //    modelmapper reducing so many line of code
    EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        return dto;
    }

    Employee mapToEntity(EmployeeDto dto) {
        Employee emp = modelMapper.map(dto, Employee.class);
        return emp;

    }


    //i want to return back a selective information dont return back all the information
//when the modified the dto object the entity will not effect  and also database will not effect
//    when the modified the entity class the database will get modified
//    I want to mentioned data and time is created
//I dont want to save the date and time but I want to mentioned current date and time when the record is been saved
//    just show as a response and dont want to save the date and time
//    Employee mapToEntity(EmployeeDto dto) {
//        Employee emp = new Employee();
////        emp.setId(dto.getId());
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
//
//
//        return emp;
//
//    }

}
