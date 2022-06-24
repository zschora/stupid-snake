import java.awt.*;
import java.util.Random;

public class Obstacles {
    private boolean[][] ob = new boolean[10][10];
    Random rnd = new Random();

    int cnt;

    Obstacles(){
        cnt = 0;
        for (int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                ob[i][j] = rnd.nextInt(100) < 4;
                if(ob[i][j]) cnt++;
            }
        }
    }

    public Boolean step(Snake.Cell cell){
        Boolean ret = ob[cell.x][cell.y];
        ob[cell.x][cell.y] = false;
        if(ret) cnt -= 1;
        return ret;

    }

    public void Repaint(Graphics2D g){
        for (int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ob[i][j])
                    g.drawOval(i*50 + 15, j*50+15, 20, 20);
            }
        }
    }

    public void Add(){
        ob[rnd.nextInt(10)][rnd.nextInt(10)] = true;
        cnt++;
    }
}
