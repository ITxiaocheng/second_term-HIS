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

@WebServlet("/HisLogin/search")
public class HisLoginSearchServlet extends HttpServlet {
    HisLoginService hisLoginService = new HisLoginService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //1、获取参数currentPage
        String search = req.getParameter("search");
        int currentPage = Integer.parseInt(req.getParameter("currentPage")) ;//当前的页码
        int pageSize = Integer.parseInt(req.getParameter("pageSize")) ;//每页显示的条数
        PageBean<HisLogin> search1 = hisLoginService.findSearchByPage(search,currentPage,pageSize);
        mapper.writeValue(resp.getWriter(), search1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
