package com.obtk.dao.impl;

import com.obtk.dao.HisRegDao;
import com.obtk.entity.HisReg;
import com.obtk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class HisRegDaoImpl implements HisRegDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有挂号信息
     * @return
     */
    @Override
    public List<HisReg> getHisReg() {
        String sql = "select * from reg";
        List<HisReg> hisRegList = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<HisReg>(HisReg.class));
        return hisRegList;
    }

    //插入挂号信息数据
    @Override
    public int regInsert(HisReg hisReg) {
        String sql = "INSERT INTO reg(`regCard`,`regName`,`regGender`,`regAge`,`regDate`,`regIdNumber`,`regSite`,`regDepartments`,`regDoctor`)VALUES(?,?,?,?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql,hisReg.getRegCard(), hisReg.getRegName(), hisReg.getRegGender(), hisReg.getRegAge(), hisReg.getRegDate(), hisReg.getRegIdNumber(), hisReg.getRegSite(), hisReg.getRegDepartments(), hisReg.getRegDoctor());
        return i;
    }

    /**
     * 根据id删除字段
     * @param id
     * @return
     */
    @Override
    public int deleteId(int id) {
        String sql = "delete from reg where regId = ?";
        int row = jdbcTemplate.update(sql, id);
        return row;
    }

    /**
     * 根据id改变就诊状态
     * @param id
     * @return
     */
    @Override
    public int Doctor(int id) {
        String sql = "UPDATE reg SET regState='已就诊' WHERE regId = ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    /**
     * 搜索挂号数据
     * @param search
     * @return
     */
    @Override
    public List<HisReg> getSearch(String search, int start, int rows) {
        System.out.println(1);
        String sql = "SELECT * FROM reg WHERE regName LIKE ? limit ?,?";
        String mh = "%"+search+"%";
        List<HisReg> list = new ArrayList<>();
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class),mh,start,rows);
        System.out.println(list);
        return list;
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(1) from reg";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<HisReg> findByPage(int start, int rows) {
        //1\定义sql语句
        String sql = "select * from reg order by regId desc limit ?,?";
        //2:获取结果集
        List<HisReg> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class),start,rows);
        return list;
    }

    @Override
    public int findSearchCount(String name) {
        String sql = "select count(1) from reg where regName LIKE ? ";
        String mh = "%"+name+"%";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mh);
        return count;
    }

    @Override
    public List<HisReg> findSearchByPage(String name, int start, int rows) {
        //1\定义sql语句
        String sql = "select * from reg  where regName LIKE ? order by regId limit ?,?";
        String mh = "%"+name+"%";
        //2:获取结果集
        List<HisReg> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class),mh,start,rows);
        return list;
    }

    /**
     * 根据医生姓名 来查询挂号信息
     * @param name
     * @return
     */
    @Override
    public List<HisReg> doctorNameShow(String name) {
        String sql = "SELECT * FROM reg WHERE regDoctor = ? ORDER  BY regId DESC";
        List<HisReg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class), name);
        return query;
    }

    /**
     * 根据就诊卡号返回数据
     * @param regCard
     * @return
     */
    @Override
    public List<HisReg> getHisRegCard(int regCard) {
        String sql = "SELECT * FROM reg WHERE regCard = ?";
        List<HisReg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class), regCard);
        return query;
    }
}
