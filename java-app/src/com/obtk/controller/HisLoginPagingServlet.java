package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisLogin;
import com.obtk.entity.HisReg;
import com.obtk.entity.PageBean;
import com.obtk.service.HisLoginService;
import com.obtk.service.HisRegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HisLogin/pageing")
public class HisLoginPagingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        HisLoginService hisLoginService = new HisLoginService();
        ObjectMapper mapper = new ObjectMapper();
        //1、获取参数currentPage
        int currentPage = Integer.parseInt(request.getParameter("currentPage")) ;//当前的页码
        int pageSize = Integer.parseInt(request.getParameter("pageSize")) ;//每页显示的条数
        //2：调用service查询
        PageBean<HisLogin> pb = hisLoginService.findStudentByPage(currentPage,pageSize);//得到分页输出结果
        //System.out.println(pb);
        //3:把pageBean存入request
        mapper.writeValue(resp.getWriter(),pb);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
