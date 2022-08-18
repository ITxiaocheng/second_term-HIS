package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.Medicine;
import com.obtk.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/medicine/classify")
public class MedicineClassifyShow extends HttpServlet {
    MedicineService service = new MedicineService();
    ObjectMapper mapper = new ObjectMapper();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String medClassifioation = req.getParameter("medClassifioation");
        List<Medicine> medicines = service.medGetClassify(medClassifioation);
        mapper.writeValue(resp.getWriter(), medicines);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
