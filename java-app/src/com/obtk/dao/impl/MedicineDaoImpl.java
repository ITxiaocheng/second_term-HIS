package com.obtk.dao.impl;

import com.obtk.dao.MedicineDao;
import com.obtk.entity.Medicine;
import com.obtk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MedicineDaoImpl implements MedicineDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 返回全部数据
     * @return
     */
    @Override
    public List<Medicine> medGetAll() {
        String sql = "SELECT * FROM medicine";
        List<Medicine> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class));
        return query;
    }

    /**
     * 搜索药
     * @param name
     * @return
     */
    @Override
    public List<Medicine> medInquire(String name) {
        String sql = "SELECT * FROM medicine WHERE medName LIKE ?";
        String mh = "%"+name+"%";
        List<Medicine> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class), mh);
        return query;
    }

    /**
     * 根据分类查找数据
     * @param name
     * @return
     */
    @Override
    public List<Medicine> medGetClassify(String name) {
        String sql = "SELECT * FROM medicine WHERE medClassification LIKE ?";
        String mh = "%"+name+"%";
        List<Medicine> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class), mh);
        return query;
    }

    /*
    库存报警查询
     */
    @Override
    public List<Medicine> medInventoryWarning(int medStock) {
        String sql = "SELECT * FROM medicine WHERE medStock <= ?";
        List<Medicine> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Medicine>(Medicine.class), medStock);
        return query;
    }

    /**
     * 插入数据all
     * @param med
     * @return
     */
    @Override
    public int medInsertAll(Medicine med) {
        String sql = "INSERT INTO medicine(medName,medUsualDose,medUnit,medUsageAndDosage,medSpell,medShapeCode,medClassification,medPurchasingPrice,medSellingPrice,medStock)VALUES(?,?,?,?,?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, med.getMedName(), med.getMedUsualDose(), med.getMedUnit(), med.getMedUsageAndDosage(), med.getMedSpell(), med.getMedShapeCode(), med.getMedClassification(), med.getMedPurchasingPrice(), med.getMedSellingPrice(), med.getMedStock());
        return i;
    }
}
