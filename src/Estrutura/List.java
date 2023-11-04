package Estrutura;

class Node<E> {
    private Node<E> previous;
    private E data;
    private Node<E> next;

    public Node(){
        this.previous = null;
        this.data = null;
        this.next = null;
    }

    public Node( E data ){
        this.previous = null;
        this.data = data;
        this.next = null;
    }

    public Node<E> getPrevious(){
        return this.previous;
    }

    public E getData(){
        return this.data;
    }

    public Node<E> getNext(){
        return this.next;
    }

    public void setPrevious( Node<E> previous ){
        this.previous = previous;
    }

    public void setData( E data ){
        this.data = data;
    }

    public void setNext( Node<E> next ){
        this.next = next;
    }
}
public class List<E> {
    private Node<E> first;
    private Node<E> last;

    private int length;

    private long comparisons;
    private long assignments;

    public List(){
        this.length = 0;
    }

    public int getLength(){
        return this.length;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAssignments() {
        return assignments;
    }

    public void push( E data ){
        Node<E> newnode = new Node<E>( data );                  this.assignments++;
        newnode.setNext( this.first );                          this.assignments++;
        this.first = newnode;                                   this.assignments++;
    }

    public void push( E data, int index ) throws Exception{
        if ( index < 0 || index > this.length ){                this.assignments++; this.comparisons++;
            throw new Exception( "Index out of bounds" );}
        if ( index == 0 ){                                      this.comparisons++;
            push( data );                                       this.assignments++;
        }
        if ( index == this.length - 1 ) {                       this.comparisons++;
            this.insert( data );                                this.assignments++;
        }
        Node<E> current = this.first;                           this.assignments++;
        int index_current = 0;                                  this.assignments++;
        while ( index_current < index ){                        this.comparisons++;
            current = current.getNext();                        this.assignments++;
            index_current++;                                    this.assignments++;
        }
        Node<E> previous = current.getPrevious();               this.assignments++;
        Node<E> next = current.getNext();                       this.assignments++;
        previous.setNext( next );                               this.assignments++;
        this.length++;                                          this.assignments++;
    }
    public E pop(  ) {
        E data = this.first.getData();                          this.assignments++;
        this.first = first.getNext();                           this.assignments++;
        this.first.setPrevious( null );                         this.assignments++;
        this.length--;                                          this.assignments++; this.assignments++;
        return data;
    }
    public E pop( int index ) throws Exception{
        this.assignments++;
        if ( index < 0 || index > this.length )
            throw new Exception( "Index out of bounds" );
        this.assignments++;
        if ( this.length == 0 )
            return null;
        this.assignments++;
        if ( index == 0 )
            return this.pop();
        Node<E> current = this.first;                           this.assignments++;
        int index_current = 0;                                  this.assignments++;
        while ( index_current < index ){                        this.comparisons++;
            current = current.getNext();                        this.assignments++;
            index_current++;                                    this.assignments++;
        }
        Node<E> previous = current.getPrevious();               this.assignments++;
        Node<E> next = current.getNext();                       this.assignments++;
                                                                this.assignments++; this.comparisons++;
        if ( next != null )
            next.setPrevious( previous );
        previous.setNext( next );                               this.assignments++;
                                                                this.assignments++; this.comparisons++;
        if ( current == this.last )
            this.last = previous;
        this.length--;                                          this.assignments++; this.assignments++;
        return current.getData();
    }
    public E getFirst(  ){                                      this.comparisons++; this.assignments++;
        if ( this.length == 0 )
            return null;
        return this.first.getData();
    }

    public void insert( E data ){
        Node<E> newnode = new Node<E>( data );                  this.assignments++;
        if ( this.last == null ){                               this.comparisons++;
            this.first = newnode;                               this.assignments++;
        } else {
            this.last.setNext( newnode );                       this.assignments++;
        }
        this.last = newnode;                                    this.assignments++;
        this.length++;                                          this.assignments++;
    }

    public E query( E key ) {
        Pair<?, ?> aux = new Pair<>( key, null );               this.assignments++;
        Node<E> current = this.first;                           this.assignments++;
        Pair<?, ?> curr;                                        this.assignments++;
        while( current != null ){                               this.comparisons++; this.comparisons++;
            if ( current.getData() instanceof Pair<?,?> )
                curr = ( Pair<?, ?> )current.getData();
            else
                curr = new Pair<>(current.getData(), null);
            this.assignments++; this.comparisons++;
            if ( curr.compareTo( aux ) == 0 )
                return current.getData();
            current = current.getNext();                        this.assignments++;
        }
        return null;
    }

    public E remove( E key ){
        Pair<?, ?> aux = new Pair<>( key, null );               this.assignments++;
        Node<E> current = this.first;                           this.assignments++;
        Pair<?, ?> curr;                                        this.assignments++;
        while( current != null ){                               this.comparisons++; this.comparisons++;
            if ( current.getData() instanceof Pair<?,?> )
                curr = ( Pair<?, ?> )current.getData();
            else
                curr = new Pair<>(current.getData(), null);
            this.assignments++; this.comparisons++;
            if ( curr.compareTo( aux ) == 0 )
                break;
            current = current.getNext();                        this.assignments++;
        }
        this.comparisons++;
        if ( current == null )
            return null;
        this.comparisons++;
        if ( current == this.first ){
            this.first = current.getNext();                     this.assignments++;
            return current.getData();
        }
        Node<E> previous = current.getPrevious();               this.assignments++;
        previous.setNext( current.getNext() );                  this.assignments++;
        if ( current == this.last ) {                           this.comparisons++;
            this.last = previous;                               this.assignments++;
        }
        this.length--;                                          this.assignments++;
        return current.getData();
    }

    public void printList(  ){
        System.out.println( this );
    }

    public String toString(){
        StringBuilder texto = new StringBuilder("[ ");
        Node< E > curr = this.first;
        while ( curr != null ){
            texto.append(curr.getData().toString());
            if ( curr.getNext() != null )
                texto.append( ", " );
            curr = curr.getNext();
        }
        texto.append( " ]" );

        return texto.toString();
    }
}
