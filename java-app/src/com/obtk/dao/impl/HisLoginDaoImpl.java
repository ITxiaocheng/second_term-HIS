package com.obtk.dao.impl;

import com.obtk.dao.HisLoginDao;
import com.obtk.entity.HisLogin;
import com.obtk.entity.HisReg;
import com.obtk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class HisLoginDaoImpl implements HisLoginDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    //查询登录表
    @Override
    public List<HisLogin> getHisLogin() {
        String sql = "select * from His_login";
        List<HisLogin> hisLoginList = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<HisLogin>(HisLogin.class));
        return hisLoginList;
    }

    //插入注册表
    @Override
    public int insertReg(HisLogin hisLogin) {
        String sql = "INSERT INTO his_login(`name`,`idNumber`,`phoneNumber`,`password`, `permission`)VALUES(?,?,?,?,?)";
        int i = jdbcTemplate.update(sql, hisLogin.getName(), hisLogin.getIdNumber(), hisLogin.getPhoneNumber(), hisLogin.getPassword(), hisLogin.getPermission());
        return i;
    }

    /**
     * 根据id删除字段
     * @param id
     * @return
     */
    @Override
    public int deleteId(int id) {
        String sql = "delete from his_login where id = ?";
        int row = jdbcTemplate.update(sql, id);
        return row;
    }

    /**
     * 根据id编辑信息
     * @param id
     * @param name
     * @param idNumber
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public int idUpdateUser(Integer id, String name, String idNumber, String phoneNumber, String password, Integer permission) {
        String sql = "update his_login set name=?,idNumber=?,phoneNumber=?,password=?,permission=? WHERE id = ?";
        int row = jdbcTemplate.update(sql, name, idNumber, phoneNumber, password, permission, id);
        return row; //返回受影响的行数
    }

    @Override
    public int findTotalCount() {
        String sql = "select count(1) from his_login";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<HisLogin> findByPage(int start, int rows) {
        //1\定义sql语句
        String sql = "select * from his_login order by id limit ?,?";
        //2:获取结果集
        List<HisLogin> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisLogin>(HisLogin.class),start,rows);
        return list;
    }

    //搜索分页
    @Override
    public int findSearchCount(String name) {
        String sql = "select count(1) from his_login where name LIKE ? ";
        String mh = "%"+name+"%";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mh);
        return count;
    }

    @Override
    public List<HisLogin> findSearchByPage(String name, int start, int rows) {
        //1\定义sql语句
        String sql = "select * from his_login  where name LIKE ? order by id limit ?,?";
        String mh = "%"+name+"%";
        //2:获取结果集
        List<HisLogin> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisLogin>(HisLogin.class),mh,start,rows);
        return list;
    }


}
