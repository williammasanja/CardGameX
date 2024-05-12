package Board;

import javax.swing.JPanel;

import Classes.Cards;
import Classes.Player;
import Classes.Stack;
import Classes.StackCreator;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;



public class Screen extends JPanel implements Runnable {
    private int Turn = 1;
    private Thread Game;
    private Image Pawn = new ImageIcon("Images/PawnPrototype.png").getImage();
    private Image Royal = new ImageIcon("Images/RoyalBPrototype.png").getImage();
    
    private int DrawX = 990;
    private int DrawY = 520;


   
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
                //System.out.println("currentTime: " + currentTime);
                delta += (currentTime - lastTime) / drawInterval;
                //System.out.println(delta);
                if( delta > 1){
                
                //updateTurn();
                DrawPhase();
                repaint();
                delta --;
                }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        g.fillRect(400, 520, 400, 140); // Playerhand


        
        g.fillRect(990, 520, 100, 140); //  Deck 
        
        g.drawImage(Pawn, 400,520,null);
        g.drawImage(Pawn, 500,520,null);
        g.drawImage(Pawn, 600,520,null);
        g.drawImage(Royal, 700,520,null);
        
        Cards TopCard = StackCreator.Test.returnTopCard();
        g.drawImage(TopCard.returnImage(), DrawX, DrawY, null); //Shows top Card on 

        g.fillRect(300, 150, 600, 360); //Board

        g.fillRect(400, 0, 400, 140); //Bot Hand

        //g.fillRect(0,160,170,90); //Turn sign
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("TURN: " + Integer.toString(Turn), 0, 195);
        
        }
    public void updateTurn(){
            this.Turn += 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
    }
    //Animates DrawPhase
    public void DrawPhase(){

        if (DrawX > 400){
            DrawX -= 10;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    
}
