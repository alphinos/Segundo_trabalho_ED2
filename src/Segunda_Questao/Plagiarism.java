package Segunda_Questao;

import Estrutura.List;
import Estrutura.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class Plagiarism {
    private final int phraseSize;
    private final ArrayList< DynamicMultiMap< String, ArrayList< String > > > phraseToDocs;

    private final ArrayList< AdaptedAVLTree< String, String > > treePhraseToDocs;

    public Plagiarism( int phraseSize, String[] paths ) {
        this.phraseSize = phraseSize;
        this.phraseToDocs = new ArrayList<>();

        this.treePhraseToDocs = new ArrayList<>();

        ArrayList< String > words;
        StringBuilder phrase;
        for ( String path : paths ){
            this.phraseToDocs.add( new DynamicMultiMap<>( 80, 80 ) );
            this.treePhraseToDocs.add( new AdaptedAVLTree< String, String >() );
            try {
                words = FileHandler.readAndSeparateTxt(path);
                words.add(path);

                for (int i = 0; i < words.size() - 1; i++) {
                    phrase = new StringBuilder();
                    for (int j = i; j < words.size() && j < i + this.phraseSize; j++) {
                        phrase.append(words.get(j));
                        if (j != i + this.phraseSize - 1)
                            phrase.append(" ");
                    }

                    this.phraseToDocs.get( this.phraseToDocs.size() - 1 )
                            .put(phrase.toString(), words);

                    this.treePhraseToDocs.get( this.phraseToDocs.size() - 1 )
                            .setRoot(
                                    this.treePhraseToDocs.get( this.treePhraseToDocs.size() - 1 )
                                            .insert(phrase.toString(), path)
                            );
                }
            } catch ( Exception e ){
                e.printStackTrace();
            }
        }
    }

    public int getPhraseSize() {
        return this.phraseSize;
    }

    public ArrayList< DynamicMultiMap< String, ArrayList< String > > > getPhraseToDocs() {
        return this.phraseToDocs;
    }

    public ArrayList< AdaptedAVLTree<String, String> > getTreePhraseToDocs() {
        return this.treePhraseToDocs;
    }

    public ArrayList< ArrayList<String> > findPlagiarismByHash(String path ) throws IOException {
        ArrayList< String > words;
        StringBuilder phrase;
        List< Pair< String, ArrayList< String > > > phraseFound;
        words = FileHandler.readAndSeparateTxt( path );

        words.add( path );

        ArrayList< ArrayList<String> > plagiarisms = new ArrayList<>();

        for ( int i = 0; i < words.size() - 1; i++ ) {
            ArrayList<String> phrase_docs = new ArrayList<>();

            phrase = new StringBuilder();
            for (int j = i; j < words.size() && j < i + this.phraseSize; j++) {
                phrase.append(words.get(j));
                if (j != i + this.phraseSize - 1)
                    phrase.append(" ");
            }

            for (DynamicMultiMap<String, ArrayList<String>> dyn : this.phraseToDocs) {
                phraseFound = dyn.findAll(phrase.toString());

                if (phraseFound == null)
                    continue;

                ArrayList<Pair<String, ArrayList<String>>> arr_phraseFound = phraseFound.toArray();

                for (Pair<String, ArrayList<String>> s : arr_phraseFound) {
                    if (phrase.toString().equals(s.getKey())) {
                        phrase_docs.add(s.getKey());
                        for (Pair<String, ArrayList<String>> pair : arr_phraseFound) {
                            phrase_docs.add(pair.getValue().get(pair.getValue().size() - 1));
                        }
                        plagiarisms.add(phrase_docs);
                    }
                }
            }
        }
        return plagiarisms;
    }

    public ArrayList< ArrayList<String> > findPlagiarismByTree( String path ) throws IOException {
        ArrayList< String > words;
        StringBuilder phrase;
        List< Pair< String,  String > > phraseFound;
        words = FileHandler.readAndSeparateTxt( path );

        words.add( path );

        ArrayList< ArrayList<String> > plagiarisms = new ArrayList<>();

        for ( int i = 0; i < words.size() - 1; i++ ) {
            ArrayList<String> phrase_docs = new ArrayList<>();

            phrase = new StringBuilder();
            for (int j = i; j < words.size() && j < i + this.phraseSize; j++) {
                phrase.append(words.get(j));
                if (j != i + this.phraseSize - 1)
                    phrase.append(" ");
            }

            for (AdaptedAVLTree<String, String> avltree : this.treePhraseToDocs) {
                phraseFound = avltree.queryAll(phrase.toString());

                if (phraseFound == null)
                    continue;

                ArrayList<Pair<String, String>> arr_phraseFound = phraseFound.toArray();

                for (Pair<String, String> s : arr_phraseFound) {
                    if (phrase.toString().equals(s.getKey())) {
                        phrase_docs.add(s.getKey());
                        for (Pair<String, String> pair : arr_phraseFound) {
                            phrase_docs.add(pair.getValue());
                        }
                        plagiarisms.add(phrase_docs);
                    }
                }
            }
        }
        return plagiarisms;
    }
}
