package TEST;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;


public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String l= "wewerr";
//		String m="ewr";
//		System.out.println(l.contains(m));
//		String ll="5.000000";
//		float w = Float.valueOf(ll);
//		System.out.println(w);
//		String ll ="sss,www,eee";
//		StringTokenizer st = new StringTokenizer(ll,",");
		
		
		
		Date date = new Date(); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String format = simpleDateFormat.format(date);
		  Date startTime=new Date();
		    //处理日期 直接复制就可以用
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(startTime);
		    //计算前一个月的日期
		    calendar.add(Calendar.MONTH, -1);
		    startTime = calendar.getTime();
		    simpleDateFormat.format(startTime);

	}

}
