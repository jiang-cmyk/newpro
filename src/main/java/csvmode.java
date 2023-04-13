package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import joinery.DataFrame;

public class csvmode {

	static DataFrame df;
	
	public static void setfile(String filepath) throws IOException {
		df = DataFrame.readCsv(filepath);
	}
	
	
	/*
	 * col(1)-col(6)
	 * jjhklb+hydm+djjg+zczb+jyzt+clrq
	 */
	
	public static double getmedia(int col) {
		return (double) df.median().col(col).get(0);
	}
	
	//获取每一列最大最小值>归一化
	public static List<Map<String,Double>> getmin_maxstd() {
	//DataFrame df =DataFrame.readCsv("C:/Users/ABC/Desktop/Output/tostd/part-r-00000");
	String[] ccols= {"x5","x6","x7","x10","x11",
    			"x12","x13","x14","x18"};

	List<Map<String,Double>> res= new  ArrayList<Map<String,Double>>();
	DataFrame min =df.min();
	DataFrame max =df.max();
	Map<String, Double> X_min =new HashMap<String, Double>();
	Map<String, Double> X_max_min =new HashMap<String, Double>();
	
	for(String cco:ccols) {
		X_min.put(cco, (double)min.col(cco).get(0));
		X_max_min.put(cco, (double)max.col(cco).get(0)-X_min.get(cco));
	}
	res.add(X_min);
	res.add(X_max_min);
	return res;
	}
	
	public static double get_EA(String EA75path) throws IOException {
	
		DataFrame df1 =DataFrame.readCsv(EA75path);

		return (Double)df1.get(df1.col(0).size()/4, 0);
	
	}
}
