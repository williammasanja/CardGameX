package Classes;

public class StackCreator {
    //Card Creation
    public static Cards A = new Cards("A", "Pawn", 2);
    public static Cards B = new Cards("B", "Pawn", 2);
    public static Cards C = new Cards("C", "Royal", 8); 
    public static Cards D = new Cards("D", "Pawn", 2);
    public static Cards E = new Cards("E", "Pawn", 3);
    public static Cards F = new Cards("F", "Royal", 3);
    public static Cards NA = new Cards("NA", "NA", -1);
    public static Cards Blank = new Cards("Blank","NA",-1);
    private static Cards[] CardList = {A, B, C, D, E};
    private static Cards[] CardListB = {A,B,C,D,F,E };
    //StackCreation
    public static Stack Test = new Stack(CardList, "Test");
    public static Stack TestB = new Stack(CardListB, "TestB");

    
    

}
