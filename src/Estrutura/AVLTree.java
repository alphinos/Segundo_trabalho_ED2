package Estrutura;

class AVLNode<Key> {
    private Pair< Key,?> data;

    private AVLNode<Key> father;
    private AVLNode<Key> left;

    private AVLNode<Key> right;

    private int height;

    public AVLNode( Pair<Key,?> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public AVLNode( Key key, Object value ){
        this.data = new Pair<>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public Pair<Key, ?> getData() {
        return this.data;
    }

    public AVLNode<Key> getFather() {
        return this.father;
    }

    public AVLNode<Key> getLeft() {
        return this.left;
    }

    public AVLNode<Key> getRight() {
        return this.right;
    }

    public int getHeight(){
        return this.height;
    }

    public int getHeight( AVLNode<Key> side ){
        if ( side == null )
            return 0;
        int heightLeft = this.getHeight( side.left );
        int heightRight = this.getHeight( side.right );
        if ( heightRight > heightLeft )
            return 1 + heightRight;
        else
            return 1 + heightLeft;
    }

    public void setData(Pair<Key, ?> data) {
        this.data = data;
    }

    public void setFather(AVLNode<Key> father) {
        this.father = father;
    }

    public void setLeft(AVLNode<Key> left) {
        this.left = left;
        left.setFather( this );
    }

    public void setRight(AVLNode<Key> right) {
        this.right = right;
        left.setFather( this );
    }

    public void setHeight( int height ){
        this.height = height;
    }
}

public class AVLTree<Key> {
    private AVLNode<Key> root;

    public AVLNode<Key> insert( Key key, Object value ){
        return this.insert( key, value, this.root );
    }
    public AVLNode<Key> insert( Key key, Object value, AVLNode<Key> t ){
        Pair<Key, ?> data = new Pair<>( key, value );
        if ( t == null ){
            t = new AVLNode<Key>( data );
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
        t.setHeight( Math.max( t.getHeight( t.getLeft() ), t.getHeight( t.getRight() ) ) + 1 );
        return t;
    }

    public Pair<Key, ?> query( Key key ){
        Pair<Key, ?> data = new Pair<>( key, null );
        AVLNode<Key> current = this.root;
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

    public Pair<Key, ?> remove( Key key ){
        Pair<?, ?> dataKey = new Pair<>( key, null );
        AVLNode<Key> current = this.root;
        AVLNode<Key> father;
        int cmp;

        Pair<Key, ?> data;

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

    public Pair<Key, ?> removeGreater(  ){
        return this.removeGreater( this.root );
    }

    public Pair<Key, ?> removeGreater(AVLNode<Key> t ){
        AVLNode<Key> current = t;
        AVLNode<Key> aux;
        Pair<Key, ?> data;
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

    private AVLNode<Key> rightRotation( AVLNode<Key> node ){
        AVLNode<Key> leftNode = node.getLeft();
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

    private AVLNode<Key> leftRotation( AVLNode<Key> node ){
        AVLNode<Key> rightNode = node.getRight();
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

    private AVLNode<Key> doubleRightRotation( AVLNode<Key> node ){
        node.setLeft( this.leftRotation( node.getLeft() ) );
        return this.rightRotation( node );
    }

    private AVLNode<Key> doubleLeftRotation( AVLNode<Key> node ){
        node.setRight( this.rightRotation( node.getRight() ) );
        return this.rightRotation( node );
    }
}
