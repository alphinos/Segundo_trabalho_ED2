package Estrutura;

public class Pair<Key, Value> implements Comparable<Pair<?, ?>> { // Fonte: https://github.com/uotlaf/ED2-Atividade1/blob/master/src/Estruturas/Generico.java
    private Key key;
    private Value value;

    public Pair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return this.key;
    }

    public Value getValue() {
        return this.value;
    }

    public void setKey( Key key ){
        if (key != null)
            this.key = (Key) key;
    }

    public void setValue( Value value ){
        if (value != null)
            this.value = (Value) value;
    }

    @Override
    public String toString() {
        return "{ " + this.key + " : " + this.value + " }";
    }

    public int compareTo(Pair<?, ?> o) {
        // Teste do tipo do valor object
        if (this.key instanceof Integer && o.getKey() instanceof  Integer ) {
            return ((Integer) this.key).compareTo((Integer) o.getKey() );
        } else if (this.key instanceof Double && o.getKey() instanceof  Double) {
            return ((Double) this.key).compareTo((Double) o.getKey());
        } else if ( this.key instanceof  String && o.getKey() instanceof  String ){
            return ((String) this.key).compareTo((String) o.getKey() );
        } else {
            try {
                throw new Exception( "Type not accepted" );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int compareTo(Pair<?, ?> o, boolean value) {
        if ( value == false )
            return this.compareTo( o );
        // Teste do tipo do valor object
        if (this.value instanceof Integer && o.getValue() instanceof  Integer ) {
            return ((Integer) this.value).compareTo((Integer) o.getValue() );
        } else if (this.value instanceof Double && o.getValue() instanceof  Double) {
            return ((Double) this.value).compareTo((Double) o.getValue());
        } else if ( this.value instanceof  String && o.getValue() instanceof  String ){
            return ((String) this.value).compareTo((String) o.getValue() );
        } else {
            try {
                throw new Exception( "Type not accepted" );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}