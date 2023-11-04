package Primeira_Questao;

import Estrutura.List;
import Estrutura.Pair;

public class MultiMap<Key, Value> {
    private final int max_size;
    private final List< Pair< Key, Value > >[] registers;

    private long comparisons;
    private long assignments;

    private long totalItems;

    public MultiMap( int capacity ){
        this.max_size = capacity;
        this.registers = new List[ this.max_size ];
        this.assignments = 0;
        this.comparisons = 0;
        this.totalItems = 0;
    }

    public int getMax_size() {
        return this.max_size;
    }

    public long getComparisons() {
        return this.comparisons;
    }

    public long getAssignments() {
        return assignments;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void put(Key key, Value value ){
        int index = hash( key );                                this.assignments++;
        Pair<Key, Value> data = new Pair<>(key, value);         this.assignments++;
        if ( registers[index] == null ){                        this.comparisons++;
            registers[index] = new List<>();                    this.assignments++;
        }
        registers[ index ].insert( data );                      this.assignments++;
        this.totalItems++;
    }

    public List< Pair< Key, Value > > findAll( Key key ){
        int index = hash( key );                                this.assignments++; this.assignments++;
        return this.registers[ index ];
    }

    public List< Pair< Key, Value > > removeAll( Key key ){
        int index = hash( key );                                this.assignments++; this.assignments++;
        List< Pair<Key, Value > > allOfKey = this.registers[ index ];
        this.registers[ index ] = null;                         this.assignments++; this.assignments++;
        this.totalItems-= allOfKey.getLength();
        return allOfKey;
    }

    private int hash( Key key ) {
        int index = 0;                                          this.assignments++;
        if (key instanceof Integer) {                           this.comparisons++;
            index = Math.abs( (Integer) key ) % this.max_size;  this.assignments++;
        } else if ( key instanceof Double ) {                   this.comparisons++;
            int aux = ( (Double) key ).intValue();              this.assignments++;
            index = Math.abs( aux ) % this.max_size;            this.assignments++;
        } else if ( key instanceof String ) {                   this.comparisons++;
            int sum = 0;                                        this.assignments++;
            for ( char c : ( (String) key ).toCharArray() ){    this.assignments++;
                sum += c;                                       this.assignments++;
            }
            index = sum % this.max_size;                        this.assignments++;
        } else if ( key instanceof Character ) {                this.comparisons++;
            index = ( ( int ) key ) % this.max_size;            this.assignments++;
        }
        return index;
    }

    public void printMap(  ){
        System.out.println( this );
    }

    public String toString(  ){
        StringBuilder text = new StringBuilder( "{\n" );
        for ( List<Pair<Key, Value>> register : this.registers ) {
            if (register != null) {
                text.append("\t");
                text.append( register );
                text.append( "\n" );
            } else {
                text.append( "\t[  ]\n" );
            }
        }
        text.append( "}" );

        return text.toString();
    }
}
