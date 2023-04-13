package main.java;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import TEST.jsontest;

public class readcsvfile {
	
	//读取csv文件指定列到文件
    public static void getform(String[] args) throws Exception {
        String readerCsvFilePath = new File(args[0]).listFiles()[0].getAbsolutePath();
        String writerCsvFilePath = args[1];
        File wfile =new File(writerCsvFilePath);
        if(!wfile.exists()) {
        	if(!wfile.getParentFile().exists()) {
        		wfile.getParentFile().mkdirs();
        	}
        	wfile.createNewFile();
        }
        String filed[]=args[2].split(",");
        int[] f = new int[filed.length];
        int i=0;
        
        for(String s:filed) {
        	f[i]=Integer.valueOf(filed[i++]);
        }
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath, ',', Charset.forName("UTF-8"));
        CsvReader csvReader = new CsvReader(readerCsvFilePath, ',', Charset.forName("GBK"));
        
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
//        String[] head = csvReader.getHeaders(); //获取表头
//        csvWriter.writeRecord(head);
        String[] s = new String[filed.length];
        while(csvReader.readRecord()){
        	i=0;
        	for(int ff:f)
        	s[i++]=csvReader.get(ff);
//        	String[] s ={csvReader.get(0),csvReader.get(1),
//        			csvReader.get(5),
//        			csvReader.get(6),csvReader.get(7),
//        			csvReader.get(8),csvReader.get(9),
//        			csvReader.get(10),csvReader.get(11),
//        			csvReader.get(12),csvReader.get(13)        			
//        			};
        	
            csvWriter.writeRecord(s);
        }
        csvReader.close();
        csvWriter.close();
    }


    public static void max_minstd(String[] args) throws Exception {
    	
    	
        String readerCsvFilePath = args[0];
        		//new File(args[0]).listFiles()[0].getAbsolutePath();
        String writerCsvFilePath = args[1];
        

    	csvmode.setfile(readerCsvFilePath);
    	List<Map<String, Double>> getmin_maxstd = csvmode.getmin_maxstd();
    	Map<String, Double> min = getmin_maxstd.get(0);
    	Map<String, Double> max_minstd = getmin_maxstd.get(1);
    	
        String[] xoh= {"nbxh","jjhklb","hydm","jyzt","time_scale","city","three","scale","x4"};
        String[] ccols= {"x5","x6","x7","x10","x11",
    			"x12","x13","x14","x18"};
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath);
        CsvReader csvReader = new CsvReader(readerCsvFilePath);
        
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        String[] head = csvReader.getHeaders(); //获取表头
        String[] heads =new String[xoh.length+ccols.length];
        for(int i = 0;i<xoh.length;i++) {
        	heads[i] = xoh[i];
        }
        for(int i = 0;i<ccols.length;i++) {
        	heads[i+xoh.length] = ccols[i];
        }
        csvWriter.writeRecord(heads);
        String[] s =new String[head.length];
        
        while(csvReader.readRecord()){

        	int i = 0;
        	for(String x:xoh) {
        		s[i++] = csvReader.get(x);
        	}
        	for(String x:ccols) {
        		
        			s[i++] = String.valueOf(((Double.valueOf(csvReader.get(x))-min.get(x))/max_minstd.get(x)));
        			if(s[i-1].equals("NaN")) {
        				s[i-1]="0";
        			}
        	}
            csvWriter.writeRecord(s);
        }
        csvReader.close();
        csvWriter.close();
    }


    public static void write_EA75(String[] args) throws Exception {
    	List<ArrayList<Double>> gmx = adjustm.getmatrix(args[0]);
    	
    	String readerCsvFilePath = args[1];
    			//"C:\\Users\\ABC\\Desktop\\Output/dc.csv";

    	String writerCsvFilePath = args[2];
    			//"C:\\Users\\ABC\\Desktop\\Output/EA75.csv";
       
    	String[] xoh= {"jjhklb","hydm","time_scale","city","three","scale","EA75"};
    	    
    	CsvWriter csvWriter = new CsvWriter(writerCsvFilePath);
        CsvReader csvReader = new CsvReader(readerCsvFilePath);
       	String jsonStr = "";
//		String path = jsontest.class.getClassLoader().getResource("resource/city.json").getPath();
//		File jsonFile = new File(path);
//		FileReader fileReader = new FileReader(jsonFile);
//		Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
//		int ch = 0;
//		StringBuffer sb = new StringBuffer();
//		while ((ch = reader.read()) != -1) {
//			sb.append((char) ch);
//		}
//		fileReader.close();
//		reader.close();
		InputStream inputStream =allmain.class.getClassLoader().getResourceAsStream("resource/city.json");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] b = new byte[10240];
		int n;
		while ((n = inputStream.read(b)) != -1) {
		    outputStream.write(b, 0, n);
		}
//		System.out.println(outputStream.toString());
		jsonStr = outputStream.toString();
		JSONObject jobj = JSON.parseObject(jsonStr);
		JSONObject jkey = jobj.getJSONObject("hydm");

		csvReader.readHeaders();

		csvWriter.writeRecord(xoh);
		String[] re =new String[xoh.length];
		String[] features = {"x4","x5","x6","x7","x10","x11",
				"x12","x13","x14","x18"};
		int[]ad = {2,3,4,5,8,9,10,11,12,16};
		int end =xoh.length-1;
        while(csvReader.readRecord()){

        	for(int i =0;i<xoh.length-1;i++) {
        		re[i] = csvReader.get(xoh[i]);
        	}
        	if(Integer.valueOf(csvReader.get("jyzt"))==0) {

        		re[end]="0";
                csvWriter.writeRecord(re);
                continue;
        	};
        	double res =0;
        	int i =(int)jkey.get(csvReader.get("hydm"));
        	int j =Integer.valueOf(csvReader.get("scale"));
        	List<Double> li=gmx.get(i*5+j-6);
        	res+=li.get(0);
        	res+=li.get(1);
        	for(int k =0;k<ad.length;k++) {         	       	
        		res+=Double.valueOf(csvReader.get(features[k]))*li.get(ad[k]);
        	}
        	re[end] = String.valueOf(res);
            csvWriter.writeRecord(re);
        }
        csvWriter.close();
        csvReader.close();
    }

    
    public static void GEA(String[] args) throws Exception {
    	

        String readerCsvFilePath = args[1];  
        //"C:\\Users\\ABC\\Desktop\\Output/EA75.csv";
        
        String writerCsvFilePath = args[2];
        
        File f=new File(writerCsvFilePath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
        CsvReader csvReader = new CsvReader(readerCsvFilePath,',',Charset.forName("UTF-8"));
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        double EA_75 = csvmode.get_EA(args[0]);
      //  System.out.println(EA_75);
        double i =0;
        double j =0;
		String[] hydms={"A","B", "C", "D", "E", "F", "G", "H","I","J","K","L","M"
		,"N","O","P","Q","R","S","T","Z"};
//		"one":["A"],
//		"two":["B", "C", "D", "E"],
//		"three":["F", "G", "H","I","J","K","L","M","N","O","P","Q","R","S","T","Z"]
		String[] fstindu = {"A"};		
		String[] sedindu = {"B", "C", "D", "E"};
		String[] trdindu = {"F", "G", "H","I","J","K","L","M","N","O","P","Q","R","S","T","Z"};		
		String[] scale= {"1","2","3","4","5"};
		String[] cities= {"武汉市","黄石市","十堰市","宜昌市","襄阳市",	
				"鄂州市","荆门市","孝感市","荆州市","黄冈市",
				"咸宁市","咸宁市","随州市","恩施市","仙桃市",	
				"潜江市","天门市","神农架林区"};
		String[] jjhklbs= {"01","02","03"};
		String[] threes= {"1","2","3"};
		String[] time_scales= {"1","2","3","4","5","6","7","8","9","10","11"};

		HashMap<String,Double> allEA =new HashMap<String,Double>();
		HashMap<String,Double> allEAs =new HashMap<String,Double>();
		HashMap<String,Double> hyEA =new HashMap<String,Double>();
		HashMap<String,Double> hyEAs =new HashMap<String,Double>();
		HashMap<String,Double> gmEA =new HashMap<String,Double>();
		HashMap<String,Double> gmEAs =new HashMap<String,Double>();
		HashMap<String,Double> cityEA =new HashMap<String,Double>();
		HashMap<String,Double> cityEAs =new HashMap<String,Double>();
		HashMap<String,Double> jjhklbEA =new HashMap<String,Double>();
		HashMap<String,Double> jjhklbEAs =new HashMap<String,Double>();
		HashMap<String,Double> threeEA =new HashMap<String,Double>();
		HashMap<String,Double> threeEAs =new HashMap<String,Double>();
		HashMap<String,Double> fstindEA =new HashMap<String,Double>();
		HashMap<String,Double> fstindEAs =new HashMap<String,Double>();
		HashMap<String,Double> secindEA =new HashMap<String,Double>();
		HashMap<String,Double> secindEAs =new HashMap<String,Double>();
		HashMap<String,Double> trdindEA =new HashMap<String,Double>();
		HashMap<String,Double> trdindEAs =new HashMap<String,Double>();
		HashMap<String,Double> time_scaleEA =new HashMap<String,Double>();
		HashMap<String,Double> time_scaleEAs =new HashMap<String,Double>();
		
		
		

		for(String hy:hydms) {
			hyEA.put(hy, 0.0);
			hyEAs.put(hy, 0.0);
		}
		
		
		for(String cs:cities) {
			cityEA.put(cs, 0.0);
			cityEAs.put(cs, 0.0);
		}
		
		for(String gm:scale) {
			gmEA.put(gm, 0.0);
			gmEAs.put(gm, 0.0);
		}

		for(String three:threes) {
			threeEA.put(three, 0.0);
			threeEAs.put(three, 0.0);
		}
		
		for(String jjhklb1:jjhklbs) {
			jjhklbEA.put(jjhklb1, 0.0);
			jjhklbEAs.put(jjhklb1, 0.0);
		}
		for(String sj:time_scales) {
			time_scaleEA.put(sj, 0.0);
			time_scaleEAs.put(sj, 0.0);
		}
		
		String hydm;
		String gms;
		String city;
		String jjhklb;
		String three;
		String time_scale;
		
		
        while(csvReader.readRecord()){
        	
        	i++;
        	hydm = csvReader.get("hydm");
        	gms = csvReader.get("scale");
        	city = csvReader.get("city");
        	jjhklb = csvReader.get("jjhklb");
        	three = csvReader.get("three");
        	time_scale = csvReader.get("time_scale");
//        	System.out.println(hydm+"\t"+gms+"\t"+city+"\t"+jjhklb);
        	
        	hyEA.replace(hydm, hyEA.get(hydm)+1);
    		gmEA.replace(gms, gmEA.get(gms)+1);
    		cityEA.replace(city, cityEA.get(city)+1);
    		jjhklbEA.replace(jjhklb, jjhklbEA.get(jjhklb)+1);
    		threeEA.replace(three, threeEA.get(three)+1);
    		time_scaleEA.replace(time_scale, time_scaleEA.get(time_scale)+1);
    		
        	if(Double.valueOf(csvReader.get("EA75"))==EA_75) {
        		hyEAs.replace(hydm, hyEAs.get(hydm)+0.75);
        		gmEAs.replace(gms, gmEAs.get(gms)+0.75);
        		cityEAs.replace(city, cityEAs.get(city)+0.75);
        		jjhklbEAs.replace(jjhklb, jjhklbEAs.get(jjhklb)+0.75);
        		threeEAs.replace(three, threeEAs.get(three)+0.75);
        		time_scaleEAs.replace(time_scale, time_scaleEAs.get(time_scale)+0.75);
        		j+=0.75;
        	}
        	
        	if(Double.valueOf(csvReader.get("EA75"))>EA_75) {
        		hyEAs.replace(hydm, hyEAs.get(hydm)+1);
        		gmEAs.replace(gms, gmEAs.get(gms)+1);
        		cityEAs.replace(city, cityEAs.get(city)+1);
        		jjhklbEAs.replace(jjhklb, jjhklbEAs.get(jjhklb)+1);
        		threeEAs.replace(three, threeEAs.get(three)+1);
        		time_scaleEAs.replace(time_scale, time_scaleEAs.get(time_scale)+1);
        		j++;
        	}

        }
        
        csvReader.close();


        allEA.put("#", i);
        allEAs.put("#", j);
        double hy1sum=0;
        double hy2sum=0;
        double hy3sum=0;
        for(String hy:fstindu) {
        	hy1sum+=hyEA.get(hy);
        	fstindEAs.put(hy, hyEA.get(hy));
        } 
        for(String hy:fstindu) {
        	fstindEA.put(hy, hy1sum);
        }
        for(String hy:sedindu) {
        	hy2sum+=hyEA.get(hy);
        	secindEAs.put(hy, hyEA.get(hy));
        } 
        for(String hy:sedindu) {
        	secindEA.put(hy, hy2sum);
        }
        for(String hy:trdindu) {
        	hy3sum+=hyEA.get(hy);
        	trdindEAs.put(hy, hyEA.get(hy));
        } 
        for(String hy:trdindu) {
        	trdindEA.put(hy, hy3sum);
        }

        
        writerescsv(writerCsvFilePath+"/行业活跃度.csv", "hydm", hydms, hyEA, hyEAs);
        writerescsv(writerCsvFilePath+"/规模活跃度.csv", "scale", scale, gmEA, gmEAs);
        writerescsv(writerCsvFilePath+"/地区活跃度.csv", "city", cities, cityEA, cityEAs);       
        writerescsv(writerCsvFilePath+"/经济户口活跃度.csv", "jjhklb", jjhklbs, jjhklbEA, jjhklbEAs);   
        writerescsv(writerCsvFilePath+"/三次产业活跃度.csv", "three", threes, threeEA, threeEAs);        
        writehycsv(writerCsvFilePath+"/分行业：一产行业数量及占比.csv", "hydm", fstindu, fstindEA, fstindEAs);        
        writehycsv(writerCsvFilePath+"/分行业：二产行业数量及占比.csv", "hydm", sedindu, secindEA, secindEAs);        
        writehycsv(writerCsvFilePath+"/分行业：三产行业数量及占比.csv", "hydm", trdindu, trdindEA, trdindEAs);        
        writerescsv(writerCsvFilePath+"/企业存续时间活跃度.csv", "time_scale", time_scales, time_scaleEA, time_scaleEAs);        
        writerescsv(writerCsvFilePath+"/整体活跃度.csv", "all", new String[] {"#"}, allEA, allEAs);        
       
        

		SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
        CsvWriter csvWriter = new CsvWriter(writerCsvFilePath+"/其他.csv",',',Charset.forName("UTF-8"));
        String ti = sf.format(contenst.getStdate())+"-"+sf.format(contenst.getEndate());
        String cont1[]= {ti+" 湖北省企业总量","注销量","吊销量","迁出量",ti+" 新成立企业数量"};
        String cont2[]= {contenst.getSumall()+"",""+contenst.getCancel(),""+contenst.getRevocation(),
        		""+contenst.getEmigration(),contenst.getNew_this_mon()+""};
        for(int i1=0;i1<cont1.length;i1++) {
        	String r[]= {cont1[i1],cont2[i1]};
        	csvWriter.writeRecord(r);
        }
        csvWriter.close();
        
    }
    
    public static void writerescsv(String path, String fl,String[]conl,
    		Map<String,Double> EA,Map<String,Double> EAs) throws IOException {
    	CsvWriter csvWriter = new CsvWriter(path,',',Charset.forName("UTF-8"));
        String con[] = {fl,"sum","proportion"};
       	csvWriter.writeRecord(con);
       	for(String c:conl) {
       		con[0] = c;
       		con[1] = String.valueOf(EA.get(c));
       		con[2] = String.valueOf(EAs.get(c)/EA.get(c));
       		csvWriter.writeRecord(con);
         }
         csvWriter.close();
    }
    
    public static void writehycsv(String path, String fl,String[]conl,
    		Map<String,Double> EA,Map<String,Double> EAs) throws IOException {
    	CsvWriter csvWriter = new CsvWriter(path,',',Charset.forName("UTF-8"));
        String con[] = {fl,"sum","proportion"};
       	csvWriter.writeRecord(con);
       	for(String c:conl) {
       		con[0] = c;
       		con[1] = String.valueOf(EAs.get(c));
       		con[2] = String.valueOf(EAs.get(c)/EA.get(c));
       		csvWriter.writeRecord(con);
         }
         csvWriter.close();
    }
    
}