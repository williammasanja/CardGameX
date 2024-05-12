package Classes;

import java.awt.Image;
import javax.swing.ImageIcon;



import java.util.Hashtable;

public class Cards {
    private final String Name;
    private  final String Type;
    private final int LvL;
    private Image img;
    private Hashtable<String, Image> Types =  new Hashtable<>();
    


    public Cards(){
        this("Test", "Pawn", 2);
    }
    public Cards(String Name, String Type, int LvL){
        this.Name = Name;
        this.Type = Type;
        this.LvL = LvL;
        Types.put("Pawn", new ImageIcon("Images/PawnPrototype.png").getImage());
        Types.put("Royal", new ImageIcon("Images/RoyalBPrototype.png").getImage());
        setImage();
    }

    public void setImage(){
        img = this.Types.get(this.Type);

    }

    public String getName(){
        return this.Name;
    }
    
    public String getType(){
        return this.Type;
    }

    public int getLvL(){
        return this.LvL;
    }

    public String toString(){
        return this.Name + " " + this.Type + " " + this.LvL;
    }

}
