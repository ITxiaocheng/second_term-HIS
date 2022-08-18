package com.obtk.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.obtk.entity.HisLogin;
import com.obtk.service.HisLoginService;
import com.obtk.utils.CipherUtil;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/HisLogin/all")
public class showHisLoginServlet extends HttpServlet {
    //new逻辑层
    HisLoginService hisLoginService = new HisLoginService();
    //转换为json数据
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
        String phoneNumber = null; //手机号码
        String password = null; //密码
        //3:将json字符串转换为User对象 注意User对象一定要有无参构造函数
        ObjectMapper ob = new ObjectMapper();
        //注意：接收的数据格式一定是  {username:'张三',password:'123456'}
        HisLogin hisLogin = ob.readValue(sb.toString(), HisLogin.class);
        phoneNumber = hisLogin.getPhoneNumber();
        password = hisLogin.getPassword();
        //得到所有用户信息
        List<HisLogin> hisLoginAll = hisLoginService.getHisLoginAll();
        CipherUtil cipher = new CipherUtil();
        Map<String,Object> map = new HashMap<String,Object>();
        HisLogin h;
        for (int i = 0; i < hisLoginAll.size(); i++) {
            h = hisLoginAll.get(i);
            //System.out.println("后台数据："+h.getPhoneNumber() +" "+h.getPassword());
            if (h.getPhoneNumber().equals(phoneNumber) && cipher.validatePassword(h.getPassword(), password)) {
                System.out.println("登录成功！");
                map.put("msg","登录成功！");
                map.put("code",true);
                map.put("qx", h.getPermission());
                map.put("yisheng", h.getName());
                break;
            } else {
                //System.out.println("登陆失败！ 用户名或密码错误！");
                map.put("msg","登录失败！");
                map.put("code",false);
            }
        }
        mapper.writeValue(resp.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Test
    public void test() {
        String pwd1 = "1234";
        String pwd2 = "";
        CipherUtil cipher = new CipherUtil();
        System.out.println("未加密的密码：" + pwd1);
        //将1234加密
        pwd2 = cipher.generatePassword(pwd1);
        System.out.println("加密后的密码：" + pwd2);

        System.out.print("验证密码是否正确：");
        if (cipher.validatePassword(pwd2, pwd1)) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }
    }

}
