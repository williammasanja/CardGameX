package Classes;

public class StackCreator {
    //Card Creation
    public static Cards A = new Cards("A", "Pawn", 2);
    public static Cards B = new Cards("B", "Pawn", 2);
    public static Cards C = new Cards("C", "Royal", 8); 
    public static Cards NA = new Cards("NA", "NA", -1);
    public static Cards Blank = new Cards("Blank","NA",-1);
    private static Cards[] CardList = {A, B, C};
    //StackCreation
    public static Stack Test = new Stack(CardList, "Test");
    
    

}
