import java.util.*;
import java.io.*;
public class Word
{
    private String name;
    private ArrayList<String> letters;
    public Word(String n)
    {
        name = n;
        letters = new ArrayList<>();
        for(int i = 0; i < name.length(); i++)
        {
            letters.add(name.substring(i, i+1));
        }
    }
  
    public String getName()
    {
        return name;
    }
    
    public ArrayList<String> getLetters()
    {
        return letters;
    }
    
    public int letterCount()
    {
        int count = name.length();
        for(int i = 0; i < letters.size(); i++)
        {
            if(letters.get(i).equals(" ") || letters.get(i).equals("-") || letters.get(i).equals("'"))
            {
                count--;
            }
        }
        return count;
    }
    
    public int containsLetter(Word w, String letter)
    {
        int numOfLetters = 0;
        for(int i = 0; i < w.name.length(); i++)
        {
            if(w.getLetters().get(i).equalsIgnoreCase(letter))
            {
                numOfLetters++;
            }
        }
        return numOfLetters;
    }
}