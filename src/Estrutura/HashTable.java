package Estrutura;

public class HashTable {
    private List[] registers;
    private int size;
    private final int max_size;

    public HashTable( int max_size ){
        this.registers = new List[ max_size ];
        this.max_size = max_size;
        this.size = 0;
    }

    public void insert( Object key, Object value ){
        Pair<?,?> data = new Pair<>(key, value);
        int index = hash( key );
        if ( registers[index] == null ){
            registers[index] = new List();
        }
        registers[ index ].insert( data );
        size++;
    }

    public Pair<?, ?> query(Object key ){
        int index = hash( key );
        return registers[ index ].query( key );
    }

    public Pair<?, ?> remove(Object key ){
        int index = hash( key );
        Pair<?, ?> aux = registers[ index ].remove( key );
        if ( aux != null )
            size--;
        return aux;
    }

    private int hash( Object key ) {
        int index = 0;
        if (key instanceof Integer) {
            index = Math.abs( (Integer) key ) % this.max_size;
        } else if ( key instanceof Double ) {
            int aux = ( (Double) key ).intValue();
            index = Math.abs( aux ) % this.max_size;
        } else if ( key instanceof String ) {
            int sum = 0;
            for ( char c : ( (String) key ).toCharArray() ){
                sum += (int) c;
            }
            index = sum % this.max_size;
        } else if ( key instanceof Character ) {
            index = ( ( int ) key ) % this.max_size;
        }
        return index;
    }
}
