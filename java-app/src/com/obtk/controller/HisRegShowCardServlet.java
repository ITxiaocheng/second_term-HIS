package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisReg;
import com.obtk.service.HisRegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/HisReg/card")
public class HisRegShowCardServlet extends HttpServlet {
    HisRegService service = new HisRegService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        int regCard = Integer.parseInt(req.getParameter("regCard"));
        List<HisReg> hisRegCard = service.getHisRegCard(regCard);
        mapper.writeValue(resp.getWriter(), hisRegCard);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
