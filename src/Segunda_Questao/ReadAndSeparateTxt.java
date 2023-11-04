package Segunda_Questao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.lang.String;

public class ReadAndSeparateTxt {
    public static ArrayList<String> readAndSeparateTxt(String path) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        ArrayList<String> allWords = new ArrayList<>();
        while (linha != null) {
            System.out.println(linha);
            String[] temp = linha.split("\\s+");
            allWords.addAll(Arrays.asList(temp));
            linha = buffRead.readLine();
        }
        buffRead.close();
        return allWords;
    }

    public int numberOfWords(ArrayList<String> allWords){return allWords.size();}
}