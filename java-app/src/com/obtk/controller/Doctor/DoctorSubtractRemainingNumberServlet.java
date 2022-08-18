package com.obtk.controller.Doctor;

import com.obtk.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/doctor/srn")
public class DoctorSubtractRemainingNumberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        String name = req.getParameter("name");
        DoctorService service = new DoctorService();
        HashMap<String, Object> map = new HashMap<>();
        int i = service.docSubtractRemainingNumber(name);
        if (i > 0) {
            map.put("msg", "修改成功");
            map.put("code", 1);
        } else {
            map.put("msg", "修改失败");
            map.put("code", 0);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
