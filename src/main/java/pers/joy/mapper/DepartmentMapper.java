package pers.joy.mapper;

import pers.joy.entity.Department;

import java.util.List;

public interface DepartmentMapper {

    List<Department> queryAllDepartments();

    Department queryDepartmentByNo(String no);
}
