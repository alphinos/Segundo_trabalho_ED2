package Primeira_Questao;

import Estrutura.Pair;
import jdk.javadoc.doclet.Reporter;

import java.util.Random;

import RP.Report;

public class Main {
    public static void main( String[] args ){
        Random random = new Random();
        int min = 0;
        int max;

        StringBuilder conteudo = new StringBuilder(  );

        for ( int i = 10; i <= 1_000_000; i *= 10 ){

            String path = "./Relatórios/Primeira/" + i + ".txt";

            MultiMap< Integer, Integer > map = new MultiMap<>(i);
            max = i;
            for ( int j = 0; j < i; j++ ){
                map.put( random.nextInt( min, max), random.nextInt( min, max) );
            }

            conteudo.append( map );

            Report.writeReport( path, conteudo.toString() );
        }
    }
}
