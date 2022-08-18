package com.obtk.service;

import com.obtk.dao.HisRegDao;
import com.obtk.dao.impl.HisRegDaoImpl;
import com.obtk.entity.HisReg;
import com.obtk.entity.PageBean;
import org.junit.Test;

import java.util.List;

public class HisRegService {
    HisRegDao dao = new HisRegDaoImpl();

    //查询所有挂号信息
    public List<HisReg> getHisRegAll() {
        return dao.getHisReg();
    }

    //根据ID删除挂号信息
    public int deleteStu(Integer id) {
        if (id != null) {
            return dao.deleteId(id);
        }
        return -1;
    }

    //根据id改变就诊状态
    public int Doctor(int id) {
        if (id != 0) {
            return dao.Doctor(id);
        }
        return -1;
    }

    //添加挂号信息
    public int regInsert(HisReg hisReg) {
        if (hisReg != null) {
            return dao.regInsert(hisReg);
        }
        return -1;
    }

    //搜索
    public PageBean<HisReg> findSearchByPage(String name, int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisReg> pb = new PageBean<HisReg>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findSearchCount(name);
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
        List<HisReg> studentList = dao.getSearch(name,start,rows);
//        System.out.println(studentList);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    public PageBean<HisReg> findStudentByPage(int _currentPage, int _pageSize) {
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数
        //创建空的pageBean
        PageBean<HisReg> pb = new PageBean<HisReg>();
        //2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   //当前页码
        //3：调用dao查询总记录数
        int total = dao.findTotalCount();
        pb.setTotal(total);
        //4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage-1)*rows;
        List<HisReg> studentList = dao.findByPage(start,rows);
//        System.out.println(studentList);
        pb.setList(studentList);
        //5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0? total/rows : total/rows+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    //根据医生姓名 来查询挂号信息
    public List<HisReg> doctorNameShow(String name) {
        if (name != null) {
            return dao.doctorNameShow(name);
        }
        return null;
    }

    //根据就诊卡号返回数据
    public List<HisReg> getHisRegCard(int regCard) {
        if (regCard < 0) {
            return null;
        }
        return dao.getHisRegCard(regCard);
    }

    @Test
    public void show() {
        HisRegService service = new HisRegService();
        List<HisReg> hisRegList = service.doctorNameShow("怡成");
        System.out.println(hisRegList);
    }
}
