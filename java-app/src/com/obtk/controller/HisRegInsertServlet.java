package com.obtk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.entity.HisLogin;
import com.obtk.entity.HisReg;
import com.obtk.service.HisRegService;
import com.obtk.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/HisReg/insert")
public class HisRegInsertServlet extends HttpServlet {
    ObjectMapper mapper = new ObjectMapper();
    private final static String FORMAT_CODE = "000"; //设置格式
    private static String FORMAT_CODEs = "000"; //
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

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
//        System.out.println(sb);

        ObjectMapper ob = new ObjectMapper();
        HisReg hisReg = ob.readValue(sb.toString(), HisReg.class);

        /**
         * 根据日期自动生成就诊卡号
         */
        String sql = "SELECT regId FROM reg ORDER BY regId DESC LIMIT 1";
        List<HisReg> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<HisReg>(HisReg.class));
        int state = 0; //最后一条数据的id
        for (HisReg data : query) {
            state = data.getRegId();
        }

        Map<String, Object> map = new HashMap<>();
        HisRegService service = new HisRegService();
        HisReg hisReg1 = new HisReg(FormatCode(state), hisReg.getRegName(), hisReg.getRegGender(), hisReg.getRegAge(), hisReg.getRegDate(), hisReg.getRegIdNumber(), hisReg.getRegSite(),hisReg.getRegDepartments(), hisReg.getRegDoctor());
        int i = service.regInsert(hisReg1);
//        System.out.println("受影响的行数：" + (i == 1 ? i+"插入成功！" : i+"插入失败！"));
        if (i == 1) {
            map.put("msg", "注册成功！");
            map.put("code", 1);
        } else {
            map.put("msg", "注册失败！");
            map.put("code", 0);
        }
        mapper.writeValue(resp.getWriter(), map);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 自动生成就诊号
     * @param count
     * @return
     */
    public int FormatCode(int count) {
        count = count + 1; // 流⽔号
        DecimalFormat dft = new DecimalFormat(FORMAT_CODE);
        String code = dft.format(count); // 格式化为四位流⽔号 code: 0001
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String date = sdf.format(new Date());
        // 格式化⽇期 date: 20200724
        String codeEnd =  date + code; // codeEnd: BM202007240001
//        System.out.println(code);
        return Integer.parseInt(codeEnd);
    }

    /**
     * 获取昨天日期
     * @return
     */
    public int yesterday() {
        Date today = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        int yesterday = Integer.parseInt(simpleDateFormat.format(today));//获取昨天日期
        return yesterday;
    }


/*
    @Test
    public void test() {
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
        int dates = Integer.parseInt(sdfs.format(new Date()));
        System.out.println(dates);
        if (yesterday() < dates) {
            System.out.println("昨天小于今天");
        } else {
            System.out.println("昨天大于今天");
        }


        System.out.println("================");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        int year =  Integer.parseInt(date.substring(0, 4)); //截取年
        int month =  Integer.parseInt(date.substring(4, 6)); //截取月
        int dater =  Integer.parseInt(date.substring(6, 8)); //截取日
        System.out.println(dater);
        System.out.println("================");
        Date date1 = new Date(year, month, dater);
        Date now;
        Calendar c = Calendar.getInstance();
        now = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        if (date1.after(now)) {
            System.out.println("超过了今天");
        } else {
            System.out.println("没超过今天");
        }

        System.out.println("================");
    }*/

    @Test
    public void test2() {
        // 1.首先，根据时间生成，我们先要格式化一下时间格式
        SimpleDateFormat dmfot = new SimpleDateFormat("yyyyMMdd");
        //截取当前时间作为流水号
        String preCode = dmfot.format(new Date());
        System.out.println(preCode);



    }
}
