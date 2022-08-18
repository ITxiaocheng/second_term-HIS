package com.obtk.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrimaryGenerater extends Thread {
    public static void main(String[] args) throws Exception {
//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//        Date startTime = ft.parse("2022-08-03 29:32:54");
//        Date endTime = ft.parse("2022-08-03 11:35:54");
//        Date nowTime = new Date();
//        boolean effectiveDate = isEffectiveDate(nowTime, startTime, endTime);
//        if (effectiveDate) {
//            System.out.println("当前时间在范围内");
//        }else {
//            System.out.println("当前时间在不在范围内");
//        }
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String date = sdf.format(new Date());
//        System.out.println("当前时间：" + date);
//
//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
//        Date startTime = ft.parse(date+" 00:00:00");
//        String dates = ft.format(startTime);
//        System.out.println("预定时间：" + dates);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-12-31");
        Date date2 = sdf.parse("2019-01-31");

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        if (date1.compareTo(date2) > 0) {
            System.out.println("Date1 时间在 Date2 之后");
        } else if (date1.compareTo(date2) < 0) {
            System.out.println("Date1 时间在 Date2 之前");
        } else if (date1.compareTo(date2) == 0) {
            System.out.println("Date1 时间与 Date2 相等");
        } else {
            System.out.println("程序怎么会运行到这里?正常应该不会");
        }
    }
    /**
     *
     * @param nowTime   当前时间
     * @param startTime	开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
