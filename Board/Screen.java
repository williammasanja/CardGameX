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
    

    // Player Varibles
    private Stack PlayerStack = StackCreator.Test;
    private Hand Playerhand = new Hand(true);
    private Hand Playerboard = new Hand(false); // Board
    private int PlayerHealth = 13;
    private Cards PlayerCardHolder = new Cards();
    private int DrawX = 990;
    private int DrawY = 520;

    // Bot Varibles 
    private Stack BotStack = StackCreator.TestB;
    private Hand BotHand = new Hand(true);
    private Hand Botboard = new Hand(false);
    private int BotHealth = 13;
    private Cards BotCardHolder = new Cards();
    private int BotX = 120;
    private int BotY = 0;


    private int Clicked = 0;
    private int[] CardAB = {-1, -1};
    Boolean P_B = true;
     
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

        g.fillRect(400,0, 400, 140); //Bot Hand
        g.fillRect(120, 0, 100, 140); // Bot Deck
        g.fillRect(900, 0, 100, 140); // Bot Graveyard
        //System.out.println(StackCreator.Test.getsize());
        showBoard(g);
        showHand(g);

        
        if (Turn == 0){
            SetupHand(g);
        }
        if (Turn % 2 == 1){
        if (Phase == 1){
            DrawPhase(g);
        }
        if (Phase == 2){
            SetCard();
        }
        }
        else{
            if (Phase == 1 && BotHand.getsize() < 4){
                DrawPhase(g);
            }
            if (Phase == 2){
                SetCard();
                Phase++;
            }
        }
        
        HandAnimation(g);
        
        

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("TURN: " + Integer.toString(Turn), 0, 195); //Sign
        g.drawString("Phase: " + Integer.toString(Phase), 0, 230);
        g.drawString("Clicked: " + Integer.toString(Clicked), 0, 280);
        g.drawString("PlaceHolder: " + PlayerCardHolder.getName(), 0, 320);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("PLAYER HEALTH: " + Integer.toString(PlayerHealth), 0, 580);
        g.drawString("Bot Health: " + Integer.toString(BotHealth), 950, 200);
        g.dispose();


        }
        public void updateTurn(){
            this.Turn += 1;
        
        }
    //Animates DrawPhase
    public void DrawPhase(Graphics g){
        
        if (Turn % 2 == 1){
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
    else if (Turn % 2 == 0){
        g.drawImage(Blank, BotX, BotY,null);
        int avaliblespace = BotHand.indexof(StackCreator.NA);
        if(BotX < BotHand.HandPos.get(BotHand.getsize() - avaliblespace-1)+30){
            BotX += 10;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            BotHand.setCard(BotHand.getsize() - avaliblespace-1,BotStack.returnTopCard());
            BotStack.removeCard(BotStack.returnTopCard());
            BotX = 120;
            BotY = 140;
            Phase = 2;
        }
    }
    }
    
    public void StacktoHand(Cards e){
            Playerhand.addCard(e);
            
    }
    public void showHand(Graphics g){
        for(int i = 0; i < Playerhand.getsize(); i++){
            g.drawImage(BotHand.returnCard(i).returnImage(),BotHand.HandPos.get(i),0, null);
            if(CardAB[0] == -1){
            g.drawImage(Playerhand.returnCard(i).returnImage(),Playerhand.HandPos.get(i),520, null);
            }
            else{
                //System.out.println(CardAB[0]);
                g.drawImage(Playerhand.returnCard(CardAB[0]).returnImage(), 400 + CardAB[0] * 100, 520-50, null);
                g.fillRect(400, 520+90, 100,50);
            }  
        }
        //for(int i = 0; i < BotHand.getsize(); i++){
            
        //}
    }
    public void showBoard(Graphics g){
        for(int i = 0; i < Playerboard.getsize(); i++){
            g.drawImage(Playerboard.returnCard(i).returnImage(),Playerboard.HandPos.get(i),350, null);
            g.drawImage(Botboard.returnCard(i).returnImage(), Playerboard.HandPos.get(i), 170, null);
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
    public int ClickedHand(){
        if(Setup.ClickedX >= 400 && Setup.ClickedX  <= 400+100){
            if(Setup.ClickedY >= 520 && Setup.ClickedY <= 520+140){
               // System.out.println("YOP");
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    Clicked = 1;
                    return 0;
                   }
            }
        }
        if(Setup.ClickedX >= 500 && Setup.ClickedX  <= 500+100){
            if(Setup.ClickedY >= 520 && Setup.ClickedY <= 520+140){
               // System.out.println("YOP");
                if(Playerhand.indexof(StackCreator.NA) != 0){
                    Clicked = 1;
                    return 1;
                   }
            }
        }
        else if(Setup.ClickedX >= 600 && Setup.ClickedX  <= 600+100){
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
       
        return - 1;

    }

    public int ClickedBoard(){
        if(Setup.ClickedX >= 350 && Setup.ClickedX  <= 350+100){
            if(Setup.ClickedY >= 350 && Setup.ClickedY <= 350+140){
                if(Playerhand.indexof(StackCreator.NA) != 0 && Clicked == 2){
                    Clicked = 3;
                    return 0;
                   }
            }
        }
        else if(Setup.ClickedX >= 550 && Setup.ClickedX  <= 550+100){
            if(Setup.ClickedY >= 350 && Setup.ClickedY <= 350+140){
                if(Playerhand.indexof(StackCreator.NA) != 0 && Clicked == 2){
                    Clicked = 3;
                    return 1;
                   }
            }
        }
        if(Setup.ClickedX >= 750 && Setup.ClickedX  <= 750+100){
            if(Setup.ClickedY >= 350 && Setup.ClickedY <= 350+140){
                if(Playerhand.indexof(StackCreator.NA) != 0 && Clicked == 2){
                    Clicked = 3;
                    return 2;
                   }
            }
        }
        return -1;
    }

    
    
    public void HandtoPlaceHolder(int num){
        if(Clicked == 1){
            CardAB[0] = num;
            PlayerCardHolder = Playerhand.returnCard(num);
            Clicked++;
           // PlaceHoldertoBoard(ClickedBoard(), num);
        }
    }
    public void PlaceHoldertoBoard(int num){
        if(Clicked == 3){
            System.out.println(num);
            CardAB[1] = num;
            Clicked++;
            
        }
    }   
    public void BoardtoHand(){
        System.out.println(CardAB[0] + " " + CardAB[1]);
        Playerboard.setCard(CardAB[1], Playerhand.returnCard(CardAB[0]));
        Playerhand.removeCard(Playerhand.returnCard(CardAB[0]));
        CardAB[0] = -1;
        CardAB[1] = -1;
        PlayerCardHolder = new Cards();
    }


    public void SetCard(){
        if (Turn % 2 == 1){
        int num = ClickedHand();
        if(Clicked == 1){
            HandtoPlaceHolder(num);
        }
        int numB = ClickedBoard();
        if(Clicked == 3){
            PlaceHoldertoBoard(numB);
        }
        if (Clicked == 4){
            //System.out.println(CardAB[0] + " " + CardAB[1]);
            BoardtoHand();
            Playerhand.ShowList();
            Clicked++;
        }
    }
        else{
            int i = 0;
            while (Botboard.returnCard(i) != StackCreator.NA){
             i++;
            }
            Botboard.setCard(i, BotHand.returnCard(0));
            BotHand.removeCard(BotHand.returnCard(0));
        }
        
        
        
    }

    public void SetupHand(Graphics g){
        if(P_B){
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
                System.out.println(avaliblespace);
            Playerhand.setCard(avaliblespace,PlayerStack.returnTopCard());
            PlayerStack.removeCard(PlayerStack.returnTopCard());
            DrawX = 990;
            DrawY = 520;
            }
            if(avaliblespace == 3){
                P_B = false;
            }
       }
       
        }
        
    }
    if (P_B == false){

        g.drawImage(Blank, BotX, BotY,null);
        System.out.println(BotHand.getsize() - BotHand.fillednum()-1);
         int avaliblespace = BotHand.getsize() - BotHand.fillednum()-1;
        if( avaliblespace != -1){
            if(BotX < BotHand.HandPos.get(avaliblespace)+30){
                BotX += 10;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else{
               // System.out.println(avaliblespace);
                if(avaliblespace > -1){
                    //System.out.println(avaliblespace);
                    BotHand.setCard(avaliblespace,BotStack.returnTopCard());
                    BotStack.removeCard(BotStack.returnTopCard());
                    BotX = 120;
                    BotY = 0; 
                }
            }
        }
        else{
            updateTurn();
            Phase = 2;

        }
       
    }
    }
    
}

