package Segunda_Questao;

import java.io.*;
import java.util.ArrayList;

import java.lang.String;
import java.util.Scanner;

public class FileHandler {
    public static ArrayList<String> readAndSeparateTxt(String path) {

        File file = new File( path );

        ArrayList< String > allTextWords = new ArrayList<>();

        if ( file.exists() && file.canRead() ){
            try ( Scanner input = new Scanner( file ).useDelimiter( "\\s+" ) ){
                while ( input.hasNext() )
                    allTextWords.add( input.next() );
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return allTextWords;

//        BufferedReader buffRead = null;
//        try {
//            buffRead = new BufferedReader(new FileReader(path));
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        String linha = "";
//        ArrayList<String> allWords = new ArrayList<>();
//        while (linha != null) {
//            String[] temp = linha.split("\\s+");
//
//            for (String palavra : temp) {
//                // Verificar se a palavra não está vazia
//                if (!palavra.isEmpty() && !palavra.equals("\n")) {
//                    allWords.add(palavra.trim());
//                }
//            }
//
//            linha = buffRead.readLine();
//        }
//        buffRead.close();
//        return allWords;
    }

    public int numberOfWords(ArrayList<String> allWords){return allWords.size();}
}