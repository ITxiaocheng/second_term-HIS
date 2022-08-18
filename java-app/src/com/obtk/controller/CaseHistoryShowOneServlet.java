package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.CaseHistory;
import com.obtk.service.CaseHistoryService;
import com.obtk.service.HisRegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/caseHistory/showOne")
public class CaseHistoryShowOneServlet extends HttpServlet {
    CaseHistoryService service = new CaseHistoryService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int caseId = Integer.parseInt(req.getParameter("caseId"));
        List<CaseHistory> caseHistories = service.chGetOne(caseId);
        mapper.writeValue(resp.getWriter(), caseHistories);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
