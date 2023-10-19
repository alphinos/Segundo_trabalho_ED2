package Estrutura;

public class Generic<Key, Value> implements Comparable< Generic<?, ?> > { // Fonte: https://github.com/uotlaf/ED2-Atividade1/blob/master/src/Estruturas/Generico.java
    private Key key;
    private Value value;

    public Generic(Key key, Value value) {
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
        this.key = key;
    }

    public void setValue( Value value ){
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ " + this.key + " : " + this.value + " }";
    }

    public int compareTo(Generic<?, ?> o) {
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
}