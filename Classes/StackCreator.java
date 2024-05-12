package Classes;

public class StackCreator {
    //Card Creation
    public static Cards A = new Cards("A", "Pawn", 2);
    public static Cards B = new Cards("B", "Pawn", 2);
    public static Cards C = new Cards("C", "Royal", 8); 
    private static Cards[] CardList = {A, B, C};
    //StackCreation
    public static Stack Test = new Stack(CardList, "Test");
    
    

}
