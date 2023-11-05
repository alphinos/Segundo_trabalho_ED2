package Estrutura;

class AVLNode<Key, Value> {
    private Pair< Key,Value> data;

    private AVLNode<Key, Value> father;
    private AVLNode<Key, Value> left;

    private AVLNode<Key, Value> right;

    private int height;

    public AVLNode( Pair<Key,Value> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public AVLNode( Key key, Value value ){
        this.data = new Pair<Key, Value>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public Pair<Key, Value> getData() {
        return this.data;
    }

    public AVLNode<Key, Value> getFather() {
        return this.father;
    }

    public AVLNode<Key, Value> getLeft() {
        return this.left;
    }

    public AVLNode<Key, Value> getRight() {
        return this.right;
    }

    public int getHeight(){
        return this.height;
    }

    public int getHeight( AVLNode<Key, Value> side ){
        if ( side == null )
            return 0;
        int heightLeft = this.getHeight( side.left );
        int heightRight = this.getHeight( side.right );
        if ( heightRight > heightLeft )
            return 1 + heightRight;
        else
            return 1 + heightLeft;
    }

    public void setData(Pair<Key, Value> data) {
        this.data = data;
    }

    public void setFather(AVLNode<Key, Value> father) {
        this.father = father;
    }

    public void setLeft(AVLNode<Key, Value> left) {
        this.left = left;
        left.setFather( this );
    }

    public void setRight(AVLNode<Key, Value> right) {
        this.right = right;
        left.setFather( this );
    }

    public void setHeight( int height ){
        this.height = height;
    }
}

public class AVLTree<Key, Value> {
    private AVLNode<Key, Value> root;

    public AVLNode<Key, Value> insert( Key key, Value value ){
        return this.insert( key, value, this.root );
    }
    public AVLNode<Key, Value> insert( Key key, Value value, AVLNode<Key, Value> t ){
        Pair<Key, Value> data = new Pair<>( key, value );
        if ( t == null ){
            t = new AVLNode<Key, Value>( data );
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

    public Value query( Key key ){
        Pair<Key, Value> data = new Pair<>( key, null );
        AVLNode<Key, Value> current = this.root;
        int cmp;
        while ( current != null ){
            cmp = data.compareTo( current.getData() );
            if ( cmp == 0 ){
                return current.getData().getValue();
            } else if ( cmp < 0 ){
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public Value remove( Key key ){
        Pair<?, ?> dataKey = new Pair<>( key, null );
        AVLNode<Key, Value> current = this.root;
        AVLNode<Key, Value> father;
        int cmp;

        Pair<Key, Value> data;

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
                    return data.getValue();
                } else if ( current.getLeft() == null ) {
                    data = current.getData();
                    if ( current.getData().compareTo( father.getData() ) <= 0 ){
                        father.setLeft( current.getRight() );
                    } else {
                        father.setLeft( current.getRight() );
                    }
                    return data.getValue();
                } else if ( current.getRight() == null ) {
                    data = current.getData();
                    if ( current.getData().compareTo( father.getData() ) <= 0 ){
                        father.setLeft( current.getLeft() );
                    } else {
                        father.setLeft( current.getLeft() );
                    }
                    return data.getValue();
                } else {
                    data = current.getData();
                    current.setData( this.removeGreater( current.getLeft() ) );
                    return data.getValue();
                }
            }
        }
        return null;
    }

    public Pair<Key, Value> removeGreater(  ){
        return this.removeGreater( this.root );
    }

    public Pair<Key, Value> removeGreater(AVLNode<Key, Value> t ){
        AVLNode<Key, Value> current = t;
        AVLNode<Key, Value> aux;
        Pair<Key, Value> data;
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

    private AVLNode<Key, Value> rightRotation( AVLNode<Key, Value> node ){
        AVLNode<Key, Value> leftNode = node.getLeft();
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

    private AVLNode<Key, Value> leftRotation( AVLNode<Key, Value> node ){
        AVLNode<Key, Value> rightNode = node.getRight();
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

    private AVLNode<Key, Value> doubleRightRotation( AVLNode<Key, Value> node ){
        node.setLeft( this.leftRotation( node.getLeft() ) );
        return this.rightRotation( node );
    }

    private AVLNode<Key, Value> doubleLeftRotation( AVLNode<Key, Value> node ){
        node.setRight( this.rightRotation( node.getRight() ) );
        return this.rightRotation( node );
    }
}
