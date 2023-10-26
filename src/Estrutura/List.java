package Estrutura;

class Node{
    private Node previous;
    private Generic<?,?> data;
    private Node next;

    public Node(){
        this.previous = null;
        this.data = null;
        this.next = null;
    }

    public Node( Generic<?,?> data ){
        this.previous = null;
        this.data = data;
        this.next = null;
    }

    public Node getPrevious(){
        return this.previous;
    }

    public Generic<?,?> getData(){
        return this.data;
    }

    public Node getNext(){
        return this.next;
    }

    public void setPrevious( Node previous ){
        this.previous = previous;
    }

    public void setData( Generic<?,?> data ){
        this.data = data;
    }

    public void setNext( Node next ){
        this.next = next;
    }
}
public class List {
    private Node first;
    private Node last;

    private int length;

    public void push( Generic<?,?> data ){
        Node newnode = new Node( data );
        newnode.setNext( this.first );
        this.first = newnode;
    }

    public void push( Generic<?,?> data, int index ) throws Exception{
        if ( index < 0 || index > this.length )
            throw new Exception( "Index out of bounds" );
        if ( index == 0 )
            push( data );
        if ( index == this.length - 1 )
            insert( data );
        Node current = this.first;
        int index_current = 0;
        while ( index_current < index ){
            current = current.getNext();
            index_current++;
        }
        Node previous = current.getPrevious();
        Node next = current.getNext();
        previous.setNext( next );
    }
    public Generic<?,?> pop(  ) throws Exception{
        Generic<?,?> data = this.first.getData();
        this.first = first.getNext();
        this.first.setPrevious( null );
        return data;
    }
    public Generic<?,?> pop( int index ) throws Exception{
        if ( index < 0 || index > this.length )
            throw new Exception( "Index out of bounds" );
        if ( this.length == 0 )
            return null;
        if ( index == 0 )
            return this.pop();
        Node current = this.first;
        int index_current = 0;
        while ( index_current < index ){
            current = current.getNext();
            index_current++;
        }
        Node previous = current.getPrevious();
        Node next = current.getNext();
        if ( next != null )
            next.setPrevious( previous );
        previous.setNext( next );
        if ( current == this.last )
            this.last = previous;
        return current.getData();
    }
    public Generic<?,?> getFirst(  ){
        if ( this.length == 0 )
            return null;
        return this.first.getData();
    }

    public void insert( Generic<?,?> data ){
        Node newnode = new Node( data );
        this.last.setNext( newnode );
        this.last = newnode;
    }

    public Generic<?,?> query( Object key ) {
        Generic<?,?> aux = new Generic<>( key, null );
        Node current = this.first;
        while( current != null ){
            if ( current.getData().compareTo( aux ) == 0 )
                return current.getData();
            current = current.getNext();
        }
        return null;
    }

    public Generic<?,?> remove( Object key ){
        Generic<?,?> aux = new Generic<>( key, null );
        Node current = this.first;
        while( current != null ){
            if ( current.getData().compareTo( aux ) == 0 )
                break;
            current = current.getNext();
        }
        if ( current == null )
            return null;
        if ( current == this.first ){
            this.first = current.getNext();
            return current.getData();
        }
        Node previous = current.getPrevious();
        previous.setNext( current.getNext() );
        if ( current == this.last )
            this.last = previous;
        return current.getData();
    }
}
