package Classes;

import javax.swing.ImageIcon;

public class Cards {
    private final String Name;
    private  final String Type;
    private final int LvL;



    public Cards(){
        Name = "Test";
        Type = "Pawn";
        LvL = 2;
    }
    public Cards(String Name, String Type, int LvL){
        this.Name = Name;
        this.Type = Type;
        this.LvL = LvL;
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
