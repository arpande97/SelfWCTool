import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class WCTool {
    public static void main(String[] args) throws IOException
    {
        if(args.length == 0)
        {
            System.out.println("Usage: self_wc -<options> <filename>");
            System.exit(1);
        }
        String filename = args[args.length - 1];
        Set<Character> options = new HashSet<>();
        for(int i = 0; i < args.length - 1; i++)
        {
            String arg = args[i];
            int length = arg.length();
            if(arg.charAt(0) != '-')
            {
                System.out.println("Usage: self_wc -<options> <filename>");
                System.exit(1);
            }
            for(int j = 1; j < length; j++)
            {
                char option = arg.charAt(j);
                if(option != 'l' && option != 'c' && option != 'm' && option != 'w')
                {
                    System.out.println("Unrecognized option");
                    System.exit(1);
                }
                options.add(option);
            }

        }
        runWC(filename, options);

    }
    private static void runWC(String filename, Set<Character> options) throws IOException
    {
        File file = new File(filename);
        long numberOfBytes = file.length();
        int numberOfLines = 0;
        int numberOfWords = 0;
        int numberOfCharacters = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            
            
            String line;
            while((line = reader.readLine()) != null)
            {
                
                String[] words = line.split("\\s");
                
                for(String word : words)
                {
                    if(word.matches("\\S+"))
                        numberOfWords++;
                    

                }
                // numberOfWords += words.length;
                numberOfLines++;
            }
            
            

        }
        try(BufferedReader charReader = new BufferedReader(new FileReader(filename)))
            {
                
                while(charReader.read() != -1)
                {
                    numberOfCharacters++;
                }
            }
        StringBuilder sb = new StringBuilder();
        if(options.contains('l'))
            sb.append(numberOfLines + " " + filename);
        if(options.contains('w'))
            sb.append(numberOfWords + " " + filename);
        if(options.contains('c'))
            sb.append(numberOfBytes + " " + filename);
        if(options.contains('m'))
            sb.append(numberOfCharacters + " " + filename);
        if(options.isEmpty())
            System.out.println(numberOfLines + " " + numberOfWords + " " + numberOfBytes + " " + filename);
        else    
            System.out.println(sb);
    }
}
