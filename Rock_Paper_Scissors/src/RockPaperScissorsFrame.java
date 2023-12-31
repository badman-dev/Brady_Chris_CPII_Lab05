import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RockPaperScissorsFrame extends JFrame {
    JPanel topPnl;
    JPanel titlePnl;
    JPanel actionPnl;
    JPanel statsPnl;
    JPanel resultsPnl;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;
    JLabel titleLbl;
    JLabel playerWinLbl;
    JLabel compWinLbl;
    JLabel tieLbl;
    JTextField playerWinTf;
    JTextField compWinTf;
    JTextField tieTf;
    int playerWins = 0;
    int compWins = 0;
    int ties = 0;
    JTextArea resultsArea;
    JScrollPane resultsScroll;

    public RockPaperScissorsFrame() {
        createActionPanel();
        createStatsPanel();
        createResultsPanel();

        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        setTitle("Rock Paper Scissors Game");
        setSize((int)(screenSize.width * .75), (int)(screenSize.height * .75));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createActionPanel() {
        actionPnl = new JPanel();
        actionPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        rockBtn = new JButton(new ImageIcon("src/rock.png"));
        paperBtn = new JButton(new ImageIcon("src/paper.png"));
        scissorsBtn = new JButton(new ImageIcon("src/scissors.png"));
        quitBtn = new JButton(new ImageIcon("src/quit.png"));
        rockBtn.addActionListener((ActionEvent ae) -> PlayGame(0));
        paperBtn.addActionListener((ActionEvent ae) -> PlayGame(1));
        scissorsBtn.addActionListener((ActionEvent ae) -> PlayGame(2));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        actionPnl.add(rockBtn);
        actionPnl.add(paperBtn);
        actionPnl.add(scissorsBtn);
        actionPnl.add(quitBtn);
        add(actionPnl, BorderLayout.NORTH);
    }

    private void createStatsPanel() {
        statsPnl = new JPanel();
        playerWinLbl = new JLabel("Player Wins");
        playerWinLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        compWinLbl = new JLabel("Computer Wins");
        compWinLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        tieLbl = new JLabel("Ties");
        tieLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        playerWinTf = new JTextField("0", 4);
        playerWinTf.setEditable(false);
        playerWinTf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        compWinTf = new JTextField("0", 4);
        compWinTf.setEditable(false);
        compWinTf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        tieTf = new JTextField("0", 4);
        tieTf.setEditable(false);
        tieTf.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        statsPnl.add(playerWinLbl);
        statsPnl.add(playerWinTf);
        statsPnl.add(compWinLbl);
        statsPnl.add(compWinTf);
        statsPnl.add(tieLbl);
        statsPnl.add(tieTf);
        add(statsPnl, BorderLayout.CENTER);
    }

    private void createResultsPanel() {
        resultsPnl = new JPanel();
        resultsArea = new JTextArea(28, 40);
        resultsScroll = new JScrollPane(resultsArea);

        resultsPnl.add(resultsScroll);
        add(resultsPnl, BorderLayout.SOUTH);
    }

    private void PlayGame(int playerChoice) {
        int compChoice = (int)Math.floor(Math.random() * (3));
        switch (playerChoice) {
            case 0: //Player Rock
                switch (compChoice) {
                    case 0: //Computer Rock
                        UpdateResults("Rock versus Rock (Tie Game)\n");
                        AddWin(2);
                        break;
                    case 1: //Computer Paper
                        UpdateResults("Paper covers Rock (Computer Wins)\n");
                        AddWin(1);
                        break;
                    case 2: //Computer Scissors
                        UpdateResults("Rock breaks Scissors (Player Wins)\n");
                        AddWin(0);
                        break;
                }
                break;
            case 1: //Player Paper
                switch (compChoice) {
                    case 0: //Computer Rock
                        UpdateResults("Paper covers Rock (Player Wins)\n");
                        AddWin(0);
                        break;
                    case 1: //Computer Paper
                        UpdateResults("Paper versus Paper (Tie Game)\n");
                        AddWin(2);
                        break;
                    case 2: //Computer Scissors
                        UpdateResults("Scissors cut Paper (Computer Wins)\n");
                        AddWin(1);
                        break;
                }
                break;
            case 2: //Player Scissors
                switch (compChoice) {
                    case 0: //Computer Rock
                        UpdateResults("Rock breaks Scissors (Computer Wins)\n");
                        AddWin(1);
                        break;
                    case 1: //Computer Paper
                        UpdateResults("Scissors cut Paper (Player Wins)\n");
                        AddWin(0);
                        break;
                    case 2: //Computer Scissors
                        UpdateResults("Scissors versus Scissors (Tie Game)\n");
                        AddWin(2);
                        break;
                }
                break;
        }
        UpdateStats();
    }

    private void UpdateResults(String result) {
        resultsArea.append(result);
    }

    private void AddWin(int winner) {
        switch (winner) {
            case 0: //Player wins
                playerWins += 1;
                break;
            case 1: //Computer wins
                compWins += 1;
                break;
            case 2: //Tie
                ties += 1;
                break;
        }
    }

    private void UpdateStats() {
        playerWinTf.setText(Integer.toString(playerWins));
        compWinTf.setText(Integer.toString(compWins));
        tieTf.setText(Integer.toString(ties));
    }
}
