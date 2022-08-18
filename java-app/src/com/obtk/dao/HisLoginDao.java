package com.obtk.dao;

import com.obtk.entity.HisLogin;
import com.obtk.entity.HisReg;

import java.util.List;

public interface HisLoginDao {

    //返回登录信息
    List<HisLogin> getHisLogin();

    //注册用户信息 插入数据
    int insertReg(HisLogin hisLogin);

    //根据id删除信息
    int deleteId(int id);

    //根据id编辑信息
    int idUpdateUser(Integer id, String name, String idNumber, String phoneNumber, String password, Integer permission);

    //分页------
    // 总条数
    int findTotalCount();
    //统计每页显示行数
    List<HisLogin> findByPage(int start, int rows);

    //搜索分页
    // 总条数
    int findSearchCount(String name);
    //统计每页显示行数
    List<HisLogin> findSearchByPage(String name, int start, int rows);
}
