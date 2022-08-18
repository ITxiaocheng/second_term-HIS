package com.obtk.controller.Doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.Doctor;
import com.obtk.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctor/showAll")
public class DoctorShowAllServlet extends HttpServlet {
    DoctorService service = new DoctorService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        List<Doctor> doctors = service.docGetAll();
        mapper.writeValue(resp.getWriter(), doctors);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
