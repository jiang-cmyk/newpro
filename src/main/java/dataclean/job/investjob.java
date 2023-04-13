package main.java.dataclean.job;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import main.java.dataclean.mapper.fzjgmapper;
import main.java.dataclean.mapper.investmapper;
import main.java.dataclean.reduce.fzjgreducer;
import main.java.dataclean.reduce.investreducer;


public class investjob extends Configured implements Tool{

	
	public static int work(String[] args) throws Exception {
		//启动job任务
		Configuration conf = new Configuration();
//		conf.set("mapred.textoutputformat.separator", ",");
		return ToolRunner.run(conf, new investjob(), args);
//		System.exit(run);
	}
	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//创建job任务对象
		nt.initLogRecord.initLog();
		Job job = Job.getInstance(super.getConf(), "investclean");
		job.setJarByClass(investjob.class);
		//配置job任务对象
		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(job, 
				new Path("file:///"+new File(args[0]).listFiles()[0].getAbsolutePath()));
			//	("file:///C:\\Users\\ABC\\Desktop\\2022.9月活跃度分析\\9月份活跃度数据\\企业关系人表/exportsql.20220925131951.gbk.csv"));
		job.setMapperClass(investmapper.class);
		//设置K2，V2类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);
				
		//reduce的处理方式
		job.setReducerClass(investreducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);
		
		//设置输出类型
		job.setOutputFormatClass(TextOutputFormat.class);
		Path outputPath = new Path(args[1]);

		outputPath.getFileSystem(super.getConf()).delete(outputPath, true);

		TextOutputFormat.setOutputPath(job,outputPath);
		//("file:///C:\\Users\\ABC\\Desktop\\Output/企业关系人"));
				//"hdfs://localhost:9000/wordcount_out"));
		
		boolean waitForCompletion = job.waitForCompletion(true);
		
		return waitForCompletion?0:1;
	}
	///newpro/src/main/java/mapred/testjobmain.java

}
