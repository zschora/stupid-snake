import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Deque;

public class Field extends JPanel implements Runnable, KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        switch (c) {
            case 37 : snake.UpdateDirection("LEFT"); return;
            case 38 : snake.UpdateDirection("UP"); return;
            case 39 : snake.UpdateDirection("RIGHT"); return;
            case 40 : snake.UpdateDirection("DOWN");return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    Snake snake;
    Obstacles obs;
    Boolean fall = false;
    Field(String name){
        this.setName(name);
        setBounds(0, 0, 500, 500);
        snake = new Snake();
        obs = new Obstacles();
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if(snake.isStopped()){
                fall=true;
                break;}
            snake.NexStep();
            Snake.Cell head = snake.Head();
            if (obs.step(head)){
                snake.Eat();
            }

            try {
                Thread.sleep(300);
            } catch (Exception e){
                break;
            }
            repaint();
            if (obs.rnd.nextInt(13) == 0 || obs.cnt < 2) obs.Add();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(fall){
            g2.drawString("FAIL",200, 200);
            return;
        }
        for (int i = 0; i< 10; i++){
            g.drawLine(0, i*50, 500, i*50);
            g.drawLine(i*50, 0, i*50, 500);
        }
        snake.Repaint(g2);
        obs.Repaint(g2);
    }
}

