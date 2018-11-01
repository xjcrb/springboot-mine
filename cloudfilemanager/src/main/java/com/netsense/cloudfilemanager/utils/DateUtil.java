package com.netsense.cloudfilemanager.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Util
 *
 * @author: huijunluo
 * @date:Oct 12, 2015
 * @Copyright:Copyright (c) 深圳世纪博仕网络信息技术有限公司 2014 ~ 2015 版权所有
 */
public class DateUtil {

    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
    public static final String YM = "yyyyMM";
    public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String NORMAL_DATE_FORMAT_NEW = "yyyy-mm-dd hh24:mi:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ALL = "yyyyMMddHHmmssS";

    

//    public static void main(String args[]) {
//        int i= compare_date("1995-11-12 15:21", "1999-12-11 09:59");
//        System.out.println("i=="+i);
//     }

     public static int compare_date(Date DATE1, Date DATE2) {
        
        
//         DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
         try {
//             Date dt1 = parseDateTime(DATE1);
//             Date dt2 = parseDateTime(DATE2);
             if (DATE1.getTime() > DATE2.getTime()) {
//                 System.out.println("dt1 在dt2前");
                 return 1;
             } else if (DATE1.getTime() < DATE2.getTime()) {
//                 System.out.println("dt1在dt2后");
                 return -1;
             } else {
                 return 0;
             }
         } catch (Exception exception) {
             exception.printStackTrace();
         }
         return 0;
     }
     
     public static void main(String[] args) {
         
         try {
//             String smsTime = "2016-03-08 14:36:57";
//             System.out.println(DateUtil.parseDateTime(smsTime));
//             System.out.println(DateUtil.formatDate(getLastYear(new Date())));
//             System.out.println(DateUtil.formatDate(getthreeyesrago(new Date())));
//             System.out.println(DateUtil.formatDate(new Date()));
//           System.out.println( DateUtil.addSecond(DateUtil.parseDateTime(smsTime),60));
        	 System.out.println(DateUtil.formatDateTime(new Date()));
        	 System.out.println(DateUtil.formatDate(new Date()));
        	 System.out.println(parseDateTime("99991231"));
        	 System.out.println(dateToNormalString(new Date()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static Date addSecond(Date date, int second) throws Exception {
        Calendar cl = Calendar. getInstance();
        cl.setTime(date);
        cl.add(Calendar.SECOND, second);
        return cl.getTime();
    }
    
    public static Long strDateToNum(String paramString) throws Exception {
        if (paramString == null)
            return null;
        String[] arrayOfString = null;
        String str = "";
        if (paramString.indexOf("-") >= 0) {
            arrayOfString = paramString.split("-");
            for (int i = 0; i < arrayOfString.length; ++i)
                str = str + arrayOfString[i];
            return Long.valueOf(Long.parseLong(str));
        }
        return Long.valueOf(Long.parseLong(paramString));
    }

    public static Long strDateToNum1(String paramString) throws Exception {
        if (paramString == null)
            return null;
        String[] arrayOfString = null;
        String str = "";
        if (paramString.indexOf("-") >= 0) {
            arrayOfString = paramString.split("-");
            for (int i = 0; i < arrayOfString.length; ++i)
                if (arrayOfString[i].length() == 1)
                    str = str + "0" + arrayOfString[i];
                else
                    str = str + arrayOfString[i];
            return Long.valueOf(Long.parseLong(str));
        }
        return Long.valueOf(Long.parseLong(paramString));
    }

    public static String numDateToStr(Long paramLong) {
        if (paramLong == null)
            return null;
        String str = null;
        str = paramLong.toString().substring(0, 4) + "-"
                + paramLong.toString().substring(4, 6) + "-"
                + paramLong.toString().substring(6, 8);
        return str;
    }


    public static java.util.Date stringToDate(String paramString1,
                                              String paramString2) throws Exception {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
                paramString2);
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString1);
        } catch (ParseException localParseException) {
            throw new Exception("解析日期字符串时出错！");
        }
    }

    public static String dateToString(java.util.Date paramDate,
                                      String paramString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
                paramString);
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static java.util.Date compactStringToDate(String paramString)
            throws Exception {
        if(paramString.length()==6){
            return stringToDate(paramString, "yyyyMM");
        }
        if(paramString.length()==8){
            return stringToDate(paramString, "yyyyMMdd");
        }
        return new Date();
    }

    public static String dateToCompactString(java.util.Date paramDate) {
        return dateToString(paramDate, "yyyyMMdd");
    }

    public static String dateToNormalString(java.util.Date paramDate) {
        return dateToString(paramDate, "yyyy-MM-dd");
    }

    public static String compactStringDateToNormal(String paramString)
            throws Exception {
        return dateToNormalString(compactStringToDate(paramString));
    }

    public static int getDaysBetween(java.util.Date paramDate1,
            java.util.Date paramDate2) throws Exception {
        Calendar localCalendar1 = Calendar.getInstance();
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar1.setTime(paramDate1);
        localCalendar2.setTime(paramDate2);
        if (localCalendar1.after(localCalendar2))
            throw new Exception("起始日期小于终止日期!");
        int i = localCalendar2.get(6) - localCalendar1.get(6);
        int j = localCalendar2.get(1);
        if (localCalendar1.get(1) != j) {
            localCalendar1 = (Calendar) localCalendar1.clone();
            do {
                i += localCalendar1.getActualMaximum(6);
                localCalendar1.add(1, 1);
            } while (localCalendar1.get(1) != j);
        }
        return i;
    }

    public static java.util.Date addDays(java.util.Date paramDate, int paramInt)
            throws Exception {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int i = localCalendar.get(6);
        localCalendar.set(6, i + paramInt);
        return localCalendar.getTime();
    }

    public static java.util.Date addDays(String paramString1,
                                         String paramString2, int paramInt) throws Exception {
        Calendar localCalendar = Calendar.getInstance();
        java.util.Date localDate = stringToDate(paramString1, paramString2);
        localCalendar.setTime(localDate);
        int i = localCalendar.get(6);
        localCalendar.set(6, i + paramInt);
        return localCalendar.getTime();
    }

    public static java.sql.Date getSqlDate(java.util.Date paramDate)
            throws Exception {
        java.sql.Date localDate = new java.sql.Date(paramDate.getTime());
        return localDate;
    }

    public static String formatDatesimple(java.util.Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM");
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static String formatDate(java.util.Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static String formatDateTime(java.util.Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setLenient(false);
        return localSimpleDateFormat.format(paramDate);
    }

    public static java.util.Date parseDate(String paramString)
            throws Exception {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("日期解析出错！", localParseException);
        }
    }

    public static java.util.Date parseDateTime(String paramString)
            throws Exception {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("时间解析异常！", localParseException);
        }
    }

    public static Integer getYM(String paramString) throws Exception {
        if (paramString == null)
            return null;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localSimpleDateFormat.setLenient(false);
        java.util.Date localDate;
        try {
            localDate = localSimpleDateFormat.parse(paramString);
        } catch (ParseException localParseException) {
            throw new Exception("时间解析异常！", localParseException);
        }
        return getYM(localDate);
    }

    public static Integer getYM(java.util.Date paramDate) {
        if (paramDate == null)
            return null;
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int i = localCalendar.get(1);
        int j = localCalendar.get(2) + 1;
        return new Integer(i * 100 + j);
    }

    public static int addMonths(int paramInt1, int paramInt2) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(1, paramInt1 / 100);
        localCalendar.set(2, paramInt1 % 100 - 1);
        localCalendar.set(5, 1);
        localCalendar.add(2, paramInt2);
        return getYM(localCalendar.getTime()).intValue();
    }

    public static java.util.Date addMonths(java.util.Date paramDate,
            int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(2, paramInt);
        return localCalendar.getTime();
    }

    public static int monthsBetween(int paramInt1, int paramInt2) {
        int i = paramInt2 / 100 * 12 + paramInt2 % 100
                - (paramInt1 / 100 * 12 + paramInt1 % 100);
        return i;
    }

    public static int monthsBetween(java.util.Date paramDate1,
            java.util.Date paramDate2) {
        return monthsBetween(getYM(paramDate1).intValue(), getYM(paramDate2).intValue());
    }

    public static String getChineseDate(Calendar paramCalendar) {
        int i = paramCalendar.get(1);
        int j = paramCalendar.get(2);
        int k = paramCalendar.get(5);
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append(i);
        localStringBuffer.append("年");
        localStringBuffer.append(j + 1);
        localStringBuffer.append("月");
        localStringBuffer.append(k);
        localStringBuffer.append("日");
        return localStringBuffer.toString();
    }

    public static String getChineseWeekday(Calendar paramCalendar) {
        switch (paramCalendar.get(7)) {
        case 2:
            return "星期一";
        case 3:
            return "星期二";
        case 4:
            return "星期三";
        case 5:
            return "星期四";
        case 6:
            return "星期五";
        case 7:
            return "星期六";
        case 1:
            return "星期日";
        }
        return "未知";
    }

    /**
     * 获取时间差
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws
     */
    public static String getDateDiff(Date beginDate, Date endDate) throws Exception {
        String str = "";

        // 开始时间
        Calendar calendarBegin = Calendar.getInstance();
        if (beginDate!=null) {
            calendarBegin.setTime(beginDate);
        }

        // 结束时间
        Calendar calendarEnd = Calendar.getInstance();
        if (endDate!=null) {
            calendarEnd.setTime(endDate);
        }

        // 求时间差
        long timeDiff = calendarEnd.getTimeInMillis() - calendarBegin.getTimeInMillis();
        int day = (int) (timeDiff / (1000 * 60 * 60 * 24));

        if (day <= 30) {
            str = "1个月";
        } else {
            int month = (day / 30);
            if (month >= 12) {
                int year = month / 12;
                if (month > year * 12) {
                    str = year + "年" + (month - year * 12) + "个月";
                } else {
                    str = year + "年";
                }
            } else {
                str = month + "个月";
            }
        }

        return str;
    }

    public static String getSimpleDate(String dateStr, String regex, int index){
        if(null!=dateStr){
            if(dateStr.indexOf(regex)==-1){
                return null;
            }

                dateStr = dateStr.replace(" ", "");
            String[] aa = dateStr.split(regex);
            if(aa!=null && aa.length-1>=index){
                return aa[index];
            }

        }
        return null;
    }
    //上一个月
    public static Date getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
    //上一年
    public static Date getLastYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -12);
        return cal.getTime();
    }
    public static Date getthreeyesrago(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -36);
        return cal.getTime();
    }
    public static String getsimpledatatostring(Date date){
    	 String s = DateUtil.formatDate(date);
    	 String[] array = s.split("-");
    	 String ss="";
    	 for(int i=0;i<array.length;i++){
    		 ss +=array[i];
    	 }
    	 return ss;
    }
    //上一天
    public static Date getLastDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    //回到指定几天前的日期
    public static Date getlastfewday(Date date,int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -day);
        return cal.getTime();
    }
}
