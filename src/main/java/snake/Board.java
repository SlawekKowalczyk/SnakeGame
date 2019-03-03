package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener, KeyListener {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;

    private final List<Integer> x_SNAKE = new ArrayList<>();
    private final List<Integer> y_SNAKE = new ArrayList<>();

    private final int SQUARE_SIZE = 20;
    private final int RAND_POS = 19;

    private int snakeLength;
    private int score;
    private int x_Strawberry, y_Strawberry;
    private int x_Apple, y_Apple;
    private int x_Mouse, y_Mouse;

    private boolean gameStatus = true;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private final int SPEED = 300;
    private Timer timer;

    private Image iSnakeHead, iSnake, iStrawberry, iApple, iMouse;

    private Snake snake;

    public Board(Snake snake) {
        this.snake = snake;
        addKeyListener(this);
        setProperties();
        loadImage();
        initGame();
        timer = new Timer(SPEED, this);
    }

    private void setProperties() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initGame() {
        snakeLength = 5;
        for (int i = 0; i < snakeLength; i++) {
            x_SNAKE.add(100 - i * SQUARE_SIZE);
            y_SNAKE.add(100);
        }
        strawberryLocation();
        appleLocation();
        mouseLocation();
    }

    public void loadImage() {
        ImageIcon iiHead = new ImageIcon(Constans.HEAD);
        iSnakeHead = iiHead.getImage();

        ImageIcon iiSnake = new ImageIcon(Constans.SNAKE);
        iSnake = iiSnake.getImage();

        ImageIcon iiStrawberry = new ImageIcon(Constans.STRAWBERRY);
        iStrawberry = iiStrawberry.getImage();

        ImageIcon iiApple = new ImageIcon(Constans.APPLE);
        iApple = iiApple.getImage();

        ImageIcon iiMouse = new ImageIcon(Constans.MOUSE);
        iMouse = iiMouse.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (gameStatus) {
            g.drawImage(iStrawberry, x_Strawberry, y_Strawberry, this);

            if ((score > 0) && (score % 45 == 0)) {
                g.drawImage(iApple, x_Apple, y_Apple, this);
            }
            if ((score > 0) && (score % 150 == 0)) {
                g.drawImage(iMouse, x_Mouse, y_Mouse, this);
            }

            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.drawImage(iSnakeHead, x_SNAKE.get(i), y_SNAKE.get(i), this);
                } else {
                    g.drawImage(iSnake, x_SNAKE.get(i), y_SNAKE.get(i), this);
                }
            }
            if (score >= 390) {
                youWin(g);
                timer.stop();
            }
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String gameEnd = Constans.GAME_OVER;
        Font font = new Font("Helvetica", Font.BOLD, 20);

        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(gameEnd, ((HEIGHT / 2) - 100), WIDTH / 2);

    }

    private void youWin(Graphics g) {
        String gameEnd = Constans.YOU_WIN;
        Font font = new Font("Helvetica", Font.BOLD, 20);

        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(gameEnd, ((HEIGHT / 2) - 100), WIDTH / 2);
    }

    public void strawberryLocation() {
        boolean flag = false;
        while (!flag) {
            int x = (int) (Math.random() * RAND_POS);
            x_Strawberry = x * SQUARE_SIZE;
            int y = (int) (Math.random() * RAND_POS);
            y_Strawberry = y * SQUARE_SIZE;

            if ((x_SNAKE.contains(x_Strawberry)) && (y_SNAKE.contains(y_Strawberry))) {
                continue;
            } else {
                flag = true;
            }
        }
    }

    public void appleLocation() {
        boolean flag = false;
        while (!flag) {
            int x = (int) (Math.random() * RAND_POS);
            x_Apple = x * SQUARE_SIZE;
            int y = (int) (Math.random() * RAND_POS);
            y_Apple = y * SQUARE_SIZE;

            if ((x_SNAKE.contains(x_Apple)) && (y_SNAKE.contains(y_Apple))) {
                continue;
            } else {
                flag = true;
            }
        }
    }

    public void mouseLocation() {
        boolean flag = false;
        while (!flag) {
            int x = (int) (Math.random() * RAND_POS);
            x_Mouse = x * SQUARE_SIZE;
            int y = (int) (Math.random() * RAND_POS);
            y_Mouse = y * SQUARE_SIZE;

            if ((x_SNAKE.contains(x_Mouse)) && (y_SNAKE.contains(y_Mouse))) {
                continue;
            } else {
                flag = true;
            }
        }
    }

    public void move() {
        for (int i = snakeLength - 1; i > 0; i--) {
            x_SNAKE.set(i, x_SNAKE.get(i - 1));
            y_SNAKE.set(i, y_SNAKE.get(i - 1));
        }

        if (leftDirection) {
            x_SNAKE.set(0, x_SNAKE.get(0) - SQUARE_SIZE);
        }

        if (rightDirection) {
            x_SNAKE.set(0, x_SNAKE.get(0) + SQUARE_SIZE);
        }

        if (upDirection) {
            y_SNAKE.set(0, y_SNAKE.get(0) - SQUARE_SIZE);
        }

        if (downDirection) {
            y_SNAKE.set(0, y_SNAKE.get(0) + SQUARE_SIZE);
        }
    }

    public void checkStrawberry() {
        if ((x_SNAKE.get(0) == x_Strawberry) && (y_SNAKE.get(0) == y_Strawberry)) {
            snakeLength++;
            x_SNAKE.add(x_SNAKE.get(1));
            y_SNAKE.add(y_SNAKE.get(1));
            strawberryLocation();
            int strawberryPoint = 5;
            score += strawberryPoint;
            snake.setScore(score);
        }
    }

    public void checkApple() {
        if ((x_SNAKE.get(0) == x_Apple) && (y_SNAKE.get(0) == y_Apple)) {
            if (score % 45 == 0) {
                snakeLength++;
                x_SNAKE.add(x_SNAKE.get(1));
                y_SNAKE.add(y_SNAKE.get(1));
                appleLocation();
                int applePoint = 25;
                score += applePoint;
                snake.setScore(score);
            }
        }
    }

    public void checkMouse() {
        if ((x_SNAKE.get(0) == x_Mouse) && (y_SNAKE.get(0) == y_Mouse)) {
            if (score % 150 == 0) {
                snakeLength++;
                x_SNAKE.add(x_SNAKE.get(1));
                y_SNAKE.add(y_SNAKE.get(1));
                mouseLocation();
                int mousePoint = 50;
                score += mousePoint;
                snake.setScore(score);
            }
        }
    }

    public void checkCollision() {
        for (int i = 1; i < snakeLength; i++) {
            if ((x_SNAKE.get(i).equals(x_SNAKE.get(0))) && (y_SNAKE.get(i).equals(y_SNAKE.get(0)))) {
                gameStatus = false;
            }
        }
        if (x_SNAKE.get(0) >= WIDTH) {
            gameStatus = false;
        }
        if (x_SNAKE.get(0) < 0) {
            gameStatus = false;
        }
        if (y_SNAKE.get(0) >= HEIGHT) {
            gameStatus = false;
        }
        if (y_SNAKE.get(0) < 0) {
            gameStatus = false;
        }
        if (!gameStatus) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus) {
            checkStrawberry();
            checkApple();
            checkMouse();
            checkCollision();
            move();
        }
        repaint();
    }

    public void newGame() {
        score = 0;
        x_SNAKE.clear();
        y_SNAKE.clear();

        timer.start();
        gameStatus = true;
        snakeLength = 5;

        for (int i = 0; i < snakeLength; i++) {
            x_SNAKE.add(100 - i * SQUARE_SIZE);
            y_SNAKE.add(100);
        }

        leftDirection = false;
        rightDirection = true;
        upDirection = false;
        downDirection = false;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int source = e.getKeyCode();
        if (source == KeyEvent.VK_RIGHT && !leftDirection) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        }
        if (source == KeyEvent.VK_LEFT && !rightDirection) {
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        }
        if (source == KeyEvent.VK_UP && !downDirection) {
            upDirection = true;
            leftDirection = false;
            rightDirection = false;
        }
        if (source == KeyEvent.VK_DOWN && !upDirection) {
            downDirection = true;
            leftDirection = false;
            rightDirection = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
