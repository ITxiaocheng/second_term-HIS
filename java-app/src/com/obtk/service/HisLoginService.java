package com.obtk.service;

import com.obtk.dao.HisLoginDao;
import com.obtk.dao.impl.HisLoginDaoImpl;
import com.obtk.entity.HisLogin;
import com.obtk.entity.HisReg;
import com.obtk.entity.PageBean;
import org.junit.Test;

import java.util.List;

public class HisLoginService {
    HisLoginDao dao = new HisLoginDaoImpl();

    //查看所有用户信息
    public List<HisLogin> getHisLoginAll() {
        return dao.getHisLogin();
    }

    //插入注册表
    public int insertReg(HisLogin hisLogin) {
        if (hisLogin != null) {
            return dao.insertReg(hisLogin);
        }
        return -1;
    }

    //根据ID删除挂号信息
    public int deleteUser(Integer id) {
        if (id != null) {
            return dao.deleteId(id);
        }
        return -1;
    }

    //分页
    public PageBean<HisLogin> findStudentByPage(int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisLogin> pb = new PageBean<HisLogin>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findTotalCount();
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
        List<HisLogin> studentList = dao.findByPage(start,rows);
//        System.out.println(studentList);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    //搜索
    public PageBean<HisLogin> findSearchByPage(String name, int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisLogin> pb = new PageBean<HisLogin>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findSearchCount(name);
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
//        List<HisLogin> studentList = dao.getSearch(name,start,rows);
        List<HisLogin> studentList = dao.findSearchByPage(name, start, rows);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    public int idUpdate(int id, String name, String idNumber, String phoneNumber, String password, Integer permission) {
        if (id < 0 || name == null || idNumber == null || phoneNumber == null || password == null) {
            return -1;
        }
        return dao.idUpdateUser(id, name, idNumber, phoneNumber, password, permission);
    }

    @Test
    public void Test() {
        HisLoginService service = new HisLoginService();
        List<HisLogin> hisLoginAll = service.getHisLoginAll();
        System.out.println(hisLoginAll);
    }
}
