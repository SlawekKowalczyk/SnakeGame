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
        setProperties();
        addListeners();
    }

    private void initButtons() {
        logo = new JLabel();
        startGame = new JButton(Constans.START);
        pause = new JButton(Constans.PAUZA);
        newGame = new JButton(Constans.NEW_GAME);
        score = new JLabel();
        strawberry = new JLabel();
        pointsStrawberry = new JLabel();
        apple = new JLabel();
        pointsApple = new JLabel();
        mouse = new JLabel();
        pointsMouse = new JLabel();
        endGame = new JButton(Constans.END);
        board = new Board(this);

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

    private void setProperties() {
        logo.setIcon(new ImageIcon(Constans.LOGO));
        logo.setBounds(5, 5, 100, 150);

        startGame.setBackground(Color.GRAY);
        startGame.setForeground(Color.yellow);
        startGame.setBounds(5, 160, 100, 20);

        pause.setBackground(Color.GRAY);
        pause.setForeground(Color.yellow);
        pause.setBounds(5, 185, 100, 20);

        newGame.setBackground(Color.gray);
        newGame.setForeground(Color.yellow);
        newGame.setBounds(5, 210, 100, 20);

        score.setBackground(Color.gray);
        score.setForeground(Color.red);
        score.setBounds(5, 235, 100, 20);
        score.setText(Constans.SCORE);

        strawberry.setIcon(new ImageIcon(Constans.STRAWBERRY));
        strawberry.setBounds(5, 265, 20, 20);

        pointsStrawberry.setText(Constans.PKTS_5);
        pointsStrawberry.setBounds(30, 265, 50, 20);

        apple.setIcon(new ImageIcon(Constans.APPLE));
        apple.setBounds(5, 290, 20, 20);

        pointsApple.setText(" +25pkt");
        pointsApple.setBounds(30, 290, 50, 20);

        mouse.setIcon(new ImageIcon(Constans.MOUSE));
        mouse.setBounds(5, 315, 20, 20);

        pointsMouse.setText(" + 50pkt");
        pointsMouse.setBounds(30, 315, 60, 20);

        endGame.setBackground(Color.gray);
        endGame.setForeground(Color.yellow);
        endGame.setBounds(5, 365, 100, 30);

        board.setBounds(110, 0, 400, 400);
    }

    private void addListeners() {
        startGame.addActionListener(this);
        pause.addActionListener(this);
        newGame.addActionListener(this);
        endGame.addActionListener(this);
    }

    private void setWindowProperties() {
        setTitle(Constans.TITLE);
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