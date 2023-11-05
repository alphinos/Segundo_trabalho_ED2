package Primeira_Questao;

import Estrutura.HashTable;
import Estrutura.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import RP.Report;

import java.lang.Math;

//FEITO POR CARLOS EDUARDO VERAS GOMES E ANDREI RAMOS LOPES

public class Main {

    public static double module(double x){
        if(x<0){
            x*=-1;
        }
        return x;
    }

    public static String checkBigO(int n, int comparisons){
        double constant,log,linear,log_linear,quadratic,cubic,base_two_exponential,factorial;
        constant=1;
        log=Math.log(n)/Math.log(2);// DALLYSON VAI TOMAR NO JAVA
        linear=n;
        log_linear=log*linear;
        quadratic=n*n;
        cubic=n*n*n;
        base_two_exponential=Math.pow(2,n);
        factorial=n;
        for(int i=n-1;i>1;i--){
            factorial*=i;
        }

        double min_distance=module((double)1-comparisons);
        if(module((double)log_linear-comparisons)<min_distance){
            min_distance=module((double)log_linear-comparisons);
        }
        //DEPOIS CONTINUO ESSA LÓGICA!
        return "a";
    }

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

            String[] content = new String[ 1 ];
            content[ 0 ] = conteudo.toString();

            Report.writeReport( path, content );
        }
    }
}
