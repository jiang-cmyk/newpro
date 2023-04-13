package main.java;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.dataclean.job.changejob;
import main.java.dataclean.job.fjob;
import main.java.dataclean.job.fjobcz;
import main.java.dataclean.job.fzjgjob;
import main.java.dataclean.job.fzjgjobcz;
import main.java.dataclean.job.integrationjob;
import main.java.dataclean.job.investjob;
import main.java.dataclean.job.meandevjob;
import main.java.dataclean.job.mediajob;
import main.java.dataclean.job.qycsjob;
import main.java.dataclean.job.sumEAjob;
import main.java.dataclean.job.tostdjob;

public class allmain {

	public static void main(String args[]) throws Exception {
		// TODO Auto-generated constructor stub

		
		String Input = "C:\\Users\\jiang\\Documents\\WeChat Files\\wxid_pueqh3fdguqv22\\FileStorage\\File\\2023-02";
		String Output = "C:/Users/jiang/Desktop/Output";
		Date stdate =new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-24");
		Date endate =new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-03");
		
		String f="file:///";
		contenst.setStdate(stdate);
		contenst.setEndate(endate);
		
		String csvfile[]= {
				Input+"/主体登记",
				Output+"/ztdj.csv",
				"0,1,5,6,7,8,9,10,11,12,13"
		};
		readcsvfile.getform(csvfile);
		
		String qycsfile[]= {
				Input+"/迁移信息表",
				Output+"/qycs.csv",
				"1"
		};
		readcsvfile.getform(qycsfile);
		
		
		//求中位数的表
		String  media[]= {
				f+Output+"/ztdj.csv",
				f+Output+"/mode"
		};
		mediajob.work(media);
		
		csvmode.setfile(Output+"/mode/part-r-00000");	

		
		
		//主体登记
		String  f1[]= {
				f+Output+"/ztdj.csv",
				f+Output+"/主体登记"	
		
		};
		fjob.work(f1);	

		
		//变更情况
		String change[] = {
				Input+"/变更情况表",
				f+Output+"/变更情况"
				};
		changejob.work(change);


		
		
		//企业关系人
		String invest[]= {
				Input+"/企业关系人表",
				f+Output+"/企业关系人"				
		};
		investjob.work(invest);
		
		//迁移信息
		String qycs[]= {
			f+Output+"/qycs.csv",
			f+Output+"/迁移信息"			
		};
		qycsjob.work(qycs);
		

		
		
		//分支机构表 X7
		String fcz[]= {
				f+Output+"/主体登记/part-r-00000",
				f+Output+"/主体登记tep"
		};
		String fzjg[]= {
			Input+"/分支机构表",
			f+Output+"/分支机构tep"	
		};
		String fzjgcz[]= {
			f+Output+"/主体登记tep/part-r-00000",
			f+Output+"/分支机构tep/part-r-00000",
			f+Output+"/分支机构"
		};
		fjobcz.work(fcz);
		fzjgjob.work(fzjg);
		fzjgjobcz.work(fzjgcz);
//

		
		//数据整合
		String integration[]= {
			f+Output+"/变更情况/part-r-00000",
			f+Output+"/分支机构/part-r-00000",
			f+Output+"/企业关系人/part-r-00000",
			f+Output+"/迁移信息/part-r-00000",
			f+Output+"/主体登记/part-r-00000",
			f+Output+"/para1"
		};
		integrationjob.work(integration);
		
		//计算均值与方差
		String meandev[]= {
				f+Output+"/para1/part-r-00000",
				f+Output+"/meandev"
		};
		meandevjob.work(meandev);
		
		
		//非0数据标准化
		String tostd[]= {
				f+Output+"/para1/part-r-00000",
				f+Output+"/tostd"
		};
		adjustm.set_rsfpath(Output+"/meandev/part-r-00000");
		tostdjob.work(tostd);

		//归一化
		String max_minstd[]= {
				Output+"/tostd/part-r-00000",
				Output+"/dc.csv"
		};
		readcsvfile.max_minstd(max_minstd);
		
		String[] writeEA75={
			Output+"/tostd/part-r-00000",
			Output+"/dc.csv",
			Output+"/EA75.csv"
		};

		readcsvfile.write_EA75(writeEA75);
		
		
		String sumEA[]= {
			f+Output+"/EA75.csv",
			f+Output+"/EA75_"
		};
		sumEAjob.work(sumEA);
//		
		String []gea= {
				Output+"/EA75_/part-r-00000",
				Output+"/EA75.csv",
				Output+"/res"
		};
		readcsvfile.GEA(gea);
	}
}
