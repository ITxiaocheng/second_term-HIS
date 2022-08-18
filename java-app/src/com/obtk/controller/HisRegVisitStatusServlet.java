package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.service.HisLoginService;
import com.obtk.service.HisRegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/HisReg/visitStatus")
public class HisRegVisitStatusServlet extends HttpServlet {
    HisRegService service = new HisRegService();
    //转换为json数据
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int id = Integer.parseInt(req.getParameter("id"));
        int doctor = service.Doctor(id);
        Map<String,Object> map = new HashMap<String,Object>();
        if (doctor != -1) {
            map.put("msg", "状态更新成功！");
            map.put("zt", true);
        } else {
            map.put("msg", "状态更新失败！");
        }
        mapper.writeValue(resp.getWriter(), map);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
