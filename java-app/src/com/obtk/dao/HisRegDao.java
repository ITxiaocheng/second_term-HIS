package com.obtk.dao;

import com.obtk.entity.HisReg;

import java.util.List;

public interface HisRegDao {
    //返回挂号信息
    List<HisReg> getHisReg();

    //添加数据
    int regInsert(HisReg hisReg);

    //根据id删除信息
    int deleteId(int id);

    //根据id改变就诊状态
    int Doctor(int id);

    //搜索挂号数据
    List<HisReg> getSearch(String search, int start, int rows);

    // 总条数
    int findTotalCount();
    //统计每页显示行数
    List<HisReg> findByPage(int start, int rows);

    //搜索分页
    // 总条数
    int findSearchCount(String name);
    //统计每页显示行数
    List<HisReg> findSearchByPage(String name, int start, int rows);

    //根据医生姓名 来查询挂号信息
    List<HisReg> doctorNameShow(String name);

    //根据就诊卡号返回数据
    List<HisReg> getHisRegCard(int regCard);
}
