package TEST;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.protobuf.TextFormat.ParseException;

import io.netty.handler.codec.DateFormatter;
import main.java.*;

public class test10 {
	public static boolean isBetween(LocalDate beginTime, LocalDate endTime, LocalDate jutime) {
        LocalDate now = jutime;
        boolean flag = false;
        if(now.isEqual(beginTime)||now.isEqual(endTime)) {
        	flag = true;
        }
        if (now.isAfter(beginTime) && now.isBefore(endTime)) {
            flag = true;
        }
        return flag;
    }
	public static void main(String[] args) throws Exception {
		
		InputStream inputStream =allmain.class.getClassLoader().getResourceAsStream("resource/city.json");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] b = new byte[10240];
		int n;
		while ((n = inputStream.read(b)) != -1) {
		    outputStream.write(b, 0, n);
		}
		System.out.println(outputStream.toString());
//	    Date date = new Date();
//        Instant instant = date.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//
//        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
//        LocalDate localDate = instant.toLocalDate();
//        System.out.println("Date = " + date);
//        System.out.println("LocalDate = " + localDate);
//	       DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        String a = "2022-09-01";
//	        String b = "2023-09-01";
//	        String c = "2023-11-01";
//	        System.out.println(isBetween(LocalDate.parse(a,df),LocalDate.parse(b,df), LocalDate.parse(c,df)));
////        String startStr = "2022-02-26";
//        String endStr = "2023-02-09";
//        List<String> list = getMonthBetweenDate(startStr, endStr);
//        System.out.println(list);
//    }
//    /**
//     * 获取两个日期之间的所有月份 (年月)
//     *
//     * @param startTime
//     * @param endTime
//     * @throws java.text.ParseException 
//     * @return：YYYY-MM
//     */
//    public static List<String> getMonthBetweenDate(String startTime, String endTime) throws java.text.ParseException{
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        // 声明保存日期集合
//        List<String> list = new ArrayList<String>();
//        // 转化成日期类型
//		Date startDate = sdf.parse(startTime);
//		Date endDate = sdf.parse(endTime);
// 
//		//用Calendar 进行日期比较判断
//		Calendar calendar = Calendar.getInstance();
//		while (startDate.getTime()<=endDate.getTime()){
//		    // 把日期添加到集合
//		    list.add(sdf.format(startDate));
//		    // 设置日期
//		    calendar.setTime(startDate);
//		    //把日期增加一天
//		    calendar.add(Calendar.MONTH, 1);
//		    // 获取增加后的日期
//		    startDate=calendar.getTime();
//		}
//        return list;

	}
}
