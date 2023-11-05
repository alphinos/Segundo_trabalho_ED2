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

    public static String checkBigO(long n, long comparisons){
        LinkedHashMap<String,Double> big_o = new LinkedHashMap<>();
        String []keys = {"O(1)","O(log n)","O(n)","O(n.log n)","O(n^2)","O(n^3)","O(2^n)","O(n!)"};
        big_o.put("O(1)", Double.valueOf(1));
        big_o.put("O(log n)",Math.log(n)/Math.log(2));
        big_o.put("O(n)",Double.valueOf(n));
        big_o.put("O(n.log n)",Double.valueOf(n*(Math.log(n)/Math.log(2))));
        big_o.put("O(n^2)",Double.valueOf(n*n));
        big_o.put("O(n^3)",Double.valueOf(n*n*n));
        big_o.put("O(2^n)",Math.pow(2,n));
        double factorial=n;
        for(long i=n-1;i>1;i--){
            factorial*=i;
        }
        big_o.put("O(n!)",factorial);

        String big_o_key="O(1)";
        double min_distance=module(big_o.get(big_o_key)-comparisons);
        for(String k:keys){
            if(min_distance>module(big_o.get(k)-comparisons)){
            min_distance=module(big_o.get(k)-comparisons);
            big_o_key=k;
            }
        }
        System.out.println(big_o_key);
        System.out.println(min_distance);
        System.out.println(big_o.get(big_o_key));
        System.out.println(comparisons);

        return String.format("A complexidade na notação big O é de %s:", big_o_key);
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

            conteudo.append(checkBigO(map.getTotalItems(),map.getComparisons()))
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


//REMOVER

            conteudo.append("\n\nREMOÇÃO DAS CHAVES PARES COM VALORES EXISTENTES NO MULTIMAPA ")
                    .append( ".\n\n" );
            conteudo.append("Multimapa com tamanho ").append( i )
                    .append( ".\n\n" );

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
