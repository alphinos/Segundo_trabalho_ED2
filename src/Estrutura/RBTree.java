package Estrutura;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

class RBNode{
    private Generic<?,?> data;

    private RBNode father;
    private RBNode left;

    private RBNode right;

    private boolean color;

    public RBNode( Generic<?,?> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
        this.color = RBTree.RED;
    }

    public RBNode( Object key, Object value ){
        this.data = new Generic<>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
        this.color = RBTree.RED;
    }

    public Generic<?, ?> getData() {
        return this.data;
    }

    public RBNode getFather() {
        return this.father;
    }

    public RBNode getLeft() {
        return this.left;
    }

    public RBNode getRight() {
        return this.right;
    }

    public boolean getColor(){
        return this.color;
    }

    public void setData(Generic<?, ?> data) {
        this.data = data;
    }

    public void setFather(RBNode father) {
        this.father = father;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public void setColor( boolean color ){
        this.color = color;
    }

    public RBNode getSuccessor(){
        RBNode current = this;
        current = current.getRight();
        if ( current == null )
            return null;
        while( current.getLeft() != null ){
            current = current.getLeft();
        }
        return current;
    }
}

public class RBTree {
    private RBNode root;

    public static final boolean BLACK = false;
    public static final boolean RED = true;

    public void insert( Generic<?, ?> data ){
        RBNode previous = null;
        RBNode current = this.root;

        while ( current != null ){
            previous = current;
            if ( current.getData().compareTo( data ) <= 0 )
                current = current.getLeft();
            else
                current = current.getRight();
        }

        RBNode newnode = new RBNode( data );
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

    private void insertFixUP( RBNode newnode ){
        RBNode y;
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

    public Generic<?, ?> remove( Object key ){
        Generic<?, ?> dataKey = new Generic<>( key, null );
        RBNode current = this.root;
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

    private Generic<?, ?> delete( RBNode z ){
        RBNode y, x;

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
            z.getData().setKey( y.getData().getKey() );
            z.getData().setValue( y.getData().getValue() );
        }
        if ( y.getColor() == RBTree.BLACK )
            this.deleteFixUP( x );
        return y.getData();
    }

    private void deleteFixUP( RBNode x ){
        RBNode w;
        while ( x != this.root && x.getColor() == RBTree.BLACK ){
            if ( x == x.getFather().getLeft() ){
                w = x.getFather().getRight();
                if ( w.getColor() == RBTree.RED ){
                    w.setColor( RBTree.BLACK );
                    x.getFather().setColor( RBTree.RED );
                    this.leftRotation( x.getFather() );
                    w = x.getFather().getRight();
                }

                if ( w.getLeft().getColor() == RBTree.BLACK
                        && w.getRight().getColor() == RBTree.BLACK ){
                    w.setColor( RBTree.RED );
                    x = x.getFather();
                } else if ( w.getRight().getColor() == RBTree.BLACK ){
                    w.getLeft().setColor( RBTree.BLACK );
                    w.setColor( RBTree.RED );
                    this.rightRotation( w );
                    w = x.getFather().getRight();

                    w.setColor( x.getFather().getColor() );
                    x.getFather().setColor( RBTree.BLACK );
                    w.getRight().setColor( RBTree.BLACK );
                    this.leftRotation( x.getFather() );
                    x = this.root;
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

    private void rightRotation( RBNode x ){
        RBNode y = x.getLeft();
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

    private void leftRotation( RBNode x ){
        RBNode y = x.getRight();
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

    private void doubleRightRotation( RBNode node ){
        this.leftRotation( node.getLeft() );
        this.rightRotation( node );
    }

    private void doubleLeftRotation( RBNode node ){
        this.rightRotation( node.getLeft() );
        this.leftRotation( node );
    }
}
