package snake;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Snake s = new Snake();
                s.setVisible(true);
            }
        });
    }
    
}
