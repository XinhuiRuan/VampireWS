package com.run;
import java.util.*;

public class RandomStrategy {
	static Map<String,String[]> values = new HashMap<>();
	  static Random rand = new Random();

	  static String getRand(String[] array){
	     return array[rand.nextInt(array.length)];
	  }
	  static String[] getSubset(String[] array){
	     String[] tmp = new String[array.length];
	     int index = 0;
	     for(int i=0;i<array.length;i++){
	       if(rand.nextBoolean()){ tmp[index++] = array[i]; }
	     }
	    return Arrays.copyOf(tmp,index); 
	  }
	  static String[] addToArray(String[] array, String... add){
	    String[] tmp = Arrays.copyOf(array,array.length+add.length);
	    for(int i=array.length;i<tmp.length;i++){
	      tmp[i]=add[i-array.length];
	    }
	    return tmp; 
	  }

	  static {
	    // preprocessing
	    values.put("nm",new String[]{"0","2","4","8","16","32","64","128","512","1024"});
	    values.put("ins",new String[]{"0","0","0","0","3","3","5","1"});
	    values.put("sos",new String[]{"all","all","off","off","off","on","on","theory"});
	    values.put("sstl",new String[]{"0","1","3","5","10"});
	    values.put("ep",new String[]{"R","RS","RST","RSTC","off","off","off","off","off"});
	    values.put("erd",new String[]{"input_only","off","input_only"});
	    values.put("fde",new String[]{"all","none","all"});
	    values.put("gsp",new String[]{"off","input_only","off"});
	    values.put("updr",new String[]{"on","on","off"});
	    values.put("tha",new String[]{"on","on","off","some"});
	    values.put("sd",new String[]{"0","1","2","3","4","5","7","10"});
	    values.put("ss",new String[]{"axioms","off","off","off","off","included","priority"});
	    //values.put("sgt"
	    values.put("st",new String[]{"1.0","1.2","1.5","2.0","3.0","5.0"});
	    values.put("newcnf",new String[]{"on","off","off"});
	    values.put("bce",new String[]{"off","off","off","on"});

	    // saturation
	    values.put("s",new String[]{"+0","+1","+2","+3","+4","+10","+11","+20","+21","+22","+30","+31","+32","+33","+34","+35","+1002","+1003","+1004","+1010","+1011","-1","-2","-3","-4","-10","-11","-1002","-1003","-1004","-1010","-20","-21","-22","-30","-31","-32","-33","-34","-35"});
	    values.put("awr",new String[]{"1","2:3","3:2","4:1","8:1","2:1","5:1","3:1","5:4","3","5","50","8","4","16","32","1024","64","128","12","20","7","28","24","6","40"});

	    values.put("lsd",new String[]{"0","100","200","1000","0","0","0","0","0","0","0","0"});
	    values.put("lma",new String[]{"off","off","off","off","off","on"});
	    values.put("lwlo",new String[]{"off","off","off","off","off","on"});
	    values.put("thi",new String[]{"off","all","strong","overlap","full","off","off","off"});
	    values.put("uwa",new String[]{"off","interpreted_only","one_side_interpreted","one_side_constant","all","ground","off","off","off","off","off","off"});
	    values.put("inst",new String[]{"off","off","off","off","off","on"});
	    values.put("bd",new String[]{"all","off","preordered","all","all","off"});
	    values.put("bs",new String[]{"on","off","on","off","unit_only","off"});
	    values.put("bsr",new String[]{"on","off","on","off","unit_only","off"});
	    values.put("br",new String[]{"on","off","on"});
	    values.put("cond",new String[]{"off","on","off","fast"});
	    values.put("drc",new String[]{"on","on","on","off"});
	    values.put("er",new String[]{"filter","known","off","off","off"});
	    values.put("fd",new String[]{"all","all","all","off","preordered"});
	    values.put("flr",new String[]{"on","off","off","off","off","off","off"});
	    values.put("fs",new String[]{"on","on","on","on","on","on","on","on","on","off"});
	    values.put("irw",new String[]{"on","off","off","off","off","off","off"});
	    values.put("etr",new String[]{"on","off","off","off","off","off","off"});
	    values.put("urr",new String[]{"ec_only","on","on","off","off"});
	    values.put("sfv",new String[]{"on","on","off"});
	    values.put("inw",new String[]{"off","off","off","on"});
	    values.put("lcm",new String[]{"standard","standard","predicate","reverse"});
	    values.put("nwc",new String[]{"1","1.1","1.2","1.3","1.5","1.7","2","2.5","3","4","5","10"});
	    values.put("sp",new String[]{"arity","occurence","reverse_arity","frequency","arity","occurence","reverse_arity","frequency","arity","occurence","reverse_arity","frequency","reverse_arity","reverse_frequency","weighted_frequency","reverse_weighted_frequency"});
	    values.put("spb",new String[]{"none","none","none","none","none","none","none","none","none","goal","units","goal_then_units"});

	    // Global Subsumption
	    values.put("gs",new String[]{"on","off"});
	    values.put("gsssp",new String[]{"propagation_only","propagation_only","propagation_only","full"});
	    values.put("gsem",new String[]{"off","on","randomized","randomized","randomized"});
	    values.put("gsaa",new String[]{"off","from_current","full_model","off","off","off"});

	    // instgen
	    values.put("igbrr",new String[]{"0.0","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0"});
	    values.put("igrr",new String[]{"128/1","64/1","32/1","16/1","8/1","4/1","2/1","1/1","1/2","1/4","1/8","1/16","1/32","1/64","1/128"});
	    values.put("igrp",new String[]{"100","200","400","700","1000","1400","2000","4000"});
	    values.put("igrpq",new String[]{"1.0","1.05","1.1","1.2","1.3","1.5","2.0"});
	    values.put("igs", new String[]{"0","1","2","3","4","10","1011","0","0","0","0","0"});
	    values.put("igwr",new String[]{"on","off"});

	    // avatar
	    values.put("av",new String[]{"on","off"});
	    values.put("sac", new String[]{"on","off","off"});
	    values.put("aac",new String[]{"ground","none","ground"});
	    values.put("acc",new String[]{"off","off","off","off","off","model","on"});
	    values.put("ccuc",new String[]{"all","all","all","first","small_ones"});
	    values.put("alpa",new String[]{"none","none","none","none","none","none","false","true"});
	    values.put("amm",new String[]{"off","sco","all","all","all"});
	    values.put("aer",new String[]{"on","on","on","on","on","off"});
	    values.put("afr",new String[]{"off","off","off","on"});
	    values.put("add",new String[]{"on","on","on","large","off"});
	    values.put("afp",new String[]{"0","1000","4000","10000","40000","100000"});
	    values.put("afq",new String[]{"1.0","1.1","1.2","1.4","2.0"});
	    values.put("anc",new String[]{"known","known","all","all_dependent","known","none"});
	    values.put("nicw",new String[]{"false","false","false","on"});
	    values.put("sffsmt",new String[]{"off","off","off","on"});

	    // sat solver
	    values.put("sas",new String[]{"minisat","minisat","vampire","z3"});

	    //fmb
	    values.put("fmbsr",new String[]{"1.0","1.0","1.1","1.6","1.8","1.2","1.4","2.0","1.5"});
	    values.put("fmbas",new String[]{"off","expand","group","predicate","function","group"});
	    values.put("fmbdsb",new String[]{"false","false","false","false","false","on"});
	    values.put("fmbswr",new String[]{"1","1","1","1","0","2","5","10"});
	    values.put("fmbes",new String[]{"contour","sbeam","contour","smt"});

	  }

	  static String optionsFrom(String[] opts){
	    String[] out = new String[opts.length]; 
	    for(int i=0;i<opts.length;i++){
	      String opt = opts[i];
	     // System.out.println(opt);
	      String value = getRand(values.get(opt));
	      out[i] = opt+"="+value;
	    }
	    return Arrays.toString(out).replaceAll("\\[|\\]", "").replaceAll(", ",":");
	  }

	  static String getPreprocessing(){
	    String[] preprocessing = new String[]{"nm","ins","sos","ep","erd","fde","gsp","updr","tha","ss","newcnf","bce"};
	    String[] chosen = getSubset(preprocessing);
	    for(String opt : chosen){
	      if(opt=="sos"){ chosen = addToArray(chosen,"sstl");}
	      if(opt=="ss"){ chosen = addToArray(chosen,"sd","st");}
	    }
	    return optionsFrom(chosen);
	  }
	  static String getSaturation(){
	    String[] saturation = {"lsd","lma","lwlo","thi","uwa","inst","bd","bs","bsr","br","cond","drc","er","fd","flr","fs","irw","etr","urr","sfv","inw","lcm","nwc","sp","spb"};
	    String[] chosen = getSubset(saturation);
	    boolean gs = rand.nextBoolean(); 
	    boolean av = rand.nextBoolean();
	    if(gs || av){ chosen = addToArray(chosen,"sas"); } 
	    if(gs){ chosen = addToArray(chosen,"gsssp","gsem","gsaa"); }
	    if(av){ chosen = addToArray(chosen,"sac","aac","acc","ccuc","alpa","amm","aer","afr","add","afp","afq","anc","nicw","sffsmt"); }
	    String base = (av ? "av=on" : "av=off") +":" + (gs ? "gs=on" : "gs=off");
	    String opts =  optionsFrom(chosen);
	    return base + (opts.isEmpty() ? "" : ":"+opts);
	  }

	  static String getInstGen(){
	    String[] inst = {"igbrr","igrr","igrp","igrpq","igs"};
	    String[] chosen = getSubset(inst);
	    boolean res = rand.nextBoolean();
	    String base = "igwr=off";
	    if(res){
	      String sat = getSaturation();
	      base = "igwr=on"+(sat.isEmpty() ? "" : ":"+sat);
	    }
	    else{
	      chosen = addToArray(chosen,"gs","gssp","gsem","gsaa");
	    }
	    String opts = optionsFrom(chosen);
	    return base + (opts.isEmpty() ? "" : ":"+opts);
	  }

	  static String getFMB(){
	    String[] fmb = {"fmbsr","fmbas","fmbdsb","fmbswr","fmbes"};
	    return optionsFrom(fmb);
	  }
	  

	  static String generateRandomStrategy(int timeLimit){

	    String[] algs = new String[]{"lrs","lrs","dis","ins","ott","fmb"};
	    String alg = getRand(algs); 
	    String pre = "+10_1";
	    if(alg!="fmb"){
	      String sel = getRand(values.get("s"));
	      String awr = getRand(values.get("awr"));
	      pre = sel+"_"+awr;
	    }
	    String prep = getPreprocessing();

	    String algSpec = ""; 
	    if(alg=="dis" || alg=="ott" | alg=="lrs"){
	      algSpec = getSaturation();
	    }
	    if(alg=="ins"){ algSpec = getInstGen(); }
	    if(alg=="fmb"){ algSpec = getFMB(); }

	    String sep = (prep.isEmpty() || algSpec.isEmpty()) ? "" : ":";
	 
	    return alg+pre+"_"+prep+sep+algSpec+"_"+Integer.toString(timeLimit);
	  }

//	  // Testing
//	  public static void main(String[] args){
//	    System.out.println(generateRandomStrategy(60));
//	  }
}
