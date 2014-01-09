package magic.ai;

import magic.model.MagicCard;
import magic.model.MagicCardList;
import magic.model.MagicColor;
import magic.model.MagicCounterType;
import magic.model.MagicGame;
import magic.model.MagicGameLog;
import magic.model.MagicPermanent;
import magic.model.MagicPermanentSet;
import magic.model.MagicPlayer;
import magic.model.event.MagicEvent;
import magic.model.phase.MagicPhase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//AI that plays randomly
public class RLAI implements MagicAI {

    private final boolean LOGGING;
    //public static Map<Long,Double> stateValue;
    //public static Map<Long,Double[]> es;
    //public static Map<Long,Double> ess;
    public static ArrayList<Double> theta=new ArrayList<Double>();
    public static Double[][] thetaH=new Double[601][];
    public static double[] et=new double[601];
    public final static double epsilon=0.9;
	public static final double gamma=0.4;
	public final static double alpha=0.1;
	public final static double lambda=0.6;
	public final static double lAlpha=0.1;
    public static double[] gz=new double[600];
    public static double change;
    public static int cCount=0;
    
    public RLAI() {
        this(false);
    }

    private RLAI(final boolean log) {
        LOGGING = log || Boolean.getBoolean("debug");
        //read stateValue from previously learned data
        File f= new File("stateValue.txt");
        BufferedReader in = null;
        
//        stateValue=new HashMap<Long,Double>();
//        try {
//			in = new BufferedReader(new FileReader(f));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        try {
//			while (in.ready()) {
//			  String s = in.readLine();
//			  String[] ss=s.split(" ");
//			  long h=Long.parseLong(ss[0]);
//			  double v=Double.parseDouble(ss[1]);
//			  stateValue.put(h, v);
//			}
//			in.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        //read theta
        f= new File("theta.txt");
        try {
			in = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			while (in.ready()) {
			  String s = in.readLine();
			  double v=Double.parseDouble(s);
			  theta.add(v);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        f= new File("thetaH.txt");
        try {
			in = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
        	int c=0;
			while (in.ready()) {
				thetaH[c]=new Double[601];
				String s = in.readLine();
				String[] ss=s.split(" ");
				for(int i=0;i<ss.length;i++){
					thetaH[c][i]=Double.parseDouble(ss[i]);
				}
				c++;
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //es=new HashMap<Long,Double[]>();
        //ess=new HashMap<Long,Double>();
        for(int i=0;i<601;i++)et[i]=0;
    }

    private void log(final String message) {
        MagicGameLog.log(message);
        if (LOGGING) {
            System.err.println(message);
        }
    }

    public Object[] findNextEventChoiceResults(final MagicGame game, final MagicPlayer scorePlayer) {
    	cCount++;
    	//get a list of choices
        MagicGame choiceGame = new MagicGame(game, scorePlayer);
        final MagicEvent event=choiceGame.getNextEvent();
        final List<Object[]> choices=event.getArtificialChoiceResults(choiceGame);
        choiceGame = null;

        final int size = choices.size();
        final String info = "RLAI " + scorePlayer.getIndex() + " (" + scorePlayer.getLife() + ")";

        if (size == 0) {
            throw new RuntimeException("No choice results");
        }
        
        if (size == 1) {
        	return game.map(choices.get(0));
        }
        
        //let's get started!!!!
        //first find if we have already has value for this state
        RLState rstate=new RLState();
        RLState rstateprime = null;
        double vs=0,vsprime;
        double reward=0;
//        rstate.getStateParameter(scorePlayer,game.getPhase());
//        Long k=rstate.getHashValue();
//        if(!stateValue.containsKey(k))stateValue.put(k, 0.0);
//        else stateValue.put(k, 1+stateValue.get(k));
        //vs=stateValue.get(k);
     

        
        //build a list of artificial choice results
        final List<ArtificialChoiceResults> achoices=new ArrayList<ArtificialChoiceResults>();
        double maxScore=Double.NEGATIVE_INFINITY;
        ArtificialChoiceResults selected=new ArtificialChoiceResults(choices.get(0));
        //epsilon greedy; epsilon sets to 0.8
        if(Math.random()>epsilon){
        	int ans=(int)(Math.random()*choices.size());
        	for (final Object[] choice : choices) {
        		achoices.add(new ArtificialChoiceResults(choice));
        		if(ans==0){
        			selected=achoices.get(achoices.size()-1);
        			MagicGame workerGame=new MagicGame(game,scorePlayer);
    	            workerGame.executeNextEvent(workerGame.map(choice));
     	            while(!workerGame.isFinished()){
        	            if (!workerGame.hasNextEvent()) {
        	        	   workerGame.executePhase();
        	        	   continue;
        	            }
        	            final MagicEvent eventR=workerGame.getNextEvent();

        	            if (!eventR.hasChoice()) {
        	            	workerGame.executeNextEvent();
        	                continue;
        	            }
        	            final List<Object[]> choiceResultsList=eventR.getArtificialChoiceResults(workerGame);

        	            final int nrOfChoices=choiceResultsList.size();

        	            assert nrOfChoices > 0 : "nrOfChoices is 0";

        	            if (nrOfChoices==1) {
        	                workerGame.executeNextEvent(choiceResultsList.get(0));
        	                continue;
        	            }
        	            break;
    	            }
     	            
     	            
     	            reward=workerGame.getScore()-game.getScore();
     	            reward=reward/ArtificialScoringSystem.WIN_GAME_SCORE;
     	            rstateprime=new RLState();

     	            //No F.A.
//     	            rstateprime.getStateParameter(workerGame.getPlayer(scorePlayer.getIndex()),workerGame.getPhase());
//     	            Long tmp=rstateprime.getHashValue();
//    	   	        //if(tmp!=k)System.out.println("\n\n\n\n\n\n"+tmp+" "+k);
//     	           	if(!stateValue.containsKey(tmp))stateValue.put(tmp, Math.random()/1000000.0);
//     	           	maxScore=stateValue.get(tmp);
//     	            //System.out.println("\n\n\n\nollllo"+(k-tmp)+" "+k+" "+tmp);
//     	            //System.out.println("player"+(game.getScorePlayer().getStateId()-workerGame.getScorePlayer().getStateId())+" "+game.getScorePlayer().getStateId()+" "+workerGame.getScorePlayer().getStateId());
//		        	selected=achoices.get(achoices.size()-1);
     	           	
		        	//F.A.
     	            maxScore=calStateValue(rstateprime.getStateParameter(workerGame.getPlayer(scorePlayer.getIndex()),workerGame.getPhase()));
        		}
        		ans--;
        	}
        	
        }
        else {
        	for (final Object[] choice : choices) {
	            achoices.add(new ArtificialChoiceResults(choice));
	            MagicGame workerGame=new MagicGame(game,scorePlayer);
	            workerGame.executeNextEvent(workerGame.map(choice));
	            
	            while(!workerGame.isFinished()){
    	            if (!workerGame.hasNextEvent()) {
    	        	   workerGame.executePhase();
    	        	   continue;
    	            }
    	            final MagicEvent eventR=workerGame.getNextEvent();

    	            if (!eventR.hasChoice()) {
    	            	workerGame.executeNextEvent();
    	                continue;
    	            }
    	            final List<Object[]> choiceResultsList=eventR.getArtificialChoiceResults(workerGame);

    	            final int nrOfChoices=choiceResultsList.size();

    	            assert nrOfChoices > 0 : "nrOfChoices is 0";

    	            if (nrOfChoices==1) {
    	                workerGame.executeNextEvent(choiceResultsList.get(0));
    	                continue;
    	            }
    	            break;
	            }
	            
 	            RLState t=new RLState();
	        	
	        	//No F.A.
//	        	t.getStateParameter(workerGame.getPlayer(scorePlayer.getIndex()),workerGame.getPhase());
//	   		    Long tmp=t.getHashValue();
//	   	        //if(tmp!=k)System.out.println("\n\n\n\n\n\n"+tmp+" "+k);
//	            if(!stateValue.containsKey(tmp))stateValue.put(tmp, Math.random()/1000000.0);
//	            if(stateValue.get(tmp)>=maxScore){
//		        	maxScore=stateValue.get(tmp);
//		        	selected=achoices.get(achoices.size()-1);
//		        	rstateprime=t.copyRLState(t);
//		        	reward=workerGame.getScore()-game.getScore();
//		        	reward=reward/ArtificialScoringSystem.WIN_GAME_SCORE;
//		        }
	        	
	        	//F.A.
	            double statevalue=calStateValue(t.getStateParameter(workerGame.getPlayer(scorePlayer.getIndex()),workerGame.getPhase()));
	            //System.out.println("SS"+statevalue+" "+(workerGame.getScore()-game.getScore()));
	            //if(choices.size()>1)
	            //System.out.println(" "+statevalue);
		        if(statevalue>=maxScore){
		        	maxScore=statevalue;
		        	selected=achoices.get(achoices.size()-1);
		        	rstateprime=t;
		        	reward=workerGame.getScore()-game.getScore();
     	            reward=reward/ArtificialScoringSystem.WIN_GAME_SCORE;
		        }
	            

	        }//System.out.println("!");
        }
        vsprime=maxScore;
        vs=calStateValue(rstate.getStateParameter(scorePlayer,game.getPhase()));
        //No F.A.
//        Long tmp=rstate.getHashValue();
//        //if(tmp!=rstateprime.getHashValue())System.out.println(":))))"+tmp+" "+rstateprime.getHashValue());
//        double delta=reward+gamma*vsprime-vs;
//        if(ess.get(tmp)==null)ess.put(tmp, 1.0);
//        else ess.put(tmp, ess.get(tmp)+1.0);
//        for(Long key:ess.keySet()){
//        	stateValue.put(key,stateValue.get(key)+alpha*delta*ess.get(key));
//        	ess.put(key, gamma*lambda*ess.get(key));
//        }
        
        if (size >= 2) {
            //System.out.println(info);
            for (final ArtificialChoiceResults achoice : achoices) {
            	if(achoice==selected){
            		//System.out.println("* "+achoice);
            		String s=""+achoice;
            		if(s.contains("pass")){
            			reward-=1000.0/ArtificialScoringSystem.WIN_GAME_SCORE;
            			//System.out.print("!");
            		}
            	}
            	//else System.out.println("  "+achoice);
            }
        } else {
            //log(info + " " + selected);
        }
        if(reward>0.6)reward=1;
        else if(reward<-0.6)reward=-1;
        else reward=0;
        //F.A.
        double delta=reward+gamma*vsprime-vs;
        //change = 0;
        //System.out.println("D"+delta+" "+reward+" "+vsprime+" "+vs);
        double[] d=new double[600];
        for(int i=0;i<600;i++){
        	et[i]=gamma*lambda*et[i]+rstate.keys[i];
        	d[i]=delta*theta.get(i)*gz[i]*(1-gz[i]);
        	change+=Math.abs(alpha*delta*gz[i]*(vs+1.0)/2.0*(1.0-(vs+1.0)/2.0)*2);
        	theta.set(i, alpha*delta*gz[i]*(vs+1.0)/2.0*(1.0-(vs+1.0)/2.0)*2+theta.get(i));
        }
        et[600]=gamma*lambda*et[600]+1;
    	//d[500]=delta*theta.get(500)*gz[500]*(1-gz[500]);
    	change+=Math.abs(alpha*delta*(vs+1.0)/2.0*(1.0-(vs+1.0)/2.0)*2);
    	theta.set(600, alpha*delta*(vs+1.0)/2.0*(1.0-(vs+1.0)/2.0)*2+theta.get(600));
        for(int i=0;i<600;i++){
        	for(int j=0;j<601;j++){
        		thetaH[i][j]+=alpha*d[i]*et[j];
        		change+=Math.abs(alpha*d[i]*et[j]);
        	}
        }
//        Double[] t=es.get(rstate.getHashValue());
//        if(t == null){
//        	t=new Double[500];
//        	for(int i=0;i<500;i++)t[i]=1.0;
//        }
//        else {
//        	for(int i=0;i<500;i++)t[i]+=1.0;
//        }
//        es.put(rstate.getHashValue(), t);
//        
//        for(Long key:es.keySet()){
//        	Double[] newEs=new Double[500];
//        	for(int i=0;i<500;i++){
//        		newEs[i]=gamma*lambda*es.get(key)[i]+rstate.keys[i];
//        		theta.set(i, alpha*delta*newEs[i]+theta.get(i));
//        		//System.out.println(""+newEs[i]+" "+es.get(key)[i]+" "+delta);
//        	}
//        	es.put(key, newEs);
//        	
//        }
        
        // Select a random artificial choice result        
        //final int idx = MagicRandom.nextRNGInt(size);
        //final ArtificialChoiceResults selected=achoices.get(idx);
        
        
//        if (size >= 2) {
//            log(info);
//            for (final ArtificialChoiceResults achoice : achoices) {
//                log((achoice==selected?"* ":"  ")+achoice);
//            }
//        } else {
//            //log(info + " " + selected);
//        }
//        try {
//			FileWriter fw=new FileWriter("E:\\Pow\\JAVA\\Test\\keysLog.txt",true);
//			for(int i=0;i<500;i++)fw.write(""+rstate.keys[i]+" ");
//			fw.write("\n");
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
//        try {
//			FileWriter fw=new FileWriter("E:\\Pow\\JAVA\\Test\\lifeLog.txt",true);
//			fw.write(""+scorePlayer.getLife()+" "+scorePlayer.getOpponent().getLife());
//			fw.write("\n");
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
//        PrintWriter writer = null;
//		try {
//			writer = new PrintWriter("E:\\Pow\\JAVA\\Test\\stateValue.txt", "UTF-8");
//		} catch (FileNotFoundException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(Long key:stateValue.keySet()){
//			writer.println(""+key+" "+stateValue.get(key));
//		}
//		writer.close();

        return game.map(selected.choiceResults);
        
    }
    
    public static void RLAILearn(final MagicGame game, final MagicPlayer scorePlayer, Object[] playerAction) {        
        //let's get started!!!!
        //first find if we have already has value for this state
        RLState rstate=new RLState();
        RLState rstateprime = new RLState();
        double vs=0,vsprime;
        double reward=0;
        
        MagicGame choiceGame = new MagicGame(game, scorePlayer);
        final MagicEvent event=choiceGame.getNextEvent();
        final List<Object[]> choices=event.getArtificialChoiceResults(choiceGame);
        choiceGame = null;
        
        final int size = choices.size();
        final String info = "RLAI " + scorePlayer.getIndex() + " (" + scorePlayer.getLife() + ")";

        if (size == 0) {
            throw new RuntimeException("No choice results");
        }
        
        if (size == 1) {
        	return;
        }

        MagicGame workerGame=new MagicGame(game,scorePlayer);
        workerGame.executeNextEvent(workerGame.map(playerAction));
         while(!workerGame.isFinished()){
            if (!workerGame.hasNextEvent()) {
        	   workerGame.executePhase();
        	   continue;
            }
            final MagicEvent eventR=workerGame.getNextEvent();

            if (!eventR.hasChoice()) {
            	workerGame.executeNextEvent();
                continue;
            }
            final List<Object[]> choiceResultsList=eventR.getArtificialChoiceResults(workerGame);

            final int nrOfChoices=choiceResultsList.size();

            assert nrOfChoices > 0 : "nrOfChoices is 0";

            if (nrOfChoices==1) {
                workerGame.executeNextEvent(choiceResultsList.get(0));
                continue;
            }
            break;
        }
         
         
         reward=workerGame.getScore()-game.getScore();
         reward=reward/ArtificialScoringSystem.WIN_GAME_SCORE;
         
         vsprime=calStateValue(rstateprime.getStateParameter(workerGame.getPlayer(0),workerGame.getPhase()));
         vs=calStateValue(rstate.getStateParameter(scorePlayer,game.getPhase()));

        
        //F.A.
        double delta=reward+gamma*vsprime-vs;
        //change = 0;
        //System.out.println("D"+delta+" "+reward+" "+vsprime+" "+vs);
        double[] d=new double[600];
        for(int i=0;i<600;i++){
        	et[i]=gamma*lambda*et[i]+rstate.keys[i];
        	d[i]=delta*theta.get(i)*gz[i]*(1-gz[i]);
        	//change+=Math.abs(lAlpha*delta*gz[i]*(vs+1.0)/2.0*(1.0-(vs+1.0)/2.0)*2);
        	theta.set(i, lAlpha*delta*gz[i]*(vs+1.0)/2.0*(1.0-(vs+1.0)/2.0)*2+theta.get(i));
        }
        et[600]=gamma*lambda*et[600]+1;
    	//d[500]=delta*theta.get(500)*gz[500]*(1-gz[500]);
    	//change+=Math.abs(lAlpha*delta);
    	theta.set(600, lAlpha*delta+theta.get(600));
        for(int i=0;i<600;i++){
        	for(int j=0;j<601;j++){
        		thetaH[i][j]+=lAlpha*d[i]*et[j];
        		//change+=Math.abs(d[i]*et[j]);
        	}
        }
        cCount++;
        //if(cCount%10000==0)saveTheta();
        
    }
    
    private static double calStateValue(double[] stateParameter) {
		// TODO Auto-generated method stub
    	double sum=0;
//    	for(int i=0;i<500;i++){
//    		if(stateParameter[i]<1E-6)continue;
//    		sum+=stateParameter[i]*theta.get(i);
//    	}
		//System.out.println("Sum:"+sum+" "+stateParameter[0]+" "+theta.get(0));

    	for(int i=0;i<600;i++){
    		double s=0;
    		for(int j=0;j<600;j++){
    			s+=stateParameter[j]*thetaH[i][j];
    		}
    		s+=thetaH[i][600];
    		gz[i]=g(s);
    		sum+=gz[i]*theta.get(i);
    	}
    	
    	sum+=theta.get(600);
    	
    	return 2.0*g(sum)-1.0;
	}

	private static Double g(double s) {
		// TODO Auto-generated method stub
		return 1.0/(1.0+Math.pow(Math.E, -s));
	}

	public static void saveTheta(){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("theta.txt", "UTF-8");
		} catch (FileNotFoundException
				| UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<601;i++){
			writer.println(""+RLAI.theta.get(i));
		}
		writer.close();
		
		try {
			writer = new PrintWriter("thetaH.txt", "UTF-8");
		} catch (FileNotFoundException
				| UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<601;i++){
			for(int j=0;j<601;j++){
				writer.print(""+RLAI.thetaH[i][j]+" ");
			}
			writer.println();
		}
		writer.close();
	}
}


