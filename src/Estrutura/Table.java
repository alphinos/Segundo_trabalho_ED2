package Estrutura;

public class Table< Key > {
    private Generic< Key, ?> registers[];
    private int size;

    public Table( int max_size ){
        this.registers = new Generic[max_size];
        this.size = 0;
    }

    public Generic< Key, ? > search( Key key ){
        for ( Generic< Key, ? > word : this.registers ){
            if ( word.getKey() == key ) {
                return word;
            }
        }
        return null;
    }

    public void insert( Generic< Key, ? > word ) throws Exception {
        if ( this.size == ( this.registers.length ) ){
            throw new Exception( "Table is full" );
        }
        this.registers[ ++this.size ] = word;
    }

    public Generic< Key, ? > remove( Key key ){
        int i;
        Generic< Key, ? > removed = null;
        for ( i = 0; i < this.size; i++ ){
            if ( this.registers[ i ].getKey() == key ){
                removed = this.registers[ i ];
                break;
            }
        }
        if ( removed == null ){
            return null;
        }
        for ( int j = i + 1; j < this.size; j++ ){
            this.registers[ j - 1 ] = this.registers[ j ];
        }
        this.size--;
        return removed;
    }
}
