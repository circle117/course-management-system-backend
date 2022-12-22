package pers.joy.service.impl;

import org.springframework.stereotype.Service;
import pers.joy.entity.Department;
import pers.joy.mapper.DepartmentMapper;
import pers.joy.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentMapper.queryAllDepartments();
    }
}
