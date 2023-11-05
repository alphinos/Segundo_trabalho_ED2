package Segunda_Questao;

import Estrutura.List;
import Estrutura.Pair;

import java.util.ArrayList;

public class DynamicMultiMap<Key, Value> {

    private final int initial_size;
    private int max_size;
    private int increment;
    private ArrayList< List< Pair< Key, Value > > > registers;
    private long comparisons;
    private long assignments;
    private int totalIndex;
    private long totalItems;

    public DynamicMultiMap(int capacity, int increment ){
        this.initial_size = capacity;
        this.max_size = capacity;
        this.increment = increment;
        this.registers = new ArrayList<>( this.max_size );
        for ( int i = 0; i < this.max_size; i++ )
            this.registers.add( null );
        this.assignments = 0;
        this.comparisons = 0;
        this.totalItems = 0;
        this.totalIndex = 0;
    }

    public int getInitial_size(){
        return this.initial_size;
    }

    public int getMax_size() {
        return this.max_size;
    }

    public long getComparisons() {
        return this.comparisons;
    }

    public long getAssignments() {
        return this.assignments;
    }

    public long getTotalItems() {
        return this.totalItems;
    }

    public int getTotalIndex(){
        return this.totalIndex;
    }

    public void setIncrement( int increment ){
        this.increment = increment;
    }

    public void put(Key key, Value value ){
        if ( this.totalIndex == this.registers.size() - 1 ){
            this.registers = this.reallocateMap();
        }
        int index = hash( key );                                        this.assignments++;
        if ( index >= this.registers.size() )
            this.registers = this.reallocateMap();

        index = hash( key );

        Pair<Key, Value> data = new Pair<>(key, value);                 this.assignments++;
        if ( !this.registers.isEmpty()
                && this.registers.get(index) == null ){                 this.comparisons++;
            this.registers.set(index, new List<>());                    this.assignments++;
            this.totalIndex++;
        }
        this.registers.get(index).insert( data );                       this.assignments++;
        this.totalItems++;                                              this.assignments++;
    }

    public List< Pair< Key, Value > > findAll( Key key ){
        int index = hash( key );                                        this.assignments++; this.assignments++;
        return this.registers.get(index);
    }

    public List< Pair< Key, Value > > removeAll( Key key ){
        int index = hash( key );                                        this.assignments++; this.assignments++;
        List< Pair<Key, Value > > allOfKey = this.registers.get(index);
        this.registers.set(index, null);                                this.assignments++; this.assignments++;
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

    private int hashQuadratico(Key key,int k){
        if(k==0){
            return hash(key);
        }
        return (hashQuadratico(key,k-1)+k) % this.max_size;
    }

    public void printMap(  ){
        System.out.println( this );
    }

    private ArrayList< List< Pair< Key, Value > > > reallocateMap(){
        this.max_size += this.increment;
        ArrayList< List< Pair< Key, Value > > > newmap = new ArrayList<>( this.max_size );
        for ( int i = 0; i < this.max_size; i++ )
            newmap.add( null );

        for ( List< Pair< Key, Value > > linkedElms : this.registers ){
            if ( linkedElms != null ){
                int index = hash( linkedElms.getFirst().getKey() );
                newmap.set(index, linkedElms);
            }
        }

        return newmap;
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
