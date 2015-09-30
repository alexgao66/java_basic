package com.alex.elasticjob;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.AbstractPerpetualElasticJob;
import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;

@Component
public class MyElasticJob extends AbstractPerpetualElasticJob<Task> {
	
	private static final Logger log = LoggerFactory.getLogger(MyElasticJob.class);
	
	private static final ArrayList<Task> DBTasks = new ArrayList<Task>();
	
	static {
		DBTasks.add(new Task(1, "t-1"));
		DBTasks.add(new Task(2, "t-2"));
		DBTasks.add(new Task(3, "t-3"));
		DBTasks.add(new Task(4, "t-4"));
		DBTasks.add(new Task(5, "t-5"));
		DBTasks.add(new Task(6, "t-6"));
	}

	@Override
	protected List<Task> fetchData(JobExecutionMultipleShardingContext context) {
		log.info("context:{}", context.getShardingItemParameters());
		log.info("items:{}", context.getShardingItems());
		/*for(Map.Entry<Integer, String> entry : context.getShardingItemParameters()) {
			log.info("key:{}, value:{}", entry.getKey(), entry.getValue());
		}*/
		ArrayList<Task> tasks = new ArrayList<Task>();
		for(Task task : DBTasks) {
			if(!task.isProcessed()) {
				log.info("add task, id:{}, name:{}", task.getId(), task.getName());
				tasks.add(task);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return tasks;
	}

	@Override
	protected boolean processData(JobExecutionMultipleShardingContext context,
			Task task) {
		log.info("process task, id:{}, name:{}", task.getId(), task.getName());
		try {
			task.setProcessed(true);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

}
