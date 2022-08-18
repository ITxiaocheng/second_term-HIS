package com.obtk.dao;

import com.obtk.entity.Medicine;

import java.util.List;

public interface MedicineDao {
    //返回全部数据
    List<Medicine> medGetAll();

    //搜索药
    List<Medicine> medInquire(String name);

    //根据分类查找数据
    List<Medicine> medGetClassify(String name);

    //库存报警查询
    List<Medicine> medInventoryWarning(int medStock);

    //插入数据all
    int medInsertAll(Medicine med);
}
