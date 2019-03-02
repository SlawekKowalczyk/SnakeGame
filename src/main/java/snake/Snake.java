package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends JFrame implements ActionListener {

    private Board board;

    private JButton startGame;
    private JButton pause;
    private JButton newGame;
    private JButton endGame;

    private JLabel score, pointsStrawberry, pointsApple, pointsMouse;
    private JLabel logo, apple, strawberry, mouse;

    public Snake() {
        setWindowProperties();

        initButtons();
    }

    private void initButtons() {
        logo = new JLabel();
        logo.setIcon(new ImageIcon(Constans.LOGO));
        logo.setBounds(5, 5, 100, 150);

        startGame = new JButton("Uruchom");
        startGame.setBackground(Color.GRAY);
        startGame.setForeground(Color.yellow);
        startGame.setBounds(5, 160, 100, 20);
        startGame.addActionListener(this);

        pause = new JButton("Pauza");
        pause.setBackground(Color.GRAY);
        pause.setForeground(Color.yellow);
        pause.setBounds(5, 185, 100, 20);
        pause.addActionListener(this);

        newGame = new JButton("Nowa gra");
        newGame.setBackground(Color.gray);
        newGame.setForeground(Color.yellow);
        newGame.setBounds(5, 210, 100, 20);
        newGame.addActionListener(this);

        score = new JLabel();
        score.setBackground(Color.gray);
        score.setForeground(Color.red);
        score.setBounds(5, 235, 100, 20);
        score.setText("Wynik: 0pkt");

        strawberry = new JLabel();
        strawberry.setIcon(new ImageIcon(Constans.STRAWBERRY));
        strawberry.setBounds(5, 265, 20, 20);

        pointsStrawberry = new JLabel();
        pointsStrawberry.setText(" +5pkt");
        pointsStrawberry.setBounds(30, 265, 50, 20);

        apple = new JLabel();
        apple.setIcon(new ImageIcon(Constans.APPLE));
        apple.setBounds(5, 290, 20, 20);

        pointsApple = new JLabel();
        pointsApple.setText(" +25pkt");
        pointsApple.setBounds(30, 290, 50, 20);

        mouse = new JLabel();
        mouse.setIcon(new ImageIcon(Constans.MOUSE));
        mouse.setBounds(5, 315, 20, 20);

        pointsMouse = new JLabel();
        pointsMouse.setText(" + 50pkt");
        pointsMouse.setBounds(30, 315, 60, 20);

        endGame = new JButton("Koniec");
        endGame.setBackground(Color.gray);
        endGame.setForeground(Color.yellow);
        endGame.setBounds(5, 365, 100, 30);
        endGame.addActionListener(this);

        board = new Board(this);
        board.setBounds(110, 0, 400, 400);

        add(board);
        add(logo);
        add(apple);
        add(strawberry);
        add(mouse);
        add(pointsApple);
        add(pointsStrawberry);
        add(pointsMouse);
        add(startGame);
        add(newGame);
        add(score);
        add(pause);
        add(endGame);
    }

    private void setWindowProperties() {
        setTitle("Snake");
        setSize(515, 428);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setScore(int score) {
        this.score.setText("Wynik: " + score + "pkt");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGame) {
            board.getTimer().start();
            startGame.setEnabled(false);
            board.requestFocus();
        } else if (e.getSource() == pause) {
            startGame.setEnabled(true);
            board.getTimer().stop();
            board.requestFocus();
        } else if (e.getSource() == newGame) {
            setScore(0);
            board.newGame();
            board.requestFocus();
            repaint();
        } else if (e.getSource() == endGame) {
            board.getTimer().stop();
            System.exit(0);
        }
    }
}
