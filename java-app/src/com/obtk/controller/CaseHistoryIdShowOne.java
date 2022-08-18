package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.CaseHistory;
import com.obtk.service.CaseHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/caseHistory/idShow")
public class CaseHistoryIdShowOne extends HttpServlet {
    CaseHistoryService service = new CaseHistoryService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int caseCard = Integer.parseInt(req.getParameter("caseCard"));
        List<CaseHistory> caseHistories = service.chGetIdOne(caseCard);
        mapper.writeValue(resp.getWriter(), caseHistories);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
