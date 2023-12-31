package Estrutura;

public class HashTable<Key, Value> {
    private final List< Pair<Key, Value> >[] registers;
    private int size;
    private final int max_size;

    public HashTable( int max_size ){
        this.registers = new List[ max_size ];
        this.max_size = max_size;
        this.size = 0;
    }

    public void insert( Key key, Value value ){
        int index = hash( key );
        Pair<Key, Value> data = new Pair<>(key, value);
        if ( registers[index] == null ){
            registers[index] = new List<>();
        }
        registers[ index ].insert( data );
        size++;
    }

    public Pair<Key, Value> query( Key key ){
        int index = hash( key );
        Pair<Key, Value> keyPair = new Pair<>( key, null );
        return registers[ index ].query( keyPair );
    }

    public Pair<Key, Value> remove( Key key ){
        int index = hash( key );
        Pair<Key, Value> keyPair = new Pair<>( key, null );
        Pair<Key, Value> aux = registers[ index ].remove( keyPair );
        if ( aux != null )
            size--;
        return aux;
    }

    private int hash( Key key ) {
        int index = 0;
        if (key instanceof Integer) {
            index = Math.abs( (Integer) key ) % this.max_size;
        } else if ( key instanceof Double ) {
            int aux = ( (Double) key ).intValue();
            index = Math.abs( aux ) % this.max_size;
        } else if ( key instanceof String ) {
            int sum = 0;
            for ( char c : ( (String) key ).toCharArray() ){
                sum += c;
            }
            index = sum % this.max_size;
        } else if ( key instanceof Character ) {
            index = ( ( int ) key ) % this.max_size;
        }
        return index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
