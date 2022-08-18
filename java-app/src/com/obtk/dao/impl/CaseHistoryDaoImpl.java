package com.obtk.dao.impl;

import com.obtk.dao.CaseHistoryDao;
import com.obtk.entity.CaseHistory;
import com.obtk.entity.HisLogin;
import com.obtk.entity.HisReg;
import com.obtk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CaseHistoryDaoImpl implements CaseHistoryDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 插入就诊数据
     * @param ch
     * @return
     */
    @Override
    public int chInsert(CaseHistory ch) {
        String sql = "INSERT INTO caseHistory(`caseCard`,`caseName`,`caseGender`,`caseAge`,`caseDate`,`caseIdNumber`,`caseDepartments`,`caseDoctor`,`caseComplaint`, `casePHI`,`casePreviousHistory`,`caseHealthCheckup`, `caseAuxiliaryExamination`,`caseDiagnose`,`caseOpinion`,`caseWriteDate`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
        int update = jdbcTemplate.update(sql, ch.getCaseCard(), ch.getCaseName(), ch.getCaseGender(), ch.getCaseAge(), ch.getCaseDate(), ch.getCaseIdNumber(), ch.getCaseDepartments(), ch.getCaseDoctor(), ch.getCaseComplaint(), ch.getCasePHI(), ch.getCasePreviousHistory(), ch.getCaseHealthCheckup(), ch.getCaseAuxiliaryExamination(), ch.getCaseDiagnose(), ch.getCaseOpinion());
        return update;
    }

    /**
     * 根据卡号更改就诊状态
     * @param id
     * @return
     */
    @Override
    public int Doctor(int id) {
        String sql = "UPDATE reg SET regState='已就诊' WHERE regCard = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<CaseHistory> chGetAll() {
        String sql = "SELECT * FROM caseHistory ORDER  BY caseId DESC";
        List<CaseHistory> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CaseHistory>(CaseHistory.class));
        return query;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @Override
    public int chDelete(int id) {
        String sql = "delete from caseHistory where caseId = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    /**
     * 根据id查询数据
     * @return
     */
    @Override
    public List<CaseHistory> chGetOne(int id) {
        String sql = "SELECT * FROM caseHistory WHERE caseId = ?";
        List<CaseHistory> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CaseHistory>(CaseHistory.class), id);
        return query;
    }

    /**
     * 根据就诊卡号查询一条数据
     * @param id
     * @return
     */
    @Override
    public List<CaseHistory> chGetIdOne(int id) {
        String sql = "SELECT * FROM caseHistory WHERE caseCard = ?";
        List<CaseHistory> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CaseHistory>(CaseHistory.class), id);
        return query;
    }

    /**
     * 就诊分页
     * @param name
     * @return
     */
    @Override
    public int findDoctorCount(String name) {
        String sql = "select count(1) from reg where regDoctor = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count;
    }

    @Override
    public List<HisReg> findDoctorByPage(String name, int start, int rows) {
        //1\定义sql语句
        String sql = "SELECT * FROM reg WHERE regDoctor = ? ORDER BY regId LIMIT ?,?";
        //2:获取结果集
        List<HisReg> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class),name,start,rows);
        return list;
    }


    /**
     * 就诊搜索分页
     * @param nameDoctor
     * @param name
     * @return
     */
    @Override
    public int findSearchCount(String nameDoctor, String name) {
        String sql = "SELECT COUNT(1) FROM reg WHERE regDoctor = ? AND regName LIKE ?";
        String mh = "%"+name+"%";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nameDoctor, mh);
        return count;
    }

    @Override
    public List<HisReg> findSearchByPage(String nameDoctor, String name, int start, int rows) {
        //1\定义sql语句
        String sql = "SELECT * FROM reg  WHERE regDoctor = ? AND regName LIKE ? ORDER BY regId LIMIT ?,?";
        String mh = "%"+name+"%";
        //2:获取结果集
        List<HisReg> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class),nameDoctor, mh,start,rows);
        return list;
    }

    /**
     * 搜索病历
     * @param name
     * @return
     */
    @Override
    public List<CaseHistory> chGetSearch(String name) {
        String sql = "SELECT * FROM caseHistory WHERE caseName LIKE ?";
        String mh = "%"+name+"%";
        List<CaseHistory> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CaseHistory>(CaseHistory.class), mh);
        return query;
    }

    /**
     * 管理员就诊分页
     * @return
     */
    @Override
    public int findAdminDoctorCount() {
        String sql = "select count(1) from reg";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<HisReg> findAdminDoctorByPage(int start, int rows) {
        //1\定义sql语句
        String sql = "select * from reg order by regId desc limit ?,?";
        //2:获取结果集
        List<HisReg> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class),start,rows);
        return list;
    }

}
