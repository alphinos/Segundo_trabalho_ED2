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

    public List(){
        this.length = 0;
    }

    public void push( E data ){
        Node<E> newnode = new Node<E>( data );
        newnode.setNext( this.first );
        this.first = newnode;
    }

    public void push( E data, int index ) throws Exception{
        if ( index < 0 || index > this.length )
            throw new Exception( "Index out of bounds" );
        if ( index == 0 )
            push( data );
        if ( index == this.length - 1 )
            insert( data );
        Node<E> current = this.first;
        int index_current = 0;
        while ( index_current < index ){
            current = current.getNext();
            index_current++;
        }
        Node<E> previous = current.getPrevious();
        Node<E> next = current.getNext();
        previous.setNext( next );
        this.length++;
    }
    public E pop(  ) {
        E data = this.first.getData();
        this.first = first.getNext();
        this.first.setPrevious( null );
        this.length--;
        return data;
    }
    public E pop( int index ) throws Exception{
        if ( index < 0 || index > this.length )
            throw new Exception( "Index out of bounds" );
        if ( this.length == 0 )
            return null;
        if ( index == 0 )
            return this.pop();
        Node<E> current = this.first;
        int index_current = 0;
        while ( index_current < index ){
            current = current.getNext();
            index_current++;
        }
        Node<E> previous = current.getPrevious();
        Node<E> next = current.getNext();
        if ( next != null )
            next.setPrevious( previous );
        previous.setNext( next );
        if ( current == this.last )
            this.last = previous;
        this.length--;
        return current.getData();
    }
    public E getFirst(  ){
        if ( this.length == 0 )
            return null;
        return this.first.getData();
    }

    public void insert( E data ){
        Node<E> newnode = new Node<E>( data );
        this.last.setNext( newnode );
        this.last = newnode;
        this.length++;
    }

    public E query( E key ) {
        Pair<?, ?> aux = new Pair<>( key, null );
        Node<E> current = this.first;
        Pair<?, ?> curr;
        while( current != null ){
            if ( current.getData() instanceof Pair<?,?> )
                curr = ( Pair<?, ?> )current.getData();
            else
                curr = new Pair<>(current.getData(), null);
            if ( curr.compareTo( aux ) == 0 )
                return current.getData();
            current = current.getNext();
        }
        return null;
    }

    public E remove( E key ){
        Pair<?,?> aux = new Pair<>( key, null );
        Node<E> current = this.first;
        Pair<?, ?> curr;
        while( current != null ){
            if ( current.getData() instanceof Pair<?,?> )
                curr = ( Pair<?, ?> )current.getData();
            else
                curr = new Pair<>(current.getData(), null);
            if ( curr.compareTo( aux ) == 0 )
                break;
            current = current.getNext();
        }
        if ( current == null )
            return null;
        if ( current == this.first ){
            this.first = current.getNext();
            return current.getData();
        }
        Node<E> previous = current.getPrevious();
        previous.setNext( current.getNext() );
        if ( current == this.last )
            this.last = previous;
        this.length--;
        return current.getData();
    }
}
