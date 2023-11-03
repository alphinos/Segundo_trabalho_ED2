package Primeira_Questao;

import Estrutura.List;
import Estrutura.Pair;

public class MultiMap<Key, Value> {
    private final int max_size;
    private final List< Pair< Key, Value > >[] registers;

    public MultiMap( int capacity ){
        this.max_size = capacity;
        this.registers = new List[ this.max_size ];
    }

    public void put( Key key, Value value ){
        int index = hash( key );
        Pair<Key, Value> data = new Pair<>(key, value);
        if ( registers[index] == null ){
            registers[index] = new List<>();
        }
        registers[ index ].insert( data );
    }

    public List< Pair< Key, Value > > findAll( Key key ){
        int index = hash( key );
        return this.registers[ index ];
    }

    public List< Pair< Key, Value > > removeAll( Key key ){
        int index = hash( key );
        List< Pair<Key, Value > > allOfKey = this.registers[ index ];
        this.registers[ index ] = null;
        return allOfKey;
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

    public void printMap(  ){
        System.out.println("{");
        for ( List<Pair<Key, Value>> register : this.registers ) {
            if (register != null) {
                System.out.print("\t");
                register.printList();
            } else {
                System.out.println("\t[  ]");
            }
        }
        System.out.println("}");
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
