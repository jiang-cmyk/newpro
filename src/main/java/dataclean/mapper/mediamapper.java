package main.java.dataclean.mapper;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.opencsv.CSVParser;


public class mediamapper extends Mapper<LongWritable, Text, LongWritable, Text>{


	int jjhklb;
	String hydm;
	String djjg;
	String zczb;
	String jyzt;
	String clrq;
	String hzrq;
	
	 public static boolean isNumeric(String str){
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    if( !isNum.matches() ){
        return false;
    }
    return true;
}
	private Text text =new Text();
	private String filed[];
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
	
//		 0   #nbxh(C|50)    int64  
//		 1   qymc(C|300)    object 
//		 2   uniscid(C|50)  object 
//		 3   zch(C|50)      object 
//		 4   fzrxm(C|500)   object 
//		 5   jjhklb(C|2)    int64  
//		 6   qylx(C|6)      int64  
//		 7   hydm(C|8)      object 
//		 8   djjg(C|20)     int64  
//		 9   clrq(D)        object 
//		 10  hzrq(D)        object 
//		 11  zczb(N|21|6)   float64
//		 12  jyzt(C|4)      float64
//		 13  tslx(C|2)      int64  
//		 14  jyfw(C|4000)   object 
//		csvReader.get(0),csvReader.get(1),
//		csvReader.get(5),
//		csvReader.get(6),csvReader.get(7),
//		csvReader.get(8),csvReader.get(9),
//		csvReader.get(10),csvReader.get(11),
//		csvReader.get(12),csvReader.get(13)    
		  CSVParser csvParser = new CSVParser();
		  filed= csvParser.parseLine(value.toString());
	
	jjhklb =Integer.valueOf(filed[2]);
	hydm =filed[4];
		djjg =filed[5];
	zczb =filed[8];
		jyzt =filed[9];
	 clrq = filed[6];
	 hzrq = filed[7];
		if(clrq.length()!=10||hzrq.length()!=10) {
			return;
		}
		if(hydm.equals("")) {
			return;
		}
		text.set(jjhklb+","+(hydm.charAt(0)+0)+","+djjg+","+zczb+","+jyzt
				+","+clrq.substring(0,4)+clrq.substring(5,7)+clrq.substring(8)
				+","+hzrq.substring(0,4)+hzrq.substring(5,7)+hzrq.substring(8));
		context.write(key, text);
	}
}
