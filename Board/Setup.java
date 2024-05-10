package Board;
import java.awt.Color;

import javax.swing.*;

public class Setup {
    private JFrame frame;

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

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        frame.add(panel);

    }

}
