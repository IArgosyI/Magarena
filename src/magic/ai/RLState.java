package magic.ai;

import java.util.Collections;
import java.util.Comparator;

import magic.model.MagicCard;
import magic.model.MagicCardList;
import magic.model.MagicColor;
import magic.model.MagicCounterType;
import magic.model.MagicPermanent;
import magic.model.MagicPermanentSet;
import magic.model.MagicPlayer;
import magic.model.phase.MagicPhase;

public class RLState{
	//RL data
	public MagicCardList cardsInHand;
	public MagicPermanentSet RLPermanents;
	//public int convertedMana;
	//public int blue,black,white,red,green;
	
	//opponent data
	public int convertedMana;
	public int blue=0,black=0,white=0,red=0,green=0;
	public int noCardsinHand;
	public MagicPermanentSet opponentPermanents;//no Land!
	//public int[] creaturePower,creatureToughness,creatureValue,creatureColor;
	//public int[] permanentValue,permanentType;
	
	public RLState(){
		keys=new double[600];
		/*
		Comparator<MagicPermanent> permanentComparator=new Comparator<MagicPermanent>(){
			@Override
			public int compare(MagicPermanent arg0, MagicPermanent arg1) {
				// TODO Auto-generated method stub
				if(arg0.getConvertedCost()!=arg1.getConvertedCost()){
					return arg0.getConvertedCost()>arg1.getConvertedCost()?-1:1;
				}
				else{
					return arg0.getName().compareTo(arg1.getName());
				}
			}
			
		};
		*/
	}
	public RLState copyRLState(RLState rl){
		RLState r = new RLState();
		for(int i=0;i<600;i++)r.keys[i]=rl.keys[i];
		return r;
	}
	
	public double[] keys;
	
	public double[] getStateParameter(MagicPlayer player, MagicPhase phase){ 
		cardsInHand=player.getHand();
		RLPermanents=player.getPermanents();
		MagicPlayer opponent=player.getOpponent();
		noCardsinHand=opponent.getHandSize();
		opponentPermanents=opponent.getPermanents();
		for(int i=0;i<600;i++)keys[i]=-10.0;
		int count=0;
		//sort cardsInHand
		Comparator<MagicCard> magicCardComparator=new Comparator<MagicCard>(){
			@Override
			public int compare(MagicCard arg0, MagicCard arg1) {
				// TODO Auto-generated method stub
				if(arg0.getCardDefinition().getScore()!=arg1.getCardDefinition().getScore()){
					return arg0.getConvertedCost()>arg1.getConvertedCost()?-1:1;
				}
				else{
					return arg0.getName().compareTo(arg1.getName());
				}
			}
			
		};
		Collections.sort(cardsInHand,magicCardComparator);
		keys[count++]=(phase.getType().ordinal()-5.67)/3.02;
		keys[count++]=(player.getLife()-10.5)/5.92;
		for (final MagicCard card : cardsInHand) {
			//what to store...
            keys[count++] = (Math.log(card.getCardDefinition().getIndex())-7.27)/0.9;
        }
		count=10;
		
		for (final MagicPermanent permanent : RLPermanents) {
            //keys[count++] = (int)permanent.getStateId();
			if(permanent.getCardDefinition().getIndex()>0.0001)keys[count++] = (Math.log(permanent.getCardDefinition().getIndex())-7.51)/0.68;
			else keys[count++]=-15/0.68;
			
			if(permanent.getStateFlags()>0.0001)keys[count++] = (permanent.getStateFlags()-8193.485)/3.06;
			else keys[count++] = (8180-8193.485)/3.06;
			
			keys[count++] = permanent.getDamage();
			keys[count++] = permanent.getPreventDamage();
			
			
			keys[count++] = permanent.getEquippedCreature().getCardDefinition().getIndex();
			if(keys[count-1]>0.00001)keys[count-1]-=3800;
			keys[count++] = permanent.getEnchantedCreature().getCardDefinition().getIndex();
			if(keys[count-1]>0.00001)keys[count-1]-=3800;
			keys[count++] = permanent.getBlockedCreature().getCardDefinition().getIndex();
			if(keys[count-1]>0.00001)keys[count-1]-=3800;
			
			keys[count] += permanent.getCounters(MagicCounterType.PlusOne);
			keys[count] += permanent.getCounters(MagicCounterType.MinusOne);
			keys[count] += permanent.getCounters(MagicCounterType.Charge);
			keys[count] += permanent.getCounters(MagicCounterType.Bribery);
			keys[count] += permanent.getCounters(MagicCounterType.Feather);
			keys[count] += permanent.getCounters(MagicCounterType.Gold);
			count++;
			if(permanent.getCounters(MagicCounterType.PlusOne)>0)keys[count++]=-2;
			else if(permanent.getCounters(MagicCounterType.MinusOne)>0)keys[count++]=-1;
			else if(permanent.getCounters(MagicCounterType.Charge)>0)keys[count++]=0;
			else if(permanent.getCounters(MagicCounterType.Bribery)>0)keys[count++]=1;
			else if(permanent.getCounters(MagicCounterType.Feather)>0)keys[count++]=2;
			else if(permanent.getCounters(MagicCounterType.Gold)>0)keys[count++]=3;
			else keys[count++]=-10;
        }
		count=200;//15*13+10 allow 15 permanents
		keys[count++]=(opponent.getLife()-10.5)/5.92;
		keys[count++]=(noCardsinHand-5.85)/1.37;
/*		keys[count++]=convertedMana;
		
*/		
		for (final MagicPermanent permanent : opponentPermanents) {
			if(count>590)break;
			//keys[count++] = (int)permanent.getStateId();
			if(permanent.getCardDefinition().getIndex()>0.0001)keys[count++] = (Math.log(permanent.getCardDefinition().getIndex())-7.51)/0.68;
			else keys[count++]=-15/0.68;
			
			if(permanent.getStateFlags()>0.0001)keys[count++] = (permanent.getStateFlags()-8193.485)/3.06;
			else keys[count++] = (8180-8193.485)/3.06;
			
			keys[count++] = permanent.getDamage();
			keys[count++] = permanent.getPreventDamage();
			
			
			keys[count++] = permanent.getEquippedCreature().getCardDefinition().getIndex();
			if(keys[count-1]>0.00001)keys[count-1]-=3800;
			keys[count++] = permanent.getEnchantedCreature().getCardDefinition().getIndex();
			if(keys[count-1]>0.00001)keys[count-1]-=3800;
			keys[count++] = permanent.getBlockedCreature().getCardDefinition().getIndex();
			if(keys[count-1]>0.00001)keys[count-1]-=3800;
			
			keys[count] += permanent.getCounters(MagicCounterType.PlusOne);
			keys[count] += permanent.getCounters(MagicCounterType.MinusOne);
			keys[count] += permanent.getCounters(MagicCounterType.Charge);
			keys[count] += permanent.getCounters(MagicCounterType.Bribery);
			keys[count] += permanent.getCounters(MagicCounterType.Feather);
			keys[count] += permanent.getCounters(MagicCounterType.Gold);
			count++;
			if(permanent.getCounters(MagicCounterType.PlusOne)>0)keys[count++]=-2;
			else if(permanent.getCounters(MagicCounterType.MinusOne)>0)keys[count++]=-1;
			else if(permanent.getCounters(MagicCounterType.Charge)>0)keys[count++]=0;
			else if(permanent.getCounters(MagicCounterType.Bribery)>0)keys[count++]=1;
			else if(permanent.getCounters(MagicCounterType.Feather)>0)keys[count++]=2;
			else if(permanent.getCounters(MagicCounterType.Gold)>0)keys[count++]=3;
			else keys[count++]=-10;
        }
		
		
//		for (final MagicPermanent permanent : opponentPermanents) {
//			//if(permanent.isLand())keys[count++]=permanent.getManaId();
//			blue+=permanent.getCardDefinition().getManaSource(MagicColor.Blue);
//			black+=permanent.getCardDefinition().getManaSource(MagicColor.Black);
//			green+=permanent.getCardDefinition().getManaSource(MagicColor.Green);
//			red+=permanent.getCardDefinition().getManaSource(MagicColor.Red);
//			white+=permanent.getCardDefinition().getManaSource(MagicColor.White);
//		}
//		keys[count++]=(blue-1.0)/3.0;
//		keys[count++]=(black-1.0)/3.0;
//		keys[count++]=(white-1.0)/3.0;
//		keys[count++]=(red-1.0)/3.0;
//		keys[count++]=(green-1.0)/3.0;
//		for (final MagicPermanent permanent : opponentPermanents) {
//			if(permanent.isLand())continue;
//            keys[count++] = (permanent.getCardScore()-350.0)/74.42;
//            keys[count++] = permanent.getConvertedCost()-2.11;
//			
//            if(permanent.isCreature())keys[count++] = 1;
//            else if(permanent.isEquipment())keys[count++] = 2;
//            else if(permanent.isEnchantment())keys[count++] = 3;
//            else if(permanent.isArtifact())keys[count++] = 4;
//            else if(permanent.isPlaneswalker())keys[count++] = 5;
//            else keys[count++]=0;
//            keys[count-1]=(keys[count-1]-1.36)/0.84;
//            
//            keys[count++] = permanent.getToughness()-2;
//            keys[count++] = (permanent.getPower()-1.95)/0.67;
//            keys[count++] = permanent.getDamage();
//            keys[count++] = permanent.getPreventDamage();
//            if(permanent.getStateFlags()>0.0001)keys[count++] = (permanent.getStateFlags()-8193.485)/3.06;
//			else keys[count++] = (8190-8193.485)/3.06;
//            
//			keys[count] += permanent.getCounters(MagicCounterType.PlusOne);
//			keys[count] += permanent.getCounters(MagicCounterType.MinusOne);
//			keys[count] += permanent.getCounters(MagicCounterType.Charge);
//			keys[count] += permanent.getCounters(MagicCounterType.Bribery);
//			keys[count] += permanent.getCounters(MagicCounterType.Feather);
//			keys[count] += permanent.getCounters(MagicCounterType.Gold);
//			count++;
//			if(permanent.getCounters(MagicCounterType.PlusOne)>0)keys[count++]=-2;
//			else if(permanent.getCounters(MagicCounterType.MinusOne)>0)keys[count++]=-1;
//			else if(permanent.getCounters(MagicCounterType.Charge)>0)keys[count++]=0;
//			else if(permanent.getCounters(MagicCounterType.Bribery)>0)keys[count++]=1;
//			else if(permanent.getCounters(MagicCounterType.Feather)>0)keys[count++]=2;
//			else if(permanent.getCounters(MagicCounterType.Gold)>0)keys[count++]=3;
//			else keys[count++]=-3;
//        }
		//for(int i=0;i<600;i++)keys[i]/=10.0;
		//for(int i=0;i<500;i++)keys[i]/=20.0;
		return keys;
	}
	
	public long getHashValue(){
		long[] tmp=new long[600];
		for(int i=0;i<600;i++)tmp[i]=(long)(keys[i]*1000);
		return magic.MurmurHash3.hash(tmp);
	}
}