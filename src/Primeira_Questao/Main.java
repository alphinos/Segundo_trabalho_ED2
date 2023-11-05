package Primeira_Questao;

import Estrutura.HashTable;
import Estrutura.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import RP.Report;

//FEITO POR CARLOS EDUARDO VERAS GOMES E ANDREI RAMOS LOPES

public class Main {
    public static void main( String[] args ){
        Random random = new Random();
        int min = 0;
        int max;

        StringBuilder conteudo;
        MultiMap< Integer, Integer > map;
//Inserir
        for ( int i = 10; i <= 50; i += 10 ){

            conteudo = new StringBuilder(  );

            String path = "./Relatórios/Primeira/" + i + ".txt";

            map = new MultiMap<>(i);
            max = i;
            for ( int j = 0; j < i; j++ ){
                map.put( random.nextInt( min, max), random.nextInt( min, max));
            }
            conteudo.append("INSERÇÃO NO MULTIMAPA ")
                    .append( ".\n\n" );
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

//            Report.writeReport( path, conteudo.toString() );

//BUSCAR
            conteudo.append("\n\nBUSCA NO MULTIMAPA ")
                    .append( ".\n\n" );
            conteudo.append("Multimapa com tamanho ").append( i )
                    .append( ".\n\n" );

            for ( int j = 0; j < i; j++ ){
                conteudo.append( map.findAll( j )==null ? "[ ]" : map.findAll(j) )
                        .append("\n");
            }

//            conteudo.append( map );

//            Report.writeReport( path, conteudo.toString() );



//REMOVER

            conteudo.append("\n\nREMOÇÃO DAS CHAVES PARES COM VALORES EXISTENTES NO MULTIMAPA ")
                    .append( ".\n\n" );
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

//            conteudo.append( map );
            for ( int j = 0; j < i; j++ ){
                if(j%2==0 && map.findAll(j)!=null){
                map.removeAll( j );
                }
            }
            conteudo.append(map);

            Report.writeReport( path, conteudo.toString() );
        }
    }
}
