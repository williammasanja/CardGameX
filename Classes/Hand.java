package Classes;

import java.util.Hashtable;
public class Hand extends Stack {
    public Hashtable<Integer,Integer> HandPos = new Hashtable<Integer,Integer>();
    public Hand() {
        super();
        //super.addCard(super.randomCard());
        HandPos.put(0, 400);
        HandPos.put(1,500);
        HandPos.put(2,600);
        HandPos.put(3, 700);
        //super.addCard(StackCreator.NA);
        
    }
    @Override
    public void removeCard(Cards e){
        super.stack.set(super.stack.indexOf(e),StackCreator.NA);
    }
    
}
