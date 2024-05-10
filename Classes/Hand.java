package Classes;

public class Hand extends Stack {

    public Hand(Cards[] List) {
        super(List);
    }

    public void ShowList(){
        super.ShowList();
    }
    public int indexof(Cards e){
        return super.indexof(e);
    }

    public void addCard(Cards e){
        super.stack.add(e);
    }

    public void removeCArd(Cards e){
        super.stack.remove(e);
    }
    
    public int getsize(){
        return super.getsize();
    }
}
