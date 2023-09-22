package com.vasu.Springboot.tutorial.controller;

import com.vasu.Springboot.tutorial.entity.Department;
import com.vasu.Springboot.tutorial.error.DepartmentNotFoundException;
import com.vasu.Springboot.tutorial.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    /*Telling Spring to use the object of DepartmentService that you
    have in your container and wire it with this reference*/
    /*There are two types of dependency injection that we can use
    Constructor Base and Setter based
    here we are using property based*/
    @Autowired
    private DepartmentService departmentService;
    /*Logger is used to debug our application*/
    private final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @PostMapping("/departments")
    /*@RequestBody -> Convert the json object that we are getting into department object*/
    public Department saveDepartment(@Valid @RequestBody Department department){
        /*Call Service layer to save the data*/
        logger.info("Inside saveDepartment of DepartmentService");
        return departmentService.saveDepartment(department);
    }
    /*Getting all Departments*/
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList(){
        logger.info("Inside fetchDepartmentList of DepartmentService");
        return departmentService.fetchDepartmentList();
    }
    /*Fetching The data by id*/
    /*Over here id(path variable) is a dynamic part as we want to fetch the data
    only for that id */
    @GetMapping("/departments/{id}")
    /*PathVariable is used to map id with departmentid*/
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentById(departmentId);
    }
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department Successfully deleted";
    }
    /*Updating data*/
    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartment(departmentId, department);
    }

    /*Fetching Department by Name*/
    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentName(departmentName);
    }
}
