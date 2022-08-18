package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisReg;
import com.obtk.entity.PageBean;
import com.obtk.service.CaseHistoryService;
import com.obtk.service.HisRegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/HisReg/all")
public class showHisRegServlet extends HttpServlet {
    //new逻辑层
    //HisRegService hisRegService = new HisRegService();
    CaseHistoryService service = new CaseHistoryService();
    //转换为json数据
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));

        //List<HisReg> hisRegList = hisRegService.getHisRegAll();
        PageBean<HisReg> adminDoctorByPage = service.findAdminDoctorByPage(currentPage, pageSize);
        mapper.writeValue(resp.getWriter(),adminDoctorByPage);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
