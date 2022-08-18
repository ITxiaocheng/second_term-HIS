package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.service.HisRegService;
import com.obtk.utils.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/HisReg/del")
public class HisRegDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        Integer id = Integer.valueOf(req.getParameter("id"));
        System.out.println("id=" + id);
        HisRegService hisRegService = new HisRegService();
        ObjectMapper mapper = new ObjectMapper();
        HashMap<Object, Object> map = new HashMap<>();
        int i = hisRegService.deleteStu(id);
        int code = i > 0 ? 0 : 1; //0成功 1失败
        map.put("code", code);
        mapper.writeValue(resp.getWriter(), map);
//        System.out.println(code);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
