package main.java.dataclean.mapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import TEST.jsontest;
import main.java.allmain;
import main.java.contenst;
import main.java.csvmode;

public class fmapper extends Mapper<LongWritable, Text,Text, Text>{
	
	 private String filed[];
	
	//源字段 统一使用String类处理，后面有需要时可进行类型转换
	 private String nbxh; //0.内部序号
	 private String name;//1.企业名称
	 private String jjhklb;//2.经济户口类别
	 private String qylx;  //3.用于决定那些企业公积金为0
	 private char hydm;  //4.行业代码
	 private String djjg;  //5.等级机关
	 private String clrq;   //6.成立日期
	 private String hzrq;
	 private String zczb;//8.注册资本
	 private String jyzt;  //9.经营状态
	 private String tslx;//10.（吊销==2，注销==7，迁出==13）
	 
	 //衍生字段
	 private String year_month;
	 private String day;
	 private String new_year_month;
	 private String new_day;
	 private int time;//划分企业年限
	 private int stday;
	 private int enday;
	 
	 //输出字段
	 private String city;//地区分类
	 private String time_scale;//划分企业年限
	 private String three; 
	 private String scale;
	 private String X1_2;
	 private String X3;
	 private String X4;
	 
	 private JSONObject jkey;
	 private Text arg1=new Text();
	 private Text arg2= new Text();
	 private String lastmon;
	 private String thismon;

	 int month_ ;
	 int month__ ;
	 int up_month ;
	 int down_month;
		/*
		 * col(1)-col(6)
		 * jjhklb+hydm+djjg+zczb+jyzt+clrq
		 */
	 
	 
	 
	 public  int media1;
	 public  int media2;
	 public  String media3;
	 public  int media4;
	 public  int media5;
	 public  String media6;
	 public  String media7;
	 Date startTime;
	 Date endTime;
//	 public static boolean isNumeric(String str){
//		    Pattern pattern = Pattern.compile("[0-9]*");
//		    Matcher isNum = pattern.matcher(str);
//		    if( !isNum.matches() ){
//		        return false;
//		    }
//		    return true;
//	}
	 @Override
	 protected void setup(Context context) throws IOException {
			String jsonStr = "";
			InputStream inputStream =allmain.class.getClassLoader().getResourceAsStream("resource/city.json");
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] b = new byte[10240];
			int n;
			while ((n = inputStream.read(b)) != -1) {
			    outputStream.write(b, 0, n);
			}
//			System.out.println(outputStream.toString());
			jsonStr = outputStream.toString();
			JSONObject jobj = JSON.parseObject(jsonStr);
			jkey = jobj.getJSONObject("city");


			media1 = (int)csvmode.getmedia(1);
			media2 = (int)csvmode.getmedia(2);
			String l3 = String.valueOf((int)csvmode.getmedia(6));
			String l4 = String.valueOf((int)csvmode.getmedia(7));
			media3 = String.valueOf((int)csvmode.getmedia(3));
			media4 = (int)csvmode.getmedia(4);
			media5 = (int)csvmode.getmedia(5);
			media6 = l3.substring(0,4)+"-"+l3.substring(4,6)+"-"+l3.substring(6);
			media7 = l4.substring(0,4)+"-"+l4.substring(4,6)+"-"+l4.substring(6);
		

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			
			startTime=contenst.getStdate();
			lastmon =simpleDateFormat.format(startTime);
			stday =startTime.getDate();

			endTime = contenst.getEndate();
			thismon =simpleDateFormat.format(endTime);
			enday =endTime.getDate();
			
			
	
	 }
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
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//过滤首行
//		if(key.toString().equals("0")) {
//			return;
//		}
		
		
		  CSVParser csvParser = new CSVParser();
		  filed= csvParser.parseLine(value.toString());
		 
		//过滤格式异常数据
		if(filed.length!=11) {
			System.out.println(value.toString());
			return;
		}
		
		clrq = filed[6];
		hzrq = filed[7];
		
		
		if(clrq.equals("")) {
			clrq=media6;
		}
		if(hzrq.equals("")) {
			hzrq=media7;
		}


//		lastmon="2022-09";
//		thismon="2022-10";

		new_year_month = clrq.substring(0, 7);
		new_day = clrq.substring(8);
	
		month__ = Integer.valueOf(clrq.substring(5, 7));
		
//		if((new_year_month.equals(lastmon)&&Integer.valueOf(new_day)>=stday)
//				||(new_year_month.equals(thismon)&&Integer.valueOf(new_day)<=enday)
//				|| (down_month<month__&&month__<up_month)) {
//		当月成立企业
	
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startlo=startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endlo=endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//LocalDate startlo=startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		if(isBetween(startlo,endlo, LocalDate.parse(clrq,df))) {
		
//			   DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String a = "2022-09-01";
//        String b = "2023-09-01";
//        String c = "2023-11-01";
//        System.out.println(isBetween(LocalDate.parse(a,df),LocalDate.parse(b,df), LocalDate.parse(c,df)));
////
			contenst.setNew_this_mon(contenst.getNew_this_mon()+1);
		}




		tslx = filed[10];
		year_month = hzrq.substring(0, 7);
		day = hzrq.substring(8);
		month_ = Integer.valueOf(hzrq.substring(5, 7));
		up_month = Integer.valueOf(thismon.substring(5, 7));
		down_month = Integer.valueOf(lastmon.substring(5, 7));
		if(tslx.equals("07")||tslx.equals("11")||tslx.equals("13")) {
			jyzt = "0";
			if(isBetween(startlo,endlo, LocalDate.parse(hzrq,df))) {
					//针对本月迁出数据
				if(tslx.equals("07")) {
					contenst.setCancel(contenst.getCancel()+1);
				}
				if(tslx.equals("11")) {
					contenst.setRevocation(contenst.getRevocation()+1);
				}
				if(tslx.equals("13")) {
					contenst.setEmigration(contenst.getEmigration()+1);
				}
			}else {
				return;//针对迁出数据
			}
		}
//		
		nbxh =filed[0];
		name=filed[1];
		jjhklb=filed[2];
		qylx =filed[3];
			//行业代码只提取第一个
		hydm = filed[4].toCharArray()[0];
		djjg = filed[5];
		
		
		zczb = filed[8];
		jyzt = filed[9];
		
		
		
		//缺失值填补
		if(jjhklb=="") {
			jjhklb = String.valueOf(media1);
		}
		if(jyzt.equals("")) {
			jyzt=String.valueOf(media5);
		}
		if(Integer.valueOf(jyzt)>1) {
			jyzt="1";
		}
		if(filed[4].equals("")) {
			hydm=(char)media2;
		}
		if(djjg.equals("")) {
			djjg=media3;
		}
		if(zczb.equals("")) {
			zczb=String.valueOf(media4);
		}
		
	
		//划分企业年限
		time = 2022 - Integer.valueOf(clrq.substring(0, 4));
		time = time <= 1 ? 1 : time;
		time = time > 10 ? 11 : time;
		time_scale = String.valueOf(time);
		
		
		
		//地区分类
		city =jkey.containsKey(djjg.substring(0,4))? 
				(String)jkey.get(djjg.substring(0,4)):
				(String)jkey.get(djjg.substring(0,6));

		

		//三次产业划分 ascii 'A'=65 'Z'=90
		three = "3";
		if(hydm == 65) {
			three ="1";
		}else if(hydm>65&&hydm<70) {
			three ="2";
		}
		
		
		
		//规模分类
		float zb =Float.valueOf(zczb);
		if(zb<=100) {
			scale="1";
		}else if(zb<=1000) {
			scale="2";
		}else if(zb<=10000) {
			scale="3";
		}else if(zb<=20000) {
			scale="4";
		}else if(zb>20000) {
			scale="5";
		}
		
		
		
		//添加特征	 X1_2 , X3 , X4
		X1_2="1";
		X3="1";
		X4="1";
		
		int iqylx = Integer.valueOf(qylx);
		if(iqylx==1151||iqylx==4540||iqylx==2125) {
			X4="0";
		}
		contenst.setSumall(contenst.getSumall()+1);
		arg1.set(nbxh);
		arg2.set(jjhklb+","+hydm+","+jyzt+","+ qylx +","+time_scale+","
				+city+","+three+","+scale+","+X1_2+","+X3+","+X4+"\t"+name);
		context.write(arg1,arg2);
	}
}
