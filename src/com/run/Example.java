package com.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime rt = Runtime.getRuntime();
//			Process process = Runtime.getRuntime().exec("/Users/mac/Desktop/VampireTool/Vampire4.1/vampire");
//			String[] commandArgs={"open /Users/mac/Desktop/VampireTool/Vampire4.1/vampire",			
//						"/Users/mac/Desktop/VampireTool/Vampires/Problems/ans.p"};
//			String[] commands = {"open /Users/mac/Desktop/VampireTool/Vampire4.1/vampire"};
//			Process process = Runtime.getRuntime().exec("open /Users/mac/Desktop/VampireTool/Vampire4.1/vampire");				"fof(abc,axiom,p(a,b,c)).fof(def,axiom,p(d,e,f)).fof(ghi_or_jkl,axiom,( p(g,h,i) | p(j,k,l) )).fof(xyz,question, ? [X,Y,Z] : p(X,Y,Z)).");
			//Process process = rt.exec("open /Users/mac/Desktop/VampireTool/Vampire4.1/vampire");
//			Process process = rt.exec("system.exe", "-get t");
//			Process proc = Runtime.getRuntime().exec(commandArgs);
			//System.out.println(process);
			
			String strategy = "dis+11_7_16";
			String time_limit = "10"; // in seconds
			String[] commands={"/Users/mac/Desktop/VampireTool/Vampire4.1/vampire", "--mode","casc"};
//			String[] commands = {"/Users/mac/Desktop/VampireTool/Vampire4.1/vampire","--decode",strategy,"-t",time_limit,"-szs","on"};
			Process proc = rt.exec(commands);
			BufferedWriter in = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
			BufferedReader out = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			
			//write the problem to in
			ArrayList<String> lines = new ArrayList<>();
			lines.add("fof(abc,axiom,p(a,b,c)).");
			lines.add("fof(def,axiom,p(d,e,f)).");
			lines.add("fof(ghi_or_jkl,axiom,( p(g,h,i) | p(j,k,l) )).");
			lines.add("fof(xyz,question, ? [X,Y,Z] : p(X,Y,Z)).");
			
			for(String line : lines){
				//System.out.println(line);
				in.write(line);
				in.newLine();
			}
			in.flush();
			in.close();
			
			//read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while((s = out.readLine()) != null){
				System.out.println(s);
		}
			
			//read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while((s = err.readLine()) != null){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() throws InterruptedException{
		
		while(true){
			int job = 0;
			//int no_jobs_counter = 100;
			do{
				job = getNextJob();
				if(job==0){
					//if(no_jobs_counter==0){ return;}
					//no_jobs_counter--;
					Thread.sleep(1000);
				}
			}
			while(job==0);
			
			String strategy = null;
			do{
				strategy = getRandomStrategy();
			}
			while(alreadyRun(job,strategy));
			runJob(job,strategy);
		}
		
	}
	
	public void runJob(int job, String strategy){
		// Use the commands code from above to
		// - get the problem for the job
		// - run vampire on the problem using the strategy
		// - save the results into the DB
	}
	
	public String getRandomStrategy(){
		// Giles will write
		return "";
	}
	
	public int getNextJob(){
		//get job information from DB and choose which job to run next
		// return 0 if there are no jobs to run
		return 0;
	}
	public boolean alreadyRun(int job, String strategy){
		// go to DB and check if this job has already used this strategy
		return false;
	}
	
}