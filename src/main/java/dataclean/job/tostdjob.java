package main.java.dataclean.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import main.java.dataclean.mapper.fmapper;
import main.java.dataclean.mapper.meandevmapper;
import main.java.dataclean.mapper.tostdmapper;
import main.java.dataclean.reduce.freducer;
import main.java.dataclean.reduce.meandevreducer;
import main.java.dataclean.reduce.tostdreducer;


public class tostdjob extends Configured implements Tool{

	
	public static int work(String[] args) throws Exception {
		//启动job任务
		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.separator", ",");
		return  ToolRunner.run(conf, new tostdjob(), args);
	}
	@Override
	public int run(String[] args) throws Exception {
		//创建job任务对象
		nt.initLogRecord.initLog();
		Job job = Job.getInstance(super.getConf(), "fclean");
		job.setJarByClass(tostdjob.class);
		//配置job任务对象
		job.setInputFormatClass(TextInputFormat.class);
		TextInputFormat.addInputPath(job, new Path(args[0]));
				//"file:///C:\\Users\\ABC\\Desktop\\2022.9月活跃度分析\\9月份活跃度数据\\主体登记/exportsql.20220925122644.gbk.csv"));
		job.setMapperClass(tostdmapper.class);
		//设置K2，V2类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
				
		//reduce的处理方式
		job.setReducerClass(tostdreducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//设置输出类型
		job.setOutputFormatClass(TextOutputFormat.class);
		Path outputPath = new Path(args[1]);

		outputPath.getFileSystem(super.getConf()).delete(outputPath, true);

		TextOutputFormat.setOutputPath(job,outputPath);
						//"file:///C:\\Users\\ABC\\Desktop\\Output/主体登记"));
//		  outputPath.getFileSystem(conf).delete(outputPath, true);    // 避免job在运行的时候出现输出目录已经存在的异常
//	        FileOutputFormat.setOutputPath(job, outputPath);
		
		boolean waitForCompletion = job.waitForCompletion(true);
		
		return waitForCompletion?0:1;
	}


}
