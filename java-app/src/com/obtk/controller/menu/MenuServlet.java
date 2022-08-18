package com.obtk.controller.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

        int permission = Integer.parseInt(req.getParameter("permission")) ;

        //System.out.println("user_power"+permission);

        String sql = null;
        List<Menu> query;
        //为0则是管理员
        if (permission == 0) {
            sql = "SELECT * FROM menu";
            query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class));
        } else {
            sql = "SELECT * FROM menu where menu_show = ?";
            query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Menu>(Menu.class),permission);
        }




//        session.removeAttribute("user_power");  // 获取后删除，保证菜单是一次性的



        List<Menu> testInfos = MenuUtiil.toTree(query);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(resp.getWriter(),testInfos);

    }
}
