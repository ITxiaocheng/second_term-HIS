package com.obtk.dao;

import com.obtk.entity.Doctor;

import java.util.List;

public interface DoctorDao {
    //查询所有信息
    List<Doctor> docGetAll();

    //根据医生姓名 减余号
    int docSubtractRemainingNumber(String name);
}
