package Estrutura;

class AVLNode{
    private Generic<?,?> data;

    private AVLNode father;
    private AVLNode left;

    private AVLNode right;

    private int height;

    public AVLNode( Generic<?,?> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public AVLNode( Object key, Object value ){
        this.data = new Generic<>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public Generic<?, ?> getData() {
        return this.data;
    }

    public AVLNode getFather() {
        return this.father;
    }

    public AVLNode getLeft() {
        return this.left;
    }

    public AVLNode getRight() {
        return this.right;
    }

    public int getHeight(){
        return this.height;
    }

    public void setData(Generic<?, ?> data) {
        this.data = data;
    }

    public void setFather(AVLNode father) {
        this.father = father;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
        left.setFather( this );
    }

    public void setRight(AVLNode right) {
        this.right = right;
        left.setFather( this );
    }

    public void setHeight( int height ){
        this.height = height;
    }
}

public class AVLTree {
    private AVLNode root;

    public AVLNode insert( Object key, Object value, AVLNode t ){
        Generic<?, ?> data = new Generic<>( key, value );
        AVLNode current = this.root;
        if ( t == null ){
            t = new AVLNode( data );
        } else if ( data.compareTo( t.getData() ) < 0 ) {
            t.setLeft( this.insert( key, value, t ) );
            if ( t.getLeft().getHeight() - t.getRight().getHeight() == 2 ){
                if ( data.compareTo( t.getLeft().getData() ) < 0 )
                    t = leftRotation( t );
                else
                    t = rightRotation( t );
            }
        } else if ( data.compareTo( t.getData() ) > 0 ){
            t.setRight( this.insert( key, value, t ) );
            if ( t.getRight().getHeight() - t.getLeft().getHeight() == 2 ){
                if ( data.compareTo( t.getRight().getData() ) < 0 )
                    t = leftRotation( t );
                else
                    t = rightRotation( t );
            }
        }
        t.setHeight( Math.max( t.getLeft().getHeight(), t.getRight().getHeight() ) + 1 );
        return t;
    }

    public Generic<?, ?> query( Object key ){
        Generic<?, ?> data = new Generic<>( key, null );
        AVLNode current = this.root;
        int cmp;
        while ( current != null ){
            cmp = data.compareTo( current.getData() );
            if ( cmp == 0 ){
                return current.getData();
            } else if ( cmp < 0 ){
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public Generic<?, ?> remove( Object key ){
        Generic<?, ?> dataKey = new Generic<>( key, null );
        AVLNode current = this.root;
        AVLNode father;
        int cmp;

        Generic<?, ?> data;
        Generic<?, ?> data2;

        while ( current != null ){
            cmp = dataKey.compareTo( current.getData() );
            if ( cmp < 0 ){
                current = current.getLeft();
            } else if ( cmp > 0 ) {
                current = current.getRight();
            } else {
                father = current.getFather();
                if ( current.getLeft() == null && current.getRight() == null ){
                    data = current.getData();
                    if ( current.getData().compareTo( father.getData() ) <= 0 ){
                        father.setLeft( null );
                    } else {
                        father.setRight( null );
                    }
                    return data;
                } else if ( current.getLeft() == null ) {
                    data = current.getData();
                    if ( current.getData().compareTo( father.getData() ) <= 0 ){
                        father.setLeft( current.getRight() );
                    } else {
                        father.setLeft( current.getRight() );
                    }
                    return data;
                } else if ( current.getRight() == null ) {
                    data = current.getData();
                    if ( current.getData().compareTo( father.getData() ) <= 0 ){
                        father.setLeft( current.getLeft() );
                    } else {
                        father.setLeft( current.getLeft() );
                    }
                    return data;
                } else {
                    data = current.getData();
                    current.setData( this.removeGreater( current.getLeft() ) );
                    return data;
                }
            }
        }
        return null;
    }

    public Generic<?, ?> removeGreater(  ){
        return this.removeGreater( this.root );
    }

    public Generic<?, ?> removeGreater( AVLNode t ){
        AVLNode current = t;
        AVLNode aux;
        Generic<?, ?> data;
        while ( current != null ){
            if ( current.getRight() != null ){
                current = current.getRight();
            } else {
                if ( current.getLeft() != null ){
                    data = current.getData();
                    aux = current.getLeft();
                    current.setData( aux.getData() );
                    current.setLeft( aux.getLeft() );
                } else {
                    data = current.getData();
                    if ( current.getFather() == null ){
                        this.root = null;
                    } else {
                        current.getFather()
                                .setRight( null );
                    }
                }
                return data;
            }
        }
        return null;
    }

    private AVLNode rightRotation( AVLNode node ){
        AVLNode leftNode = node.getLeft();
        node.setLeft( leftNode.getRight() );
        leftNode.setRight( node );
        node.setHeight(
                Math.max( node.getLeft().getHeight(),
                        node.getRight().getHeight() ) + 1
        );
        leftNode.setHeight(
                Math.max( leftNode.getLeft().getHeight(),
                        node.getHeight() ) + 1
        );
        return leftNode;
    }

    private AVLNode leftRotation( AVLNode node ){
        AVLNode rightNode = node.getRight();
        node.setRight( rightNode.getLeft() );
        rightNode.setLeft( node );
        node.setHeight(
                Math.max( node.getLeft().getHeight(),
                        node.getRight().getHeight() ) + 1
        );
        rightNode.setHeight(
                Math.max( rightNode.getRight().getHeight(),
                        node.getHeight() ) + 1
        );
        return rightNode;
    }

    private AVLNode doubleRightRotation( AVLNode node ){
        node.setLeft( this.leftRotation( node.getLeft() ) );
        return this.rightRotation( node );
    }

    private AVLNode doubleLeftRotation( AVLNode node ){
        node.setRight( this.rightRotation( node.getRight() ) );
        return this.rightRotation( node );
    }
}
