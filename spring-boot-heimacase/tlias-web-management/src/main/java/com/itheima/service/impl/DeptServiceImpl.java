package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service  // 注解说明：将DeptServiceImpl类声明为Spring的Bean对象，使其可以被Spring IoC容器管理
public class DeptServiceImpl implements DeptService {

    //注入Mapper接口
    @Autowired  // 注解说明：将DeptMapper注入到DeptServiceImpl类中
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional  // 注解说明：声明当前方法为事务方法，如果该方法抛出异常，则事务回滚
    @Override
    public void delete(Integer id) {
        // 根据ID删除部门数据
        deptMapper.delete(id);

        int i = 1/0; // 用于测试事务的回滚

        // 根据部门ID删除部门下属的员工数据
        empMapper.deleteByDeptId(id);

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
