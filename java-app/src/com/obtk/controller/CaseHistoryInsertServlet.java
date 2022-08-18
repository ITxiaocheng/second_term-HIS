package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.CaseHistory;
import com.obtk.entity.HisReg;
import com.obtk.service.CaseHistoryService;
import com.obtk.service.HisRegService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/caseHistory/insert")
public class CaseHistoryInsertServlet extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        InputStreamReader reader = new InputStreamReader(req.getInputStream(),"utf-8");
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String a = null;
        while ((a = br.readLine()) != null) {
            sb.append(a);
        }

        ObjectMapper ob = new ObjectMapper();
        CaseHistory ch = ob.readValue(sb.toString(), CaseHistory.class);

        Map<String, Object> map = new HashMap<>();
        CaseHistoryService service = new CaseHistoryService();
        CaseHistory ch1 = new CaseHistory(ch.getCaseCard(), ch.getCaseName(), ch.getCaseGender(), ch.getCaseAge(), ch.getCaseDate(), ch.getCaseIdNumber(), ch.getCaseMoney(), ch.getCaseDepartments(), ch.getCaseDoctor(), ch.getCaseComplaint(), ch.getCasePHI(), ch.getCasePreviousHistory(), ch.getCaseHealthCheckup(), ch.getCaseAuxiliaryExamination(), ch.getCaseDiagnose(), ch.getCaseOpinion());
        int i = service.chInsert(ch1);
        int doctor = service.Doctor(ch.getCaseCard());
        if (i == 1 || doctor != 0) {
            map.put("msg", "插入成功！");
            map.put("code", 1);
        } else {
            map.put("msg", "插入失败！");
            map.put("code", 0);
        }
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
