package Segunda_Questao;

import Estrutura.AVLTree;
import Estrutura.List;
import Estrutura.Pair;
import Estrutura.RBTree;

import java.io.IOException;
import java.util.ArrayList;

public class Plagiarism {
    private final int phraseSize;
    private final DynamicMultiMap< String, ArrayList< String >> phraseToDocs;

    private final AVLTree< String > treePhraseToDocs;

    public Plagiarism( int phraseSize, String[] paths ) {
        this.phraseSize = phraseSize;
        this.phraseToDocs = new DynamicMultiMap<>( 80, 80 );

        this.treePhraseToDocs = new AVLTree<>();

        ArrayList< String > words;
        StringBuilder phrase;
        for ( String path : paths ){
            try {
                words = ReadAndSeparateTxt.readAndSeparateTxt(path);
                words.add(path);

                for (int i = 0; i < words.size() - 1; i++) {
                    phrase = new StringBuilder();
                    for (int j = i; j < words.size() && j < i + this.phraseSize; j++) {
                        phrase.append(words.get(j));
                        if (j != i + this.phraseSize - 1)
                            phrase.append(" ");
                    }

                    this.phraseToDocs.put(phrase.toString(), words);

                    this.treePhraseToDocs.insert(phrase.toString(), path);
                }
            } catch ( Exception e ){
                e.printStackTrace();
            }
        }
    }

    public int getPhraseSize() {
        return this.phraseSize;
    }

    public DynamicMultiMap<String, ArrayList<String>> getPhraseToDocs() {
        return this.phraseToDocs;
    }

    public AVLTree<String> getTreePhraseToDocs() {
        return this.treePhraseToDocs;
    }

    public ArrayList< ArrayList<String> > findPlagiarismByHash(String path ) throws IOException {
        ArrayList< String > words;
        StringBuilder phrase;
        List< Pair< String, ArrayList< String > > > phraseFound;

        words = ReadAndSeparateTxt.readAndSeparateTxt( path );
        words.add( path );

        System.out.println( words );

        ArrayList< ArrayList<String> > plagiarisms = new ArrayList<>();

        for ( int i = 0; i < words.size() - 1; i++ ){
            ArrayList<String> phrase_docs = new ArrayList<>();

            phrase = new StringBuilder();
            for ( int j = i; j < words.size() && j < i + this.phraseSize; j++ ){
                phrase.append( words.get(j) );
                if ( j != i + this.phraseSize - 1 )
                    phrase.append( " " );
            }

            System.out.println( phrase );

            phraseFound = this.phraseToDocs.findAll( phrase.toString() );

            if ( phraseFound != null ){
                System.out.println( phraseFound );

                ArrayList< Pair< String, ArrayList< String > > > arr_phraseFound = phraseFound.toArray();
                phrase_docs.add( arr_phraseFound.get( 0 ).getKey() );
                for ( Pair<String, ArrayList<String>> pair : arr_phraseFound ){
                    phrase_docs.add( pair.getValue().get( pair.getValue().size() - 1 ) );
                }
                plagiarisms.add( phrase_docs );
            }
        }

        return plagiarisms;
    }

    public ArrayList< ArrayList<String> > findPlagiarismByTree(String path ) throws IOException {
        ArrayList< String > words;
        StringBuilder phrase;

        words = ReadAndSeparateTxt.readAndSeparateTxt( path );
        words.add( path );

        ArrayList< ArrayList<String> > plagiarisms = new ArrayList<>();

        for ( int i = 0; i < words.size() - 1; i++ ){
            ArrayList<String> phrase_docs = new ArrayList<>();

            phrase = new StringBuilder();
            for ( int j = i; j < words.size() && j < i + this.phraseSize; j++ ){
                phrase.append( words.get(j) );
                if ( j != i + this.phraseSize - 1 )
                    phrase.append( " " );
            }

            Pair<String, ?> found = this.treePhraseToDocs.query( phrase.toString() );

            if ( found != null ){
                phrase_docs.add( found.getKey() );
                phrase_docs.add( (String) found.getValue() );
                plagiarisms.add( phrase_docs );
            }
        }

        return plagiarisms;
    }
}
