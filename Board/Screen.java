package Board;

import javax.swing.JPanel;

import Classes.Cards;
import Classes.Hand;
import Classes.Player;
import Classes.Stack;
import Classes.StackCreator;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseListener;




public class Screen extends JPanel implements Runnable{
    private int Turn = 1;
    private int Phase = 0; //Phases in a Turn

    private Thread Game;
    private Image Pawn = new ImageIcon("Images/PawnPrototype.png").getImage();
    private Image Royal = new ImageIcon("Images/RoyalBPrototype.png").getImage();
    private Image Blank = new ImageIcon("Images/Blank.png").getImage();
    private int DrawX = 990;
    private int DrawY = 520;

    private Hand Playerhand = new Hand();

   
    public Screen(){
        super();
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        GameThread();
    }
    public void GameThread(){
        Game = new Thread(this);
        Game.start();
    }

    @Override
    public void run(){
        double drawInterval = 1000000000/30; 
        double delta = 0.0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (Game != null){
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                if( delta > 1){
                //updateTurn();
                repaint();
                delta --;
                }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(400, 520, 400, 140); // Playerhand

        g.fillRect(1090, 460, 100, 50); // nextPhase Button
        PhaseButton(g);
        
        g.fillRect(990, 520, 100, 140); // Deck 
    
        showHand(g); 

        if (Phase == 1){
            DrawPhase(g);
        }
        
        g.fillRect(300, 150, 600, 360); //Board

        g.fillRect(400, 0, 400, 140); //Bot Hand

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("TURN: " + Integer.toString(Turn), 0, 195); //Sign
        g.drawString("Phase: " + Integer.toString(Phase), 0, 230);
        
        g.dispose();


        }
        public void updateTurn(){
            this.Turn += 1;
        
        }
    //Animates DrawPhase
    public void DrawPhase(Graphics g){
        g.drawImage(Blank, DrawX, DrawY,null);

        int avaliblespace = Playerhand.indexof(StackCreator.NA);
        if (DrawX > Playerhand.HandPos.get(avaliblespace)-30){
            DrawX -= 10;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
        }
        else{
            Phase = 0;
            DrawX = 990;
            DrawY = 520;
            System.err.println(Phase);
       }
    }
    public void StacktoHand(Cards e){
            Playerhand.addCard(e);
            
    }
    public void showHand(Graphics g){

        for(int i = 0; i < Playerhand.getsize(); i++){
            g.drawImage(Playerhand.returnCard(i).returnImage(),Playerhand.HandPos.get(i),520, getFocusCycleRootAncestor());
        }
    }
    public void PhaseButton(Graphics g){
        
        g.fillRect(1090, 460, 100, 50);
        if(Setup.ClickedX >= 1090 && Setup.ClickedX  <= 1090+100){
            if(Setup.ClickedY >= 460 && Setup.ClickedY <= 460 + 50){
                Phase = 1;
                Setup.ClickedX = 0;
                Setup.ClickedY = 0;    
                
                
            }
           
        }
    }
}
