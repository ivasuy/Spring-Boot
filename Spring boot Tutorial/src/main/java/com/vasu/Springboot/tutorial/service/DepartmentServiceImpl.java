package com.vasu.Springboot.tutorial.service;

import com.vasu.Springboot.tutorial.entity.Department;
import com.vasu.Springboot.tutorial.error.DepartmentNotFoundException;
import com.vasu.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        /*Call repository layer to save data in database*/
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
//         return departmentRepository.findById(departmentId).get();
        /*Error Handling*/
        Optional<Department> department =  departmentRepository.findById(departmentId);

        if (!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {

        Department department1 = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentName()) &&
        !" ".equalsIgnoreCase(department.getDepartmentName())){
            department1.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !" ".equalsIgnoreCase(department.getDepartmentAddress())){
            department1.setDepartmentAddress(department.getDepartmentAddress());
        }
        if(Objects.nonNull(department.getDepartmentCode()) &&
                !" ".equalsIgnoreCase(department.getDepartmentCode())){
            department1.setDepartmentCode(department.getDepartmentCode());
        }
        return departmentRepository.save(department1);
    }

    @Override
    public Department fetchDepartmentName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}

