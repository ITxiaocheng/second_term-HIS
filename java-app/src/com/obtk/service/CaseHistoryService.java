package com.obtk.service;

import com.obtk.dao.CaseHistoryDao;
import com.obtk.dao.impl.CaseHistoryDaoImpl;
import com.obtk.entity.CaseHistory;
import com.obtk.entity.HisReg;
import com.obtk.entity.PageBean;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class CaseHistoryService {
    CaseHistoryDao dao = new CaseHistoryDaoImpl();

    public int chInsert(CaseHistory ch) {
        if (ch != null) {
            return dao.chInsert(ch);
        }
        return -1;
    }

    public int Doctor(int id) {
        if (id != 0) {
            return dao.Doctor(id);
        }
        return -1;
    }

    public List<CaseHistory> chGetAll() {
        return dao.chGetAll();
    }

    public int chDelete(int id) {
        return dao.chDelete(id);
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    public List<CaseHistory> chGetOne(int id) {
        if (id > 0) {
            return dao.chGetOne(id);
        }
        return null;
    }

    /**
     * 根据就诊卡号查询一条数据
     * @param id
     * @return
     */
    public List<CaseHistory> chGetIdOne(int id) {
        if (id > 0) {
            return dao.chGetIdOne(id);
        }
        return null;
    }

    //就诊分页
    public PageBean<HisReg> findDoctorByPage(String name, int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisReg> pb = new PageBean<HisReg>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findDoctorCount(name);
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
        List<HisReg> studentList = dao.findDoctorByPage(name,start,rows);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    //就诊搜索
    public PageBean<HisReg> findSearchCount(String nameDoctor, String name, int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisReg> pb = new PageBean<HisReg>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findSearchCount(nameDoctor, name);
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
        List<HisReg> studentList = dao.findSearchByPage(nameDoctor, name, start, rows);
        // System.out.println(studentList);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    public List<CaseHistory> chGetSearch(String name) {
        if (name != null) {
            return dao.chGetSearch(name);
        }
        return null;
    }

    public PageBean<HisReg> findAdminDoctorByPage(int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisReg> pb = new PageBean<HisReg>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findAdminDoctorCount();
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
        List<HisReg> studentList = dao.findAdminDoctorByPage(start,rows);
        // System.out.println(studentList);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Test
    public void test() {
        CaseHistoryService service = new CaseHistoryService();
        List<CaseHistory> caseHistories = service.chGetIdOne(220809080);
        System.out.println(caseHistories);
    }
}
