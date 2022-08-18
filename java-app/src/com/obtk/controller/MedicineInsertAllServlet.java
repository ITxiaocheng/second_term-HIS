package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisReg;
import com.obtk.entity.Medicine;
import com.obtk.service.HisRegService;
import com.obtk.service.MedicineService;

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

@WebServlet("/medicine/insertAll")
public class MedicineInsertAllServlet extends HttpServlet {
    MedicineService service = new MedicineService();
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
        Medicine med = ob.readValue(sb.toString(), Medicine.class);

        Map<String, Object> map = new HashMap<>();
        Medicine medicine1 = new Medicine(med.getMedName(), med.getMedUsualDose(), med.getMedUnit(), med.getMedUsageAndDosage(), med.getMedSpell(), med.getMedShapeCode(), med.getMedClassification(), med.getMedPurchasingPrice(), med.getMedSellingPrice(), med.getMedStock());
        int i = service.medInsertAll(medicine1);
        if (i == 1) {
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
