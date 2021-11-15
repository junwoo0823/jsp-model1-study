package test.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("Job Executed [" + sdf.format(new Date()) + "]");
		
	} // execute

}






