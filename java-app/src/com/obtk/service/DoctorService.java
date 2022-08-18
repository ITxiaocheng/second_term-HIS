package com.obtk.service;

import com.obtk.dao.DoctorDao;
import com.obtk.dao.impl.DoctorDaoImpl;
import com.obtk.entity.Doctor;
import org.junit.Test;

import java.util.List;

public class DoctorService {
    DoctorDao dao = new DoctorDaoImpl();

    /**
     * 查询所有信息
     * @return
     */
    public List<Doctor> docGetAll() {
        return dao.docGetAll();
    }

    /**
     * 根据医生姓名 减余号
     * @param name
     * @return
     */
    public int docSubtractRemainingNumber(String name) {
        if (name != "") {
            return dao.docSubtractRemainingNumber(name);
        }
        return -1;
    }

    @Test
    public void Test() {
        DoctorService service = new DoctorService();
        int b = service.docSubtractRemainingNumber("扁鹊");
        System.out.println(b);
    }
}
