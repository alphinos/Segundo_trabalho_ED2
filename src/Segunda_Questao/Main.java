package Segunda_Questao;

//FEITO POR CARLOS EDUARDO VERAS GOMES E ANDREI RAMOS LOPES

import RP.Report;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] paths = new String[ 2 ];
        paths[0] = "././Relatórios/Segunda/Documentos/ClubeDeRegatasDoFlamengo_História.txt";
        paths[1] = "././Relatórios/Segunda/Documentos/ClubeDeRegatasDoFlamengo_Introdução.txt";

        ArrayList< String > pages = new ArrayList<>();

        Plagiarism plagiarism = new Plagiarism( 10, paths );

        ArrayList< ArrayList<String> > plagiarismByHash = plagiarism.findPlagiarismByHash( "Relatórios/Segunda/teste.txt" );

        for ( ArrayList< String > a : plagiarismByHash ){
            System.out.println( a.get( 0 ) );
        }

        StringBuilder page = new StringBuilder();
        if ( !plagiarismByHash.isEmpty() ){
            for ( int i = 1; i < plagiarismByHash.size(); i++ ){
                String[] prevPhrase = plagiarismByHash.get( i - 1 ).get( 0 ).split( "\\s+" );
                String[] currPhrase = plagiarismByHash.get( i ).get( 0 ).split( "\\s+" );

                for ( int j = 1; j < prevPhrase.length; j++ ){
                    if ( currPhrase[ j - 1 ].equals( prevPhrase[ j ] ) ){
                        page.append( prevPhrase[ j - 1 ] )
                                .append( " " );
                    } else {
                        page.append( currPhrase[ j ] );
                        pages.add( page.toString() );
                        page = new StringBuilder();
                        page.append( plagiarismByHash.get( i ).get( 0 ) );
                    }
                    System.out.println( page );
                }
            }
        }

        String answerPath = "././Relatórios/Segunda/Respostas/" + "plagiarismByHash00";
        String[] arrPages = pages.toArray(new String[0]);
        Report.writeReport( answerPath, arrPages );

        ArrayList< ArrayList< String > > plagiarismByTree = plagiarism.findPlagiarismByTree( "Relatórios/Segunda/teste.txt" );

        page = new StringBuilder();
        if ( !plagiarismByTree.isEmpty() ){
            for ( int i = 1; i < plagiarismByTree.size(); i++ ){
                String[] prevPhrase = plagiarismByTree.get( i - 1 ).get( 0 ).split( "\\s+" );
                String[] currPhrase = plagiarismByTree.get( i ).get( 0 ).split( "\\s+" );
                if ( currPhrase[0].equals( prevPhrase[1] ) ){
                    page.append( prevPhrase[0] )
                            .append( " " );
                } else {
                    page.append( currPhrase[0] );
                    pages.add( page.toString() );
                    page = new StringBuilder();
                    page.append( plagiarismByTree.get( i ).get( 0 ) );
                }
            }
        }

        answerPath = "././Relatórios/Segunda/Respostas/" + "plagiarismByTree00";
        arrPages = pages.toArray(new String[0]);
        Report.writeReport( answerPath, arrPages );
    }
}
