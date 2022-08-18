package com.obtk.dao.impl;

import com.obtk.dao.DoctorDao;
import com.obtk.entity.Doctor;
import com.obtk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有信息
     * @return
     */
    @Override
    public List<Doctor> docGetAll() {
        String sql = "SELECT * FROM doctor";
        List<Doctor> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Doctor>(Doctor.class));
        return query;
    }

    /**
     * 根据医生姓名 减余号
     * @param name
     * @return
     */
    @Override
    public int docSubtractRemainingNumber(String name) {
        String sql = "UPDATE doctor SET docRemainingNumber=docRemainingNumber-1 WHERE docName=?";
        int update = jdbcTemplate.update(sql, name);
        return update;
    }
}
