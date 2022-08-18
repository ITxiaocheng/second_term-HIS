package com.obtk.service;

import com.obtk.dao.MedicineDao;
import com.obtk.dao.impl.MedicineDaoImpl;
import com.obtk.entity.Medicine;
import org.junit.Test;

import java.util.List;

public class MedicineService {
    MedicineDao dao = new MedicineDaoImpl();

    /**
     * 返回全部数据
     * @return
     */
    public List<Medicine> medGetAll() {
        return dao.medGetAll();
    }

    /**
     * 搜索药
     * @param name
     * @return
     */
    public List<Medicine> medInquire(String name) {
        return dao.medInquire(name);
    }

    /**
     * 根据分类查找数据
     * @param name
     * @return
     */
    public List<Medicine> medGetClassify(String name) {
        return dao.medGetClassify(name);
    }

    /**
     * 库存报警查询
     * @param medStock
     * @return
     */
    public List<Medicine> medInventoryWarning(int medStock) {
        if (medStock > 0) {
            return dao.medInventoryWarning(medStock);
        }
        return null;
    }

    /**
     * 插入数据all
     * @param med
     * @return
     */
    public int medInsertAll(Medicine med) {
        if (med != null) {
            return dao.medInsertAll(med);
        }
        return -1;
    }

    @Test
    public void test() {
        MedicineService service = new MedicineService();
        Medicine med = new Medicine("aaa头孢","","","","",9,"",1.5,2,3);
        int i = service.medInsertAll(med);
        System.out.println(i);

    }
}
