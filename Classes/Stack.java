package Classes;

import java.util.ArrayList;
import java.util.Random;


public class Stack {
    ArrayList<Cards> stack = new ArrayList<Cards>();

    public Stack(Cards[] List){
        for(Cards x: List){
            stack.add(x);            
        }
    }

    public void ShowList(){
        int count  = 1;
        for (Cards x: stack){
            System.out.println(count +""+x);
        }
    }

    public int indexof(Cards e){
        for(int i = 0; i < stack.size(); i++){
            if (e.getName().equals(stack.get(i).getName())){
                return i;
            }
        }
        return -1;

    }
    
    public void addCard(Cards e){
        stack.add(e);
    }

    public void removeCard(Cards e){
        stack.remove(e);
    }

    public int getsize(){
        return stack.size();
    }

    public Cards randomCard(){
        Random rand = new Random();
        int randnum = rand.nextInt(stack.size());
        Cards randcard = stack.get(randnum);
        return randcard;
    }
}
