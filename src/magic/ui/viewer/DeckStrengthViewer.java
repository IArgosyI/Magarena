package magic.ui.viewer;

import magic.ai.MagicAI;
import magic.ai.MagicAIImpl;
import magic.ai.RLAI;
import magic.data.DuelConfig;
import magic.data.GeneralConfig;
import magic.data.IconImages;
import magic.model.MagicDuel;
import magic.model.MagicGame;
import magic.ui.GameController;
import magic.ui.theme.ThemeFactory;
import magic.ui.widget.FontsAndBorders;
import magic.ui.widget.TexturedPanel;
import magic.ui.widget.TitleBar;

import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeckStrengthViewer extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    public static final Dimension PREFERRED_SIZE = new Dimension(270, 175);
    public static final Dimension START_BUTTON_SIZE = new Dimension(75, 50);

    private static final String PURPOSE=
        "<html><body>"+
        "Determine the win% of your deck vs opponent's for given number of games and AI level."+
        "</body></html>";

    private static final Border INPUT_BORDER=BorderFactory.createEmptyBorder(0,10,0,10);
    private static final Color HIGH_COLOR=new Color(0x23,0x8E,0x23);
    private static final Color MEDIUM_COLOR=new Color(0xFF,0x7F,0x00);
    private static final Color LOW_COLOR=new Color(0xEE,0x2C,0x2C);

    private static final MagicAI[] DEFAULT_AIS = {MagicAIImpl.RL.getAI(),MagicAIImpl.SAI.getAI()};

    private final MagicDuel duel;
    private final JProgressBar progressBar;
    private final JLabel gameLabel;
    private final JLabel strengthLabel;
    private final JTextField gamesTextField;
    private final JComboBox<Integer> difficultyComboBox;
    private final JButton startButton;
    private final Color textColor;
    private static CalculateThread calculateThread;

    public DeckStrengthViewer(final MagicDuel duel) {

        this.duel=duel;
        textColor=ThemeFactory.getInstance().getCurrentTheme().getTextColor();

        setPreferredSize(PREFERRED_SIZE);
        setBorder(FontsAndBorders.UP_BORDER);

        setLayout(new BorderLayout());

        final TitleBar titleBar=new TitleBar("Deck Strength");
        add(titleBar,BorderLayout.NORTH);

        final JPanel mainPanel=new TexturedPanel();
        mainPanel.setLayout(new BorderLayout(0,4));
        mainPanel.setBorder(FontsAndBorders.BLACK_BORDER_2);
        add(mainPanel,BorderLayout.CENTER);

        final JPanel topPanel=new JPanel(new BorderLayout(0,4));
        topPanel.setOpaque(false);

        final JLabel purposeLabel=new JLabel(PURPOSE);
        purposeLabel.setIcon(IconImages.STRENGTH);
        purposeLabel.setForeground(textColor);

        final GeneralConfig config=GeneralConfig.getInstance();

        final JPanel gamesPanel=new JPanel(new BorderLayout(6,0));
        gamesPanel.setBorder(INPUT_BORDER);
        gamesPanel.setOpaque(false);
        gamesTextField=new JTextField(String.valueOf(config.getStrengthGames()));
        gamesPanel.add(new JLabel(IconImages.TROPHY),BorderLayout.WEST);
        gamesPanel.add(gamesTextField,BorderLayout.CENTER);

        final JPanel difficultyPanel=new JPanel(new BorderLayout(6,0));
        difficultyPanel.setBorder(INPUT_BORDER);
        difficultyPanel.setOpaque(false);

        final Integer[] levels=new Integer[MagicAI.MAX_LEVEL];
        for (int level=1;level<=levels.length;level++) {
            levels[level-1]=level;
        }
        difficultyComboBox=new JComboBox<Integer>(levels);
        difficultyComboBox.setSelectedItem(Integer.valueOf(config.getStrengthDifficulty()));
        difficultyComboBox.setFocusable(false);
        difficultyPanel.add(new JLabel(IconImages.DIFFICULTY),BorderLayout.WEST);
        difficultyPanel.add(difficultyComboBox,BorderLayout.CENTER);

        final JPanel settingsPanel=new JPanel(new GridLayout(1,2));
        settingsPanel.setOpaque(false);
        settingsPanel.add(gamesPanel);
        settingsPanel.add(difficultyPanel);

        topPanel.add(purposeLabel,BorderLayout.CENTER);
        topPanel.add(settingsPanel,BorderLayout.SOUTH);
        mainPanel.add(topPanel,BorderLayout.NORTH);

        final JPanel centerPanel=new JPanel(new BorderLayout(4,0));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(FontsAndBorders.EMPTY_BORDER);
        mainPanel.add(centerPanel,BorderLayout.CENTER);

        gameLabel=new JLabel("");
        gameLabel.setFont(FontsAndBorders.FONT2);
        gameLabel.setForeground(textColor);
        gameLabel.setHorizontalAlignment(JLabel.CENTER);
        gameLabel.setPreferredSize(new Dimension(75,0));
        centerPanel.add(gameLabel,BorderLayout.WEST);

        strengthLabel=new JLabel("0 %");
        strengthLabel.setForeground(textColor);
        strengthLabel.setHorizontalAlignment(JLabel.CENTER);
        strengthLabel.setFont(FontsAndBorders.FONT5);
        centerPanel.add(strengthLabel,BorderLayout.CENTER);

        startButton=new JButton(IconImages.START);
        startButton.setFocusable(false);
        startButton.setPreferredSize(START_BUTTON_SIZE);
        startButton.addActionListener(this);
        centerPanel.add(startButton,BorderLayout.EAST);

        progressBar=new JProgressBar();
        progressBar.setMinimum(0);
        mainPanel.add(progressBar,BorderLayout.SOUTH);
    }

    public void halt() {
        if (calculateThread!=null) {
            calculateThread.halt();
            calculateThread=null;
        }
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (calculateThread!=null) {
            startButton.setEnabled(false);
            startButton.repaint();
            halt();
        } else {
            final GeneralConfig generalConfig=GeneralConfig.getInstance();
            generalConfig.setStrengthDifficulty(difficultyComboBox.getItemAt(difficultyComboBox.getSelectedIndex()));
            try { //parse number of games
                final int games=Integer.parseInt(gamesTextField.getText());
                if (games > 0 && games < 10000) {
                    generalConfig.setStrengthGames(games);
                } else {
                    gamesTextField.setText(String.valueOf(generalConfig.getStrengthGames()));
                }
            } catch (final NumberFormatException ex) {
                gamesTextField.setText(String.valueOf(generalConfig.getStrengthGames()));
            }
            startButton.setIcon(IconImages.STOP);
            startButton.repaint();
            calculateThread=new CalculateThread();
            calculateThread.start();
        }
    }

    private void setStrength(final int percentage) {

        strengthLabel.setText(percentage+" %");
        if (percentage>=60) {
            strengthLabel.setForeground(HIGH_COLOR);
        } else if (percentage>=30) {
            strengthLabel.setForeground(MEDIUM_COLOR);
        } else if (percentage>0) {
            strengthLabel.setForeground(LOW_COLOR);
        } else {
            strengthLabel.setForeground(textColor);
        }
        strengthLabel.repaint();
    }

    private class CalculateThread extends Thread {

        private final AtomicBoolean running=new AtomicBoolean(true);
        private GameController controller;

        public void run() {
            final GeneralConfig generalConfig=GeneralConfig.getInstance();
            final DuelConfig config=new DuelConfig(DuelConfig.getInstance());
            config.setNrOfGames(generalConfig.getStrengthGames());
            final MagicDuel testDuel=new MagicDuel(config,duel);
            testDuel.setDifficulty(generalConfig.getStrengthDifficulty());
            testDuel.setAIs(DEFAULT_AIS);
            progressBar.setMaximum(testDuel.getGamesTotal());
            progressBar.setValue(0);
            setStrength(0);
            
            int gameCount=0,gameWon=0;
            while (running.get()&&!testDuel.isFinished()) {
                gameLabel.setText("Game "+(testDuel.getGamesPlayed()+1));
                final MagicGame game=testDuel.nextGame(false);
                controller=new GameController(game);
                controller.runGame();
                progressBar.setValue(testDuel.getGamesPlayed());
                if (testDuel.getGamesPlayed()>0) {
                    final int percentage=(testDuel.getGamesWon()*100)/testDuel.getGamesPlayed();
                    setStrength(percentage);
                }
                
                if(gameCount%10==0){
	            	RLAI.saveTheta();
	        		
//	        		try {
//						writer = new PrintWriter("thetaHH.txt", "UTF-8");
//					} catch (FileNotFoundException
//							| UnsupportedEncodingException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	        		for(int i=0;i<501;i++){
//	        			for(int j=0;j<501;j++){
//	        				writer.print(""+RLAI.thetaHH[i][j]+" ");
//	        			}
//	        			writer.println();
//	        		}
//	        		writer.close();
	        		
	        		try {
	        			FileWriter fw=new FileWriter("result.txt",true);
	        			fw.write(""+(testDuel.getGamesWon()-gameWon)+" ");
	        			fw.write("\n");
	        			fw.close();
	        		} catch (IOException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	        		gameWon=testDuel.getGamesWon();
	        		
//	        		PrintWriter writer = null;
//	        		try {
//	        			writer = new PrintWriter("stateValue.txt", "UTF-8");
//	        		} catch (FileNotFoundException | UnsupportedEncodingException e) {
//	        			// TODO Auto-generated catch block
//	        			e.printStackTrace();
//	        		}
//	        		for(Long key:RLAI.stateValue.keySet()){
//	        			writer.println(""+key+" "+RLAI.stateValue.get(key));
//	        		}
//	        		writer.close();
	        		
                }gameCount++;
                
                try {
        			FileWriter fw=new FileWriter("changeLog.txt",true);
        			fw.write(""+ (RLAI.change/RLAI.cCount)+" ");
        			fw.write("\n");
        			fw.close();
        			RLAI.cCount=0;
        			RLAI.change=0;
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                
                
            }

            startButton.setIcon(IconImages.START);
            startButton.setEnabled(true);
            startButton.repaint();
            calculateThread=null;
        }

        public void halt() {
            running.set(false);
            if (controller!=null) {
                controller.haltGame();
            }
        }
    }

    public static boolean isRunning() {
        return calculateThread != null;
    }
}
