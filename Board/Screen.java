package Board;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;


public class Screen extends JPanel {

    public Screen(){
        super();
        this.setBackground(Color.GREEN);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        g.fillRect(400, 520, 400, 140); // Playerhand

        g.fillRect(990, 520, 100, 140); //  Deck

        g.fillRect(300, 150, 600, 360); //Board

        g.fillRect(400, 0, 400, 140); //Bot Hand

        g.fillRect(0,160,170,90); //Turn sign



    }
    
}
