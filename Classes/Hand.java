package Classes;

import java.util.Hashtable;
public class Hand extends Stack {
    public Hashtable<Integer,Integer> HandPos = new Hashtable<Integer,Integer>();
    public Hand(Boolean B) {
        super(B);
        //super.addCard(super.randomCard());
        if(B){ //regular hand
        HandPos.put(0, 400);
        HandPos.put(1,500);
        HandPos.put(2,600);
        HandPos.put(3, 700);
        }
        else{ //Board
            HandPos.put(0, 350);
            HandPos.put(1,550);
            HandPos.put(2,750);
        }
        //super.addCard(StackCreator.NA);
        
    }

    @Override
    public void removeCard(Cards e){
        super.stack.set(super.stack.indexOf(e),StackCreator.NA);
    }

    public void setCard(int index, Cards e){
        super.stack.set(index, e);
    }
    
    public int fillednum(){ // to how many cards filled
        int count = 0;
        for(Cards e: super.stack){
            if(e.getName() != "NA"){
                count++;
            }
        }
        return count;
    }

    public void removeindex(int i){
        super.stack.remove(i);
        super.stack.set(i, StackCreator.NA);
    }

    
    
}
