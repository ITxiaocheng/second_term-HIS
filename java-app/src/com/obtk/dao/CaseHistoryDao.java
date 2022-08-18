package com.obtk.dao;

import com.obtk.entity.CaseHistory;
import com.obtk.entity.HisReg;

import java.util.List;

public interface CaseHistoryDao {

    //插入就诊数据
    int chInsert(CaseHistory caseHistory);

    //根据卡号更改就诊状态
    int Doctor(int id);

    //查询所有数据
    List<CaseHistory> chGetAll();

    //根据id删除数据
    int chDelete(int id);

    //根据id查询一条数据
    List<CaseHistory> chGetOne(int id);

    //根据就诊卡号查询一条数据
    List<CaseHistory> chGetIdOne(int id);

    //就诊分页
    // 总条数
    int findDoctorCount(String name);
    //统计每页显示行数
    List<HisReg> findDoctorByPage(String name, int start, int rows);

    //就诊搜索分页
    // 总条数
    int findSearchCount(String nameDoctor, String name);
    //统计每页显示行数
    List<HisReg> findSearchByPage(String nameDoctor, String name, int start, int rows);

    //搜索病历
    List<CaseHistory> chGetSearch(String name);

    //管理员就诊分页
    // 总条数
    int findAdminDoctorCount();
    // 统计每页显示行数
    List<HisReg> findAdminDoctorByPage(int start, int rows);

}
