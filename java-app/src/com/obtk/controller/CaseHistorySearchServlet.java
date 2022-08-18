package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisReg;
import com.obtk.entity.PageBean;
import com.obtk.service.CaseHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/caseHistory/search")
public class CaseHistorySearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        ObjectMapper mapper = new ObjectMapper();
        //1、获取参数currentPage
        String search = req.getParameter("search"); //搜索值
        String username = req.getParameter("username"); //医生姓名
        int currentPage = Integer.parseInt(req.getParameter("currentPage")) ;//当前的页码
        int pageSize = Integer.parseInt(req.getParameter("pageSize")) ;//每页显示的条数
        CaseHistoryService service = new CaseHistoryService();
        //2：调用service查询
        PageBean<HisReg> pb = service.findSearchCount(username,search, currentPage, pageSize);//得到分页输出结果
        //3:把pageBean存入request
        mapper.writeValue(resp.getWriter(),pb);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
