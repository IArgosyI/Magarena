package magic.test;

import magic.model.MagicDuel;
import magic.model.MagicGame;
import magic.model.MagicPlayer;
import magic.model.MagicPlayerDefinition;
import magic.model.MagicPlayerProfile;
import magic.model.phase.MagicMainPhase;

class TestVinesOfVastwood extends TestGameBuilder {
    public MagicGame getGame() {
        final MagicDuel duel=new MagicDuel();
        duel.setDifficulty(6);

        final MagicPlayerProfile profile=new MagicPlayerProfile("bgruw");
        final MagicPlayerDefinition player1=new MagicPlayerDefinition("Player",false,profile,15);
        final MagicPlayerDefinition player2=new MagicPlayerDefinition("Computer",true,profile,14);
        duel.setPlayers(new MagicPlayerDefinition[]{player1,player2});
        duel.setStartPlayer(0);

        final MagicGame game=duel.nextGame(true);
        game.setPhase(MagicMainPhase.getFirstInstance());
        final MagicPlayer player=game.getPlayer(0);
        final MagicPlayer opponent=game.getPlayer(1);

        MagicPlayer P = player;

        P.setLife(5);
        addToLibrary(P, "Swamp", 20);
        createPermanent(game,P,"Rupture Spire",false,9);
        addToHand(P, "Vines of Vastwood", 1);

        P = opponent;

        P.setLife(5);
        addToLibrary(P, "Mountain", 20);
        createPermanent(game,P,"Rupture Spire",false,9);
        createPermanent(game,P,"Grizzly Bears",false,1);
        addToHand(P, "Vines of Vastwood", 1);

        return game;
    }
}
