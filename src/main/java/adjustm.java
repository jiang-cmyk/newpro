package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import joinery.DataFrame;

public class adjustm {

	public static String getmin_maxPath =null;
	
	public static void set_rsfpath(String path) {
		getmin_maxPath = path;
	}
	
	//根据均值和标准差计算每个数取值的上限和下线（）	
	public static List<HashMap<String,ArrayList<Double>>> getmin_max() throws IOException {
		// TODO Auto-generated method stub

		List<ArrayList<Double>> X_mean = new ArrayList<ArrayList<Double>>();
		List<ArrayList<Double>> X_std = new ArrayList<ArrayList<Double>>();
		HashMap<String,ArrayList<Double>> X_min = new HashMap<String,ArrayList<Double>>();
		HashMap<String,ArrayList<Double>> X_max = new HashMap<String,ArrayList<Double>>();

		String[]feature = {"X5", "X6", "X7", "X10", "X11", "X12", "X13", "X14", "X18"};
		String[]feature_mean = {"X5_mean", "X6_mean", "X7_mean", "X10_mean", "X11_mean", "X12_mean", "X13_mean", "X14_mean", "X18_mean"};
		String[]feature_std = {"X5_std", "X6_std", "X7_std", "X10_std", "X11_std", "X12_std", "X13_std", "X14_std", "X18_std"};
		String hydm[]=new String[21];
//			{"A","B", "C", "D", "E", "F", "G", "H","I","J","K","L","M"
//				,"N","O","P","Q","R","S","T","Z"};

    //    String readerCsvFilePath = "C:\\Users\\ABC\\Desktop\\Output\\meandev/part-r-00000";
        CsvReader csvReader = new CsvReader(getmin_maxPath, ',');
        csvReader.readHeaders();
        int hynum=0;
        
        //从文件获取均值与标准差
        while(csvReader.readRecord()) {
        	ArrayList<Double> hm = new ArrayList<Double>();
        	ArrayList<Double> hs = new ArrayList<Double>();
        	for(String fm:feature_mean) {
        		if(csvReader.get(fm).equals("")||csvReader.get(fm).equals("NaN")) {
        			hm.add(0.0);
        			continue;
        		}
        		hm.add(Double.valueOf(csvReader.get(fm)));
        	}
        	for(String fs:feature_std) {
        		if(csvReader.get(fs).equals("")||csvReader.get(fs).equals("NaN")) {
        			hs.add(0.0);
        			continue;
        		}
        		hs.add(Double.valueOf(csvReader.get(fs)));
        	}
        	hydm[hynum++]=csvReader.get(0);
        	X_mean.add(hm);
        	X_std.add(hs);
        
        }
        
        

        for(int i=0;i<X_mean.size();i++) {
        	ArrayList<Double> HYi_min=new ArrayList<Double>();
        	ArrayList<Double> HYi_max=new ArrayList<Double>();
        	List<Double> HYi_mean=X_mean.get(i);
        	List<Double> HYi_std=X_std.get(i);
        	for(int j=0;j<feature.length;j++) {
            	HYi_min.add(HYi_mean.get(j)-1.5*HYi_std.get(j));
            	HYi_max.add(HYi_mean.get(j)+1.5*HYi_std.get(j));
        	}
//        	System.out.println(HYi_min);
        	X_min.put(hydm[i],HYi_min);
        	X_max.put(hydm[i],HYi_max);
        }
        //分行业均值与标准差
        X_mean.clear();
        X_std.clear();
//
       
//        for(String i:hydm) {
//
//            System.out.println(X_min.get(i));
//        }
        List<HashMap<String,ArrayList<Double>>> min_max=new  ArrayList<HashMap<String,ArrayList<Double>>>();
        min_max.add(X_min); 
        min_max.add(X_max);   
        return min_max;
	}



	//计算调节参数并得出调节矩阵（行业数*规模数）
	public static List<ArrayList<Double>> getmatrix(String inputpath) throws IOException {
		// TODO Auto-generated method stub


		DataFrame df =DataFrame.readCsv(inputpath);
				//"C:/Users/ABC/Desktop/Output/tostd/part-r-00000");
		
		DataFrame col2 =df.mean();
		
		List<List<Double>> X_mean = new ArrayList<List<Double>>();
		
		//定义行业调节参数
		List<List<Double>> X_mean_s = new ArrayList<List<Double>>();
		
		
		
		double []X_mean_all=new double[9];

		DataFrame col = df.groupBy("hydm").mean();
		DataFrame col_s = df.groupBy("scale").mean();

		
		
		String[] ccols= {"x5","x6","x7","x10","x11",
				"x12","x13","x14","x18"};
		int i=0;
		for(String cco:ccols) {
			X_mean_all[i++] =(double) col2.col(cco).get(0);
		}
		
//		String[] hydms={"A","B", "C", "D", "E", "F", "G", "H","I","J","K","L","M"
//				,"N","O","P","Q","R","S","T","Z"};
		long [] scales = {1,2,3,4,5};
	
		int j=0;
		for(String cco:ccols) {
			List<Double> Xi_mean =new ArrayList<Double>();
			List<?> col3 = col.col(cco);
			for(Object c:col3) {
				double tzh = (double)c;
				if(tzh!=0) {
					Xi_mean.add(X_mean_all[j]/tzh);
				}else {
					Xi_mean.add(1.0);
				}
			}
			j++;
			X_mean.add(Xi_mean);
		}

		j=0;
		for(String cco:ccols) {
			List<Double> Xi_mean =new ArrayList<Double>();
//			List<?> col3 = col_s.col(cco);
			for(Object sc:scales) {
				double tzh = (double)col_s.get(sc, cco);
//				System.out.println(tzh+"\t"+X_mean_all[j]);
				if(tzh!=0) {
					Xi_mean.add(X_mean_all[j]/tzh);
				}else {
					Xi_mean.add(1.0);
				}
			}
			j++;
			X_mean_s.add(Xi_mean);
		}
		
	

		List<List<Double>> X_means = new ArrayList<List<Double>>();
		List<List<Double>> X_means_s = new ArrayList<List<Double>>();
		
		int[] fea= {3,4,5,8,9,10,11,12,16};
		int flag = 0;
		for(int mn = 0;mn<17;mn++) {
			if(fea[flag]==mn) {
				X_means.add(X_mean.get(flag));
				flag++;
			}else {
				List<Double> Xi_mean =new ArrayList<Double>();
				for(int n=0;n<21;n++) {
					Xi_mean.add(1.0);
				}
				X_means.add(Xi_mean);
			}
		}
		
		
		flag = 0;
		for(int mn = 0;mn<17;mn++) {
			if(fea[flag]==mn) {
				X_means_s.add(X_mean_s.get(flag));
				flag++;
			}else {
				List<Double> Xi_mean =new ArrayList<Double>();
				for(int n=0;n<5;n++) {
					Xi_mean.add(1.0);
				}
				X_means_s.add(Xi_mean);
			}
		}
//	获取到行业调节参数：X_means 与规模调节参数：X_means_s
		
		//给定权重
	double[]weight = {0.4, 0.15, 0.15, 0.05, 0.05, 0.05, 0.02, 0.02, 0.025, 0.025, 0.01, 0.01, 0.04, -0.03, -0.01, -0.01, 0.05};
	List<List<Double>> X1 = X_means_s;
	List<List<Double>> X2 = X_means;
	
	//调节矩阵
    List<ArrayList<Double>> X3 = new ArrayList<ArrayList<Double>>();
	
    

	//X2.get(0): X1_2的分行业调节参数  X2.get(0).get(1):第一个指标X1_2的第二个行业B 
    for(int i1 = 0;i1<X2.get(0).size();i1++) {		//遍历行业
    	// i=1:行业1下的所有规模的调节参数
    	for(int j1 = 0;j1<X1.get(0).size();j1++) {	//遍历规模
    		ArrayList<Double> xi =new ArrayList<Double>();
    		for(int k =0; k<X1.size();k++) {
    			xi.add(X1.get(k).get(j1)*X2.get(k).get(i1)*weight[k]);
    		}

    		X3.add(xi);
    	}
    }
    return X3;
	}

}
