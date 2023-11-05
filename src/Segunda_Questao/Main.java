package Segunda_Questao;

//FEITO POR CARLOS EDUARDO VERAS GOMES E ANDREI RAMOS LOPES

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] paths = new String[ 2 ];
        paths[0] = "Relatórios/Segunda/Documentos/ClubeDeRegatasDoFlamengo_História.txt";
        paths[1] = "Relatórios/Segunda/Documentos/ClubeDeRegatasDoFlamengo_Introdução.txt";

        Plagiarism plag = new Plagiarism( 2, paths );

        ArrayList< ArrayList<String> > a = plag.findPlagiarismByHash( "Relatórios/Segunda/teste.txt" );

        System.out.println();
        for ( ArrayList<String> t : a ){
            for ( String u : t )
                System.out.print( t );
            System.out.println();
        }
        System.out.println();

        for ( ArrayList<String> t : a ){
            System.out.print( t.get( 0 ) + " : " );
            for ( int i = 1; i < t.size(); i++ ){
                System.out.print( t.get( i ) + " " );
            }
            System.out.println();
        }
    }
}
