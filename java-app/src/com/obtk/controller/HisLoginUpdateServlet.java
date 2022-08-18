package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisLogin;
import com.obtk.service.HisLoginService;
import com.obtk.utils.CipherUtil;

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

@WebServlet("/HisLogin/update")
public class HisLoginUpdateServlet extends HttpServlet {
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
        System.out.println(sb);
        ObjectMapper ob = new ObjectMapper();
        HisLogin hisLogin = ob.readValue(sb.toString(), HisLogin.class);
        Map<String, Object> map = new HashMap<>();
        HisLoginService service = new HisLoginService();
        //对密码进行MD5加密
        CipherUtil cipher = new CipherUtil();
        String newPwd = cipher.generatePassword(hisLogin.getPassword());
        int i = service.idUpdate(hisLogin.getId(), hisLogin.getName(), hisLogin.getIdNumber(), hisLogin.getPhoneNumber(), newPwd, hisLogin.getPermission());
        //System.out.println("受影响的行数：" + (i == 1 ? i+"插入成功！" : i+"插入失败！"));
        if (i == 1) {
            map.put("msg", "修改成功！");
            map.put("code", 1);
        } else {
            map.put("msg", "修改成功！");
            map.put("code", 0);
        }
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
