package Estrutura;

class RBNode<Key>{
    private Pair<Key,?> data;

    private RBNode<Key> father;
    private RBNode<Key> left;

    private RBNode<Key> right;

    private boolean color;

    public RBNode( Pair<Key,?> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
        this.color = RBTree.RED;
    }

    public RBNode( Key key, Object value ){
        this.data = new Pair<>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
        this.color = RBTree.RED;
    }

    public Pair<?, ?> getData() {
        return this.data;
    }

    public RBNode<Key> getFather() {
        return this.father;
    }

    public RBNode<Key> getLeft() {
        return this.left;
    }

    public RBNode<Key> getRight() {
        return this.right;
    }

    public boolean getColor(){
        return this.color;
    }

    public void setData(Pair<Key, ?> data) {
        this.data = data;
    }

    public void setFather(RBNode<Key> father) {
        this.father = father;
    }

    public void setLeft(RBNode<Key> left) {
        this.left = left;
    }

    public void setRight(RBNode<Key> right) {
        this.right = right;
    }

    public void setColor( boolean color ){
        this.color = color;
    }

    public RBNode<Key> getSuccessor(){
        RBNode<Key> current = this;
        current = current.getRight();
        if ( current == null )
            return null;
        while( current.getLeft() != null ){
            current = current.getLeft();
        }
        return current;
    }
}

public class RBTree<Key> {
    private RBNode<Key> root;

    public static final boolean BLACK = false;
    public static final boolean RED = true;

    public void insert( Pair<Key, ?> data ){
        RBNode<Key> previous = null;
        RBNode<Key> current = this.root;

        while ( current != null ){
            previous = current;
            if ( current.getData().compareTo( data ) <= 0 )
                current = current.getLeft();
            else
                current = current.getRight();
        }

        RBNode<Key> newnode = new RBNode<Key>( data );
        newnode.setFather( previous );

        if ( previous == null ) {
            this.root = newnode;
        } else {
            if ( newnode.getData().compareTo( previous.getData() ) <= 0 )
                previous.setLeft( newnode );
            else
                previous.setRight( newnode );
        }
        this.insertFixUP( newnode );
    }

    private void insertFixUP( RBNode<Key> newnode ){
        RBNode<Key> y;
        while ( newnode.getFather().getColor() == RBTree.RED ){
            if ( newnode.getFather() == newnode.getFather().getFather().getLeft() ){
                y = newnode.getFather().getFather().getRight();
                if ( y.getColor() == RBTree.RED ){
                    newnode.getFather().setColor( RBTree.BLACK );
                    y.setColor( RBTree.BLACK );
                    newnode.getFather().getFather().setColor( RBTree.RED );
                    newnode = newnode.getFather().getFather();
                } else if ( newnode == newnode.getFather().getRight() ){
                    newnode = newnode.getFather();
                    this.leftRotation( newnode );
                }
                newnode.getFather().setColor( RBTree.BLACK );
                newnode.getFather().getFather().setColor( RBTree.RED );
            } else {
                y = newnode.getFather().getFather().getLeft();
                if ( y.getColor() == RBTree.RED ){
                    newnode.getFather().setColor( RBTree.BLACK );
                    y.setColor( RBTree.BLACK );
                    newnode.getFather().getFather().setColor( RBTree.RED );
                    newnode = newnode.getFather().getFather();
                } else if ( newnode == newnode.getFather().getLeft() ){
                    newnode = newnode.getFather();
                    this.leftRotation( newnode );
                }
                newnode.getFather().setColor( RBTree.BLACK );
                newnode.getFather().getFather().setColor( RBTree.RED );
            }
        }
        this.root.setColor( RBTree.BLACK );
    }

    public Pair<?, ?> remove(Object key ){
        Pair<?, ?> dataKey = new Pair<>( key, null );
        RBNode<Key> current = this.root;
        int cmp;

        while ( current != null ) {
            cmp = dataKey.compareTo(current.getData());
            if (cmp < 0) {
                current = current.getLeft();
            } else if (cmp > 0) {
                current = current.getRight();
            } else {
                return delete( current );
            }
        }
        return null;
    }

    private Pair<?, ?> delete(RBNode<Key> z ){
        RBNode<Key> y, x;

        if ( z.getLeft() == null || z.getRight() == null )
            y = z;
        else
            y = z.getSuccessor();

        if ( y.getLeft() != null )
            x = y.getLeft();
        else
            x = y.getRight();

        x.setFather( y.getFather() );

        if ( y.getFather() == null )
            this.root = x;
        else if ( y == y.getFather().getLeft() )
            y.getFather().setLeft( x );
        else
            y.getFather().setRight( x );

        if ( y != z ){
            Pair<?, ?> copy = new Pair<>( y.getData().getKey(), y.getData().getValue() );
            z.setData( (Pair<Key, ?>) copy );
        }
        if ( y.getColor() == RBTree.BLACK )
            this.deleteFixUP( x );
        return y.getData();
    }

    private void deleteFixUP( RBNode<Key> x ){
        RBNode<Key> w;
        while ( x != this.root && x.getColor() == RBTree.BLACK ){
            if ( x == x.getFather().getLeft() ){
                w = x.getFather().getRight();
                if ( w.getColor() == RBTree.RED ){
                    w.setColor( RBTree.BLACK );             // Caso 1
                    x.getFather().setColor( RBTree.RED );   // Caso 1
                    this.leftRotation( x.getFather() );     // Caso 1
                    w = x.getFather().getRight();           // Caso 1
                }

                if ( w.getLeft().getColor() == RBTree.BLACK
                        && w.getRight().getColor() == RBTree.BLACK ){
                    w.setColor( RBTree.RED );               // Caso 2
                    x = x.getFather();                      // Casp 2
                } else if ( w.getRight().getColor() == RBTree.BLACK ){
                    w.getLeft().setColor( RBTree.BLACK );   // Caso 3
                    w.setColor( RBTree.RED );               // Caso 3
                    this.rightRotation( w );                // Caso 3
                    w = x.getFather().getRight();           // Caso 3

                    w.setColor( x.getFather().getColor() ); // Caso 4
                    x.getFather().setColor( RBTree.BLACK ); // Caso 4
                    w.getRight().setColor( RBTree.BLACK );  // Caso 4
                    this.leftRotation( x.getFather() );     // Caso 4
                    x = this.root;                          // Caso 4
                }
            } else {
                w = x.getFather().getLeft();
                if ( w.getColor() == RBTree.RED ){
                    w.setColor( RBTree.BLACK );
                    x.getFather().setColor( RBTree.RED );
                    this.rightRotation( x.getFather() );
                    w = x.getFather().getLeft();
                }

                if ( w.getRight().getColor() == RBTree.BLACK
                        && w.getLeft().getColor() == RBTree.BLACK ){
                    w.setColor( RBTree.RED );
                    x = x.getFather();
                } else if ( w.getLeft().getColor() == RBTree.BLACK ){
                    w.getRight().setColor( RBTree.BLACK );
                    w.setColor( RBTree.RED );
                    this.leftRotation( w );
                    w = x.getFather().getLeft();

                    w.setColor( x.getFather().getColor() );
                    x.getFather().setColor( RBTree.BLACK );
                    w.getLeft().setColor( RBTree.BLACK );
                    this.rightRotation( x.getFather() );
                    x = this.root;
                }
            }
        }
        x.setColor( RBTree.BLACK );
    }

    private void rightRotation( RBNode<Key> x ){
        RBNode<Key> y = x.getLeft();
        x.setLeft( y.getRight() );
        y.getRight().setFather( x );
        y.setFather( x.getFather() );

        if ( x.getFather() == null ){
            this.root = y;
        } else {
            if ( x == x.getFather().getLeft() )
                x.getFather().setLeft( y );
            else
                x.getFather().setRight( y );
        }

        y.setRight( x );
        x.setFather( y );
    }

    private void leftRotation( RBNode<Key> x ){
        RBNode<Key> y = x.getRight();
        x.setRight( y.getLeft() );
        y.getLeft().setFather( x );
        y.setFather( x.getFather() );

        if ( x.getFather() == null ){
            this.root = y;
        } else {
            if ( x == x.getFather().getLeft() )
                x.getFather().setLeft( y );
            else
                x.getFather().setRight( y );
        }

        y.setLeft( x );
        x.setFather( y );
    }

    private void doubleRightRotation( RBNode<Key> node ){
        this.leftRotation( node.getLeft() );
        this.rightRotation( node );
    }

    private void doubleLeftRotation( RBNode<Key> node ){
        this.rightRotation( node.getLeft() );
        this.leftRotation( node );
    }
}
