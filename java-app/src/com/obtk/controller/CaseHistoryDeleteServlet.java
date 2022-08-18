package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.service.CaseHistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/caseHistory/delete")
public class CaseHistoryDeleteServlet extends HttpServlet {
    CaseHistoryService service = new CaseHistoryService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        int caseId = Integer.parseInt(req.getParameter("caseId"));
        int i = service.chDelete(caseId);
        HashMap<String, Object> map = new HashMap<>();
        if (i > 0) {
            map.put("msg", "删除成功！");
            map.put("code", 1);
        } else {
            map.put("msg", "删除失败！");
            map.put("code", 0);
        }
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
