package magic.ai;

import magic.model.MagicGame;
import magic.model.MagicGameLog;
import magic.model.MagicPlayer;
import magic.model.MagicRandom;
import magic.model.event.MagicEvent;

import java.util.ArrayList;
import java.util.List;

//AI that plays randomly
public class SimpleAI implements MagicAI {

    private final boolean LOGGING;

    public SimpleAI() {
        this(false);
    }

    private SimpleAI(final boolean log) {
        LOGGING = log || Boolean.getBoolean("debug");
    }

    private void log(final String message) {
        MagicGameLog.log(message);
        if (LOGGING||true) {
            System.err.println(message);
        }
    }

    public Object[] findNextEventChoiceResults(final MagicGame game, final MagicPlayer scorePlayer) {
        //get a list of choices
        MagicGame choiceGame = new MagicGame(game, scorePlayer);
        final MagicEvent event=choiceGame.getNextEvent();
        final List<Object[]> choices=event.getArtificialChoiceResults(choiceGame);
        choiceGame = null;

        final int size = choices.size();
        final String info = "SimpleAI " + scorePlayer.getIndex() + " (" + scorePlayer.getLife() + ")";

        if (size == 0) {
            throw new RuntimeException("No choice results");
        }

        //build a list of artificial choice results
        double max=Double.NEGATIVE_INFINITY;
        final List<ArtificialChoiceResults> achoices=new ArrayList<ArtificialChoiceResults>();
        ArtificialChoiceResults selected=null;
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
            double score = workerGame.getScore();
            if(score>=max){
            	max=score;
            	selected=achoices.get(achoices.size()-1);
            }
        }

        // Select a random artificial choice result
//        final int idx = MagicRandom.nextRNGInt(size);
//        final ArtificialChoiceResults selected=achoices.get(idx);
//        if (size >= 2) {
//            System.out.println(info);
//            System.out.println(""+max);
//            for (final ArtificialChoiceResults achoice : achoices) {
//            	if(achoice==selected){
//            		System.out.println("* "+achoice);
//            	}
//            	else System.out.println("  "+achoice);
//            }
//        } else {
//            //log(info + " " + selected);
//        }
        if (size >= 2) {
            log(info);
            for (final ArtificialChoiceResults achoice : achoices) {
                log((achoice==selected?"* ":"  ")+achoice);
            }
        } else {
            //log(info + " " + selected);
        }
        return game.map(selected.choiceResults);
    }
}
