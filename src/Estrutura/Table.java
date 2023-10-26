package Estrutura;

import sort.Sorter;

public class Table< Key > {
    private Generic< Key, ?>[] registers;
    private Key[] keys;
    private Object[] values;
    private int size;

    private boolean ordered;

    public Table( int max_size ){
        this.registers = new Generic[ max_size ];
        this.size = 0;
        this.ordered = false;
    }

    public Key[] getKeys(){
        return this.keys;
    }

    public Object[] getValues(){
        return this.values;
    }

    public Generic< Key, ? > search(Key key){
        for ( Generic< Key, ? > word : this.registers ){
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

        Generic< Key, ? > aux = new Generic<>( key, null );

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

    public void insert( Generic< Key, ? > word ) throws Exception {
        if ( this.size == ( this.registers.length ) ){
            throw new Exception( "Table is full" );
        }
        this.registers[ ++this.size ] = word;
        this.keys[ this.size ] = word.getKey();
        this.values[ this.size ] = word.getValue();
    }

    public Generic< Key, ? > remove( Key key ) throws Exception{
        int i;
        Generic< Key, ? > removed = null;
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

    public void setOrdered( Sorter sorter ){
        sorter.sort( ( Generic< ?, ? >[] ) this.registers );
        for ( int i = 0; i + 1 < this.size; i++ ){
            if ( this.registers[ i + 1 ].compareTo( this.registers[ i ] ) < 0 ){
                return;
            }
        }
        this.ordered = true;
    }
}
