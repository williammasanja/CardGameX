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
import java.io.Serial;




public class Screen extends JPanel implements Runnable{
    private int Turn = 0; // Starts at 0 to initate draw phase
    private int Phase = 0; //Phases in a Turn

    private Thread Game;
    private Image Pawn = new ImageIcon("Images/Cards/PawnPrototype.png").getImage();
    private Image Royal = new ImageIcon("Images/Cards/RoyalBPrototype.png").getImage();
    private Image Blank = new ImageIcon("Images/Cards/Blank.png").getImage();
    private Image Select = new ImageIcon("Images/Effects/SelectTriangle.png").getImage();
    private int DrawX = 990;
    private int DrawY = 520;

    private Stack PlayerStack = StackCreator.Test;
    private Hand Playerhand = new Hand(true);
    private Hand Playerboard = new Hand(false); // Board
    private int PlayerHealth = 13;

    private int Clicked = 0;
     
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
        g.fillRect(300, 150, 600, 360); //Board
        
        //System.out.println(StackCreator.Test.getsize());
        showBoard(g);
        showHand(g);
        if (Turn == 0){
            SetupHand(g);
        }
        if (Phase == 1){
            DrawPhase(g);
        }
        if (Phase == 2){
            SetCard();
        }
        if (Phase == 3){
            
        }
        
        HandAnimation(g);
        
       

        g.fillRect(400, 0, 400, 140); //Bot Hand

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("TURN: " + Integer.toString(Turn), 0, 195); //Sign
        g.drawString("Phase: " + Integer.toString(Phase), 0, 230);
        g.drawString("Clicked: " + Integer.toString(Clicked), 0, 280);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("PLAYER HEALTH: " + Integer.toString(PlayerHealth), 0, 580);
        
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
            Playerhand.setCard(avaliblespace,PlayerStack.returnTopCard());
           /*  if(avaliblespace < 3){
            Playerboard.setCard(avaliblespace, PlayerStack.returnTopCard());
            }*/
            PlayerStack.removeCard(PlayerStack.returnTopCard());
            DrawX = 990;
            DrawY = 520;
            System.err.println(Phase);
            Phase = 2;
       }
    }
    public void StacktoHand(Cards e){
            Playerhand.addCard(e);
            
    }
    public void showHand(Graphics g){
        for(int i = 0; i < Playerhand.getsize(); i++){
            
            if(Clicked == 0){
            g.drawImage(Playerhand.returnCard(i).returnImage(),Playerhand.HandPos.get(i),520, null);
            }
            else{
                g.drawImage(Playerhand.returnCard(0).returnImage(), 400, 520-50, null);
                g.fillRect(400, 520+90, 100,50);
            }  
        }
    }
    public void showBoard(Graphics g){
        for(int i = 0; i < Playerboard.getsize(); i++){
            g.drawImage(Playerboard.returnCard(i).returnImage(),Playerboard.HandPos.get(i),350, null);
        }
    }
    public void PhaseButton(Graphics g){
        g.fillRect(1090, 460, 100, 50);
        if(Setup.ClickedX >= 1090 && Setup.ClickedX  <= 1090+100){
            if(Setup.ClickedY >= 460 && Setup.ClickedY <= 460 + 50){
                Phase += 1;
                Setup.ClickedX = 0;
                Setup.ClickedY = 0; 
                if(Phase > 3){ // Resets phases in a turn 
                    Phase = 0;
                    Turn++; 
                }   
            }
        }
    }
    public void HandAnimation(Graphics g){
        if(Setup.MouseX >= 400 && Setup.MouseX  <= 400+100){
            if(Setup.MouseY >= 520 && Setup.MouseY <= 520+140){
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    g.drawImage(Playerhand.returnCard(0).returnImage(), 400, 520-50, null);
                    g.fillRect(400, 520+90, 100,50);
                    
                   }
            }
        }
        else if(Setup.MouseX >= 500 && Setup.MouseX  <= 500+100){
            if(Setup.MouseY >= 520 && Setup.MouseY <= 520+140){
                if(Playerhand.indexof(StackCreator.NA) != 1){
                g.drawImage(Playerhand.returnCard(1).returnImage(), 500, 520-50, null);
                g.fillRect(500, 520+90, 100,50);
            }
        }
        }
        else if(Setup.MouseX >= 600 && Setup.MouseX  <= 600+100){
            if(Setup.MouseY >= 520 && Setup.MouseY <= 520+140){
                if(Playerhand.indexof(StackCreator.NA) != 2){
                
                g.drawImage(Playerhand.returnCard(2).returnImage(), 600, 520-50, null);
                g.fillRect(600, 520+90, 100,50);
            }
        }
        }
       else if(Setup.MouseX >= 700 && Setup.MouseX  <= 700+100){
            if(Setup.MouseY >= 520 && Setup.MouseY <= 520+140){
                if(Playerhand.indexof(StackCreator.NA) != 3){
                g.drawImage(Playerhand.returnCard(3).returnImage(), 700, 520-50, null);
                g.fillRect(700, 520+90, 100,50);
                }
            }
        }
        
    }
    public int Clicked(){
        if(Setup.ClickedX >= 400 && Setup.ClickedX  <= 400+100){
            if(Setup.ClickedY >= 520 && Setup.ClickedY <= 520+140){
               // System.out.println("YOP");
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    Clicked = 1;
                    return 0;
                   }
            }
        }
        else if(Setup.ClickedX >= 500 && Setup.ClickedX  <= 500+100){
            if(Setup.ClickedY >= 520 && Setup.ClickedY <= 520+140){
               // System.out.println("YOP");
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    Clicked = 1;
                    return 1;
                   }
            }
        }
        if(Setup.ClickedX >= 600 && Setup.ClickedX  <= 600+100){
            if(Setup.ClickedY >= 520 && Setup.ClickedY <= 520+140){
               // System.out.println("YOP");
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    Clicked = 1;
                    return 2;
                   }
            }
        }
        else if(Setup.ClickedX >= 700 && Setup.ClickedX  <= 700+100){
            if(Setup.ClickedY >= 520 && Setup.ClickedY <= 520+140){
               // System.out.println("YOP");
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    Clicked = 1;
                    return 3;
                   }
            }
        }
        return -1;

    }
    public Boolean HandtoBoard(){
        if(Playerboard.fillednum() < 3)
        {
            if(Playerboard.returnCard(Playerboard.avaliblespace()) == StackCreator.NA){
                Playerboard.setCard(Playerboard.avaliblespace(), Playerhand.returnCard(Clicked()));
                Playerhand.setCard(Clicked(), StackCreator.NA);
            }
            return true;
        }
        return false;

    }
    public void SetCard(){
        Clicked();
        Boolean B = false;
        if(Clicked == 1){
            //System.out.println("BOP");
            B = HandtoBoard();
            Phase = 3;
        }
        Clicked = 0;
       
        
    }

    public void SetupHand(Graphics g){
        g.drawImage(Blank, DrawX, DrawY,null);
    
        int avaliblespace = Playerhand.indexof(StackCreator.NA);
        //System.out.println(avaliblespace);
        if(avaliblespace != -1){
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
            if(avaliblespace < 4){
            Playerhand.setCard(avaliblespace,PlayerStack.returnTopCard());
            PlayerStack.removeCard(PlayerStack.returnTopCard());
            DrawX = 990;
            DrawY = 520;
            }
       }
        }
        else{
            updateTurn();
            Phase = 2;
        }
    }
    
}

