package Estrutura;

import sort.Sorter;

public class Table< Key > {
    private final Pair< Key, ?>[] registers;
    private Key[] keys;
    private Object[] values;
    private int size;

    private boolean ordered;

    public Table( int max_size ){
        this.registers = new Pair[ max_size ];
        this.size = 0;
        this.ordered = false;
    }

    public Key[] getKeys(){
        return this.keys;
    }

    public Object[] getValues(){
        return this.values;
    }

    public Pair< Key, ? > search(Key key){
        for ( Pair< Key, ? > word : this.registers ){
            if ( word.getKey() == key ) {
                return word;
            }
        }
        return null;
    }

    // Busca binária: retorna o índe do item encontrado
    public int binarySearch( Key key ) throws Exception {
        if ( !this.ordered ){
            throw new Exception( "Table must be ordered" );
        }

        if ( this.size == 0 ){
            return -1;
        }

        Pair< Key, ? > aux = new Pair<>( key, null );

        int left = 1, right = this.size, i;

        do {
            i = ( left + right ) / 2;
            if ( aux.compareTo( this.registers[ i ] ) > 0 )
                left = i + 1;
            else
                right = i - 1;
        } while ( aux.compareTo( this.registers[ i ] ) != 0 && left < right );

        if ( aux.compareTo( this.registers[ i ] ) == 0 ) return i;
        else return -1;
    }

    public void insert( Pair< Key, ? > word ) throws Exception {
        if ( this.size == ( this.registers.length ) ){
            throw new Exception( "Table is full" );
        }
        this.registers[ ++this.size ] = word;
        this.keys[ this.size ] = word.getKey();
        this.values[ this.size ] = word.getValue();
    }

    public Pair< Key, ? > remove(Key key ) throws Exception{
        int i;
        Pair< Key, ? > removed = null;
        for ( i = 0; i < this.size; i++ ){
            if ( this.registers[ i ].getKey() == key ){
                removed = this.registers[ i ];
                break;
            }
        }
        if ( removed == null ){
            throw new Exception( "Item not found" );
        }
        for ( int j = i + 1; j < this.size; j++ ){
            this.registers[ j - 1 ] = this.registers[ j ];
            this.keys[ j - 1 ] = this.keys[ j ];
            this.values[ j - 1 ] = this.values[ j ];
        }
        this.size--;
        return removed;
    }

    public void sort( Sorter sorter ){
        sorter.sort( this.registers );
        for ( int i = 0; i + 1 < this.size; i++ ){
            if ( this.registers[ i + 1 ].compareTo( this.registers[ i ] ) < 0 ){
                return;
            }
            this.keys[ i ] = this.registers[ i ].getKey();
            this.values[ i ] = this.registers[ i ].getValue();
        }
        this.ordered = true;
    }
}
