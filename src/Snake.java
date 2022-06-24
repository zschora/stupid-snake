import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake {
    public class Cell{
        int x, y;
        Cell(int x, int y){this.x = x; this.y = y;}
    }

    private Deque<Cell> snake;

    private Cell dim;
    Boolean pokushala = false;

    Snake(){
        snake = new ArrayDeque<>();
        for (int i = 0; i<4; i++){
            Cell cell = new Cell(i, 4);
            snake.add(cell);
        }
        dim = new Cell(-1, 0);

    }

    public void Eat(){
        pokushala = true;
    }

    public void NexStep(){
        AddCell();
        if(!pokushala)
            snake.removeLast();
        pokushala = false;
    }

    public void Repaint(Graphics2D g){
        for (Cell c : snake) {
            g.drawRect(c.x * 50 + 5, c.y*50+5, 40,40);
        }
    }

    public void UpdateDirection(String dir){
        switch (dir){
            case "LEFT":
                dim = new Cell(-1, 0);
                break;
            case "UP":
                dim = new Cell(0, -1);
                break;
            case "RIGHT":
                dim = new Cell(1, 0);
                break;
            case "DOWN":
                dim = new Cell(0, 1);
                break;
        }
    }

    public void AddCell(){
        Cell head = snake.getFirst();
        int xH = head.x, yH = head.y;
        snake.addFirst(new Cell((xH+dim.x+10)%10, (yH+dim.y+10)%10));
    }

    public Cell Head(){
        return snake.getFirst();
    }

    public Boolean isStopped(){
        boolean ok = false;
        Cell head = snake.removeFirst();
        for(Cell c:snake)
            if (c.x == head.x && c.y == head.y) {
                ok = true;
                break;
            }
        snake.addFirst(head);
        return ok;
    }
}
