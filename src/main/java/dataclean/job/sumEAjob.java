package main.java.dataclean.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import main.java.dataclean.mapper.sumEAmapper;
import main.java.dataclean.reduce.sumEAreducer;


public class sumEAjob extends Configured implements Tool{

	
	public static int work(String[] args) throws Exception {
		//启动job任务
		Configuration conf = new Configuration();
//		conf.set("mapred.textoutputformat.separator", ",");
		return ToolRunner.run(conf, new sumEAjob(), args);
//		System.exit(run);
	}
	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//创建job任务对象
		nt.initLogRecord.initLog();
		Job job = Job.getInstance(super.getConf(), "chclean");
		job.setJarByClass(sumEAjob.class);
		//配置job任务对象
		job.setInputFormatClass(TextInputFormat.class);
		
		TextInputFormat.addInputPath(job,
				new Path(args[0]));
						//"file:///C:\\Users\\ABC\\Desktop\\Output\\EA75.csv"));
				
		

		//设置K2，V2类型
		job.setMapperClass(sumEAmapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
				
		//reduce的处理方式
		job.setReducerClass(sumEAreducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		//设置输出类型
		job.setOutputFormatClass(TextOutputFormat.class);
		Path outputPath = new Path(args[1]);

		outputPath.getFileSystem(super.getConf()).delete(outputPath, true);

		TextOutputFormat.setOutputPath(job,outputPath);
		//"file:///C:\\Users\\ABC\\Desktop\\Output\\EA75_"));

		
		boolean waitForCompletion = job.waitForCompletion(true);
		
		return waitForCompletion?0:1;
	}


}
