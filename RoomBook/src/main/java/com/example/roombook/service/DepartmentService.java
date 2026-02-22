package com.example.roombook.service;

import com.example.roombook.entity.Department;

import java.util.List;

public interface DepartmentService {
    // Save operation
    Department saveDepartment(Department department);

    // Read operation
    List<Department> fetchDepartmentList();

    // Update operation
    Department updateDepartment(Department department,
                                Long departmentId);

    // Delete operation
    void deleteDepartmentById(Long departmentId);
}
