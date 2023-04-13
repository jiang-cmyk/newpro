
package TEST;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csvreader.CsvWriter;

import main.java.contenst;

public class test8 {

	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
//		
//		Date d =new Date();
//		System.out.println(String.valueOf(d.getDate()));
//		System.out.println(sf.format(d));
		Date stdate =new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-25");
		Date endate =new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-24");

		System.out.println(endate.getDay());
		contenst.setStdate(stdate);
		contenst.setEndate(endate);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
//        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath+"/其他.csv",',',Charset.forName("UTF-8"));
        String ti = sf.format(contenst.getStdate())+"-"+sf.format(contenst.getEndate());
      System.out.println(sf.format(contenst.getStdate()));
		// TODO Auto-generated method stub
//		System.out.println("sdasdd");
//		String path = "C:\\Users\\jiang\\Desktop\\2022.9月活跃度分析\\9月份活跃度数据\\变更情况表";
//		
//		System.out.println( new File(path).listFiles()[0].getAbsolutePath());
//		File f=new File(path);
//		if(!f.exists()) {
//			f.createNewFile();
//			System.out.println("创建");
		}
	}
///newpro/src/main/java/dataclean/reduce/fzjgreducercz.java

