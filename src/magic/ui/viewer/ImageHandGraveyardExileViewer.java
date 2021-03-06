package magic.ui.viewer;

import magic.model.MagicCardList;
import magic.ui.GameController;
import magic.ui.theme.Theme;
import magic.ui.theme.ThemeFactory;
import magic.ui.widget.TabSelector;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;

public class ImageHandGraveyardExileViewer extends JPanel implements ChangeListener {

    private static final long serialVersionUID = 1L;

    private final ViewerInfo viewerInfo;
    private final TabSelector tabSelector;
    private final ImageCardListViewer cardListViewer;
    private final MagicCardList other = new MagicCardList();

    public ImageHandGraveyardExileViewer(final ViewerInfo aViewerInfo,final GameController controller) {
        viewerInfo = aViewerInfo;

        final Theme theme=ThemeFactory.getInstance().getCurrentTheme();

        setOpaque(false);
        setLayout(new BorderLayout(6,0));

        final String playerName=viewerInfo.getPlayerInfo(false).name;
        final String opponentName=viewerInfo.getPlayerInfo(true).name;

        tabSelector=new TabSelector(this,true);
        tabSelector.addTab(theme.getIcon(Theme.ICON_SMALL_HAND),"Hand : "+playerName);
        tabSelector.addTab(theme.getIcon(Theme.ICON_SMALL_GRAVEYARD),"Graveyard : "+playerName);
        tabSelector.addTab(theme.getIcon(Theme.ICON_SMALL_GRAVEYARD),"Graveyard : "+opponentName);
        tabSelector.addTab(theme.getIcon(Theme.ICON_SMALL_EXILE),"Exile : "+playerName);
        tabSelector.addTab(theme.getIcon(Theme.ICON_SMALL_EXILE),"Exile : "+opponentName);
        tabSelector.addTab(theme.getIcon(Theme.ICON_SMALL_HAND),"Other : "+playerName);
        add(tabSelector,BorderLayout.WEST);

        cardListViewer=new ImageCardListViewer(controller);
        add(cardListViewer,BorderLayout.CENTER);
    }

    public void setSelectedTab(final int selectedTab) {
        if (selectedTab>=0) {
            tabSelector.setSelectedTab(selectedTab);
        }
    }

    public void showCards(final MagicCardList cards) {
        other.clear();
        other.addAll(cards);
    }

    public void update() {
        if (cardListViewer!=null) {
            switch (tabSelector.getSelectedTab()) {
                case 0: cardListViewer.setCardList(viewerInfo.getPlayerInfo(false).hand,true); break;
                case 1: cardListViewer.setCardList(viewerInfo.getPlayerInfo(false).graveyard,false); break;
                case 2: cardListViewer.setCardList(viewerInfo.getPlayerInfo(true).graveyard,false); break;
                case 3: cardListViewer.setCardList(viewerInfo.getPlayerInfo(false).exile,false); break;
                case 4: cardListViewer.setCardList(viewerInfo.getPlayerInfo(true).exile,false); break;
                case 5: cardListViewer.setCardList(other,false); break;
            }
            repaint();
        }
    }

    @Override
    public void stateChanged(final ChangeEvent event) {
        update();
    }
}
