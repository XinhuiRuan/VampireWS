package com.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class runJob {
	
	public Connection connectionDatabase() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/Project";
			String username = "root";
			String password = "123456";
			conn = (Connection) DriverManager.getConnection(url, username, password);
			if (conn == null) {
				System.out.println("Fail to connect to databse");
				conn.close();
			} 
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void run() throws InterruptedException{
		int job_id = 0;
		//int no_jobs_counter = 100;
		do{
			job_id = getNextJob();
			//System.out.println(job_id);
			if(job_id == 0){
				//if(no_jobs_counter==0){ return;}
				//no_jobs_counter--;
				Thread.sleep(1000);
			}
		}
		while(job_id == 0);
		
		String strategy = null;
		//strategy = "dis+11_7_16";
		//do{
			RandomStrategy random = new RandomStrategy();
			strategy = random.generateRandomStrategy(600);
			System.out.println(strategy);
			//strategy = getRandomStrategy();
		//}
//		while(alreadyRun(job_id,strategy));
		runJob(job_id,strategy);
	}

	public int getNextJob() {
		// get job information from DB and choose which job to run next
		// return 0 if there are no jobs to run
		int job_id = 0;
		try{
			Connection conn = connectionDatabase();
			Statement stmt = (Statement) conn.createStatement();
			String sql = "select job_id from Job where job_status is null";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			job_id = rs.getInt("job_id");
//			System.out.println(job_id);
//			while(rs.next()){
//				job_id = rs.getInt("job_id");
//				int problem_id = rs.getInt("problem_id");
//			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return job_id;
	}
	
	public boolean alreadyRun(int job_id, String strategy) {
		// go to DB and check if this job has already used this strategy
		boolean symbol = false;
		try{
			Connection conn = connectionDatabase();
			String sql = "select job_strategy from Job where job_id = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, job_id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String job_strategy = rs.getString("job_strategy");
			if(strategy.equals(job_strategy)){
				return symbol;
			}else{
				symbol = true;
				return symbol;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return symbol;
	}
	
	public void runJob(int job_id, String strategy) {
		// Use the commands code from above to
		// - get the problem for the job
		// - run vampire on the problem using the strategy
		// - save the results into the DB
		try {
			Runtime rt = Runtime.getRuntime();
//			String strategy = "dis+11_7_16";
			String time_limit = "10"; // in seconds
//			String[] commands={"/Users/mac/Desktop/VampireTool/Vampire4.1/vampire", "--mode","casc"};
			String[] commands = {"/Users/mac/Desktop/VampireTool/Vampire4.1/vampire","--decode",strategy,"-t",time_limit,"-szs","on","--bad_option","hard"};
			Process proc = rt.exec(commands);
			BufferedWriter in = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
			BufferedReader out = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			
			//write the problem to in
			Connection conn = connectionDatabase();
			String sql1 = "select problem_id from Job where job_id = ?";
			PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
			ps1.setInt(1, job_id);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			int problem_id = rs1.getInt("problem_id");
			String sql2 = "select problem_content from Problem where problem_id = ?";
			PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
			ps2.setInt(1, problem_id);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			String problem_content = rs2.getString("problem_content");
			//System.out.println(problem_content);
			
			in.write(problem_content);
//			ArrayList<String> lines = new ArrayList<>();
//			lines.add("fof(abc,axiom,p(a,b,c)).");
//			lines.add("fof(def,axiom,p(d,e,f)).");
//			lines.add("fof(ghi_or_jkl,axiom,( p(g,h,i) | p(j,k,l) )).");
//			lines.add("fof(xyz,question, ? [X,Y,Z] : p(X,Y,Z)).");
//			
//			for(String line : lines){
//				//System.out.println(line);
//				in.write(line);
//				in.newLine();
//			}
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
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	public static void main(String[] args) throws InterruptedException {	
		runJob run = new runJob();
//		run.getNextJob();
		run.run();
//		run.runJob(15, "dis+11_7_16");
//		while(true){
				
//		}
	}
	
//	public String getRandomStrategy() {
//	// Giles will write
//	return "";
//}
}
