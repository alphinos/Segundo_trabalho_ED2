package Primeira_Questao;

import Estrutura.Pair;

import java.util.Random;

import RP.Report;

public class Main {
    public static void main( String[] args ){
        Random random = new Random();
        int min = 0;
        int max;

        StringBuilder conteudo;
        MultiMap< Integer, Integer > map;

        for ( int i = 10; i <= 1_000_000; i *= 10 ){

            conteudo = new StringBuilder(  );

            String path = "./Relatórios/Primeira/" + i + ".txt";

            map = new MultiMap<>(i);
            max = i;
            for ( int j = 0; j < i; j++ ){
                map.put( random.nextInt( min, max), random.nextInt( min, max) );
            }

            conteudo.append("Multimapa com tamanho ").append( i )
                    .append( ".\n\n" );

            conteudo.append("Total de atribuições realizadas pelas operações do multimapa: ")
                    .append( map.getAssignments() )
                    .append("\n");

            conteudo.append("Total de comparações realizadas pelas operações do multimapa: ")
                    .append( map.getComparisons() )
                    .append("\n");

            conteudo.append("Total de elementos no mapa: ")
                    .append( map.getTotalItems() )
                    .append("\n")
                    .append("\n");

            conteudo.append( map );

            Report.writeReport( path, conteudo.toString() );
        }

    }
}
