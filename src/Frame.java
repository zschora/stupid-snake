import javax.swing.*;

public class Frame extends JFrame {
    private Field field;

    Frame(String title){
        super(title);
        setSize(550, 550);
        setLayout(null);
        setDefaultCloseOperation(2);

        field = new Field("gameField");
        this.add(field);
        this.addKeyListener(field);

        setVisible(true);
    }

    private void NewGame(){
        field = new Field("gameField");
        this.add(field);
        this.addKeyListener(field);

    }

    public static void main(String[] args){
        Frame frame = new Frame("gameFrame");
    }
}
