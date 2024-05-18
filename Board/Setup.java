package Board;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class Setup implements MouseListener, MouseMotionListener{
    private JFrame frame;
    public static int ClickedX = 0;
    public static int ClickedY = 0;

    public static int MouseX = 0;
    public static int MouseY = 0;
    private boolean isIn = false;


    public Setup(){
        init();
    }

    private void init(){
        frame = new JFrame();

        this.frame.setTitle("Nonet");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1200, 700);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        /*JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        frame.add(panel);
        */
        
        Screen Test = new Screen();
        Test.addMouseListener(this);
        Test.addMouseMotionListener(this);
        this.frame.add(Test);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("X: "+ e.getX() + " Y: " + e.getY());
        ClickedX = e.getX();
        ClickedY = e.getY();

       
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseX = e.getX();
        MouseY = e.getY();
        //System.out.println("X: "+ MouseX + " Y: " + MouseY);
    }


}
