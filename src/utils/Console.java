package utils;

public final class Console {
    private static final String newlines = System.lineSeparator().repeat(50);
    public static void clear(){
        System.out.println(newlines);
    }
}
