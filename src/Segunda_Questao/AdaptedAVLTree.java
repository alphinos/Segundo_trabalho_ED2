package Segunda_Questao;

import Estrutura.List;
import Estrutura.Pair;

class AdaptedAVLNode<Key, Value> {
    private List< Pair<Key, Value > > data;

    private AdaptedAVLNode<Key, Value> father;
    private AdaptedAVLNode<Key, Value> left;

    private AdaptedAVLNode<Key, Value> right;

    private int height;

    public AdaptedAVLNode( Key key, Value value ){
        this.data = new List<>();
        this.data.insert( new Pair<>( key, value ) );
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public void insertInData( Key key, Value value ){
        this.data.insert( new Pair<>( key, value ) );
    }

    public List< Pair<Key, Value > > getData() {
        return this.data;
    }

    public void setData( List< Pair<Key, Value > > data ){
        this.data = data;
    }

    public AdaptedAVLNode<Key, Value> getFather() {
        return this.father;
    }

    public AdaptedAVLNode<Key, Value> getLeft() {
        return this.left;
    }

    public AdaptedAVLNode<Key, Value> getRight() {
        return this.right;
    }

    public int getHeight(){
        return this.height;
    }

    public int getHeight( AdaptedAVLNode<Key, Value> side ){
        if ( side == null )
            return 0;
        return 1 + Math.max( side.getHeight( side.getLeft() ), side.getHeight( side.getRight() ) );
    }

    public void setFather(AdaptedAVLNode<Key, Value> father) {
        this.father = father;
    }

    public void setLeft(AdaptedAVLNode<Key, Value> left) {
        this.left = left;
        if ( left != null )
            left.setFather( this );
    }

    public void setRight(AdaptedAVLNode<Key, Value> right) {
        this.right = right;
        if ( right != null )
            right.setFather( this );
    }

    public void setHeight( int height ){
        this.height = height;
    }
}

public class AdaptedAVLTree<Key, Value> {
    private AdaptedAVLNode<Key, Value> root;

    public void setRoot( AdaptedAVLNode<Key, Value> node ){
        this.root = node;
    }

    public AdaptedAVLNode<Key, Value> insert(Key key, Value value ){
        return this.insert( key, value, this.root );
    }
    public AdaptedAVLNode<Key, Value> insert(Key key, Value value, AdaptedAVLNode<Key, Value> t ){
        Pair<Key, Value> data = new Pair<>( key, value );
        if ( t == null ){
            AdaptedAVLNode<Key, Value> aux = new AdaptedAVLNode<>( key, value );
            aux.setFather( t );
            t = aux;
        } else if ( data.compareTo( t.getData().getFirst() ) < 0 ) {
            t.setLeft( this.insert( key, value, t.getLeft() ) );
            if ( t.getHeight( t.getLeft() ) - t.getHeight( t.getRight() ) == 2 ){
                if ( data.compareTo( t.getLeft().getData().getFirst() ) < 0 )
                    t = rightRotation( t );
                else
                    t = doubleRightRotation( t );
            }
        } else if ( data.compareTo( t.getData().getFirst() ) > 0 ){
            t.setRight( this.insert( key, value, t.getRight() ) );
            if ( t.getHeight( t.getRight() ) - t.getHeight( t.getLeft() ) == 2 ){
                if ( data.compareTo( t.getRight().getData().getFirst() ) > 0 )
                    t = leftRotation( t );
                else
                    t = doubleLeftRotation( t );
            }
        } else {
            t.insertInData( key, value );
        }
        t.setHeight( Math.max( t.getHeight( t.getLeft() ), t.getHeight( t.getRight() ) ) + 1 );
        return t;
    }

    public List< Pair<Key, Value > > queryAll( Key key ){
        Pair<Key, Value> data = new Pair<>( key, null );
        AdaptedAVLNode<Key, Value> current = this.root;
        int cmp;
        while ( current != null ){
            cmp = data.compareTo( current.getData().getFirst() );
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

    public Value remove( Key key ){
        Pair<?, ?> dataKey = new Pair<>( key, null );
        AdaptedAVLNode<Key, Value> current = this.root;
        AdaptedAVLNode<Key, Value> father;
        int cmp;

        Pair<Key, Value> data;

        while ( current != null ){
            cmp = dataKey.compareTo( current.getData().getFirst() );
            if ( cmp < 0 ){
                current = current.getLeft();
            } else if ( cmp > 0 ) {
                current = current.getRight();
            } else {
                father = current.getFather();
                if ( current.getLeft() == null && current.getRight() == null ){
                    data = current.getData().getFirst();
                    if ( current.getData().getFirst().compareTo( father.getData().getFirst() ) <= 0 ){
                        father.setLeft( null );
                    } else {
                        father.setRight( null );
                    }
                    return data.getValue();
                } else if ( current.getLeft() == null ) {
                    data = current.getData().getFirst();
                    if ( current.getData().getFirst().compareTo( father.getData().getFirst() ) <= 0 ){
                        father.setLeft( current.getRight() );
                    } else {
                        father.setLeft( current.getRight() );
                    }
                    return data.getValue();
                } else if ( current.getRight() == null ) {
                    data = current.getData().getFirst();
                    if ( current.getData().getFirst().compareTo( father.getData().getFirst() ) <= 0 ){
                        father.setLeft( current.getLeft() );
                    } else {
                        father.setLeft( current.getLeft() );
                    }
                    return data.getValue();
                } else {
                    data = current.getData().getFirst();
                    current.setData( this.removeGreater( current.getLeft() ) );
                    return data.getValue();
                }
            }
        }
        return null;
    }

    public List< Pair<Key, Value > > removeGreater(  ){
        return this.removeGreater( this.root );
    }

    public List< Pair<Key, Value > > removeGreater( AdaptedAVLNode<Key, Value> t ){
        AdaptedAVLNode<Key, Value> current = t;
        AdaptedAVLNode<Key, Value> aux;
        List< Pair<Key, Value > > data;
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

    private AdaptedAVLNode<Key, Value> rightRotation(AdaptedAVLNode<Key, Value> node ){
        AdaptedAVLNode<Key, Value> leftNode = node.getLeft();
        node.setLeft( leftNode.getRight() );
        leftNode.setRight( node );
        leftNode.setFather( node.getFather() );
        node.setFather( leftNode );
        node.setHeight(
                Math.max( node.getHeight( node.getLeft() ),
                        node.getHeight( node.getRight() ) ) + 1
        );
        leftNode.setHeight(
                Math.max( leftNode.getHeight( leftNode.getLeft() ),
                        node.getHeight() ) + 1
        );
        return leftNode;
    }

    private AdaptedAVLNode<Key, Value> leftRotation(AdaptedAVLNode<Key, Value> node ){
        AdaptedAVLNode<Key, Value> rightNode = node.getRight();
        node.setRight( rightNode.getLeft() );
        rightNode.setLeft( node );
        rightNode.setFather( node.getFather() );
        node.setFather( rightNode );
        node.setHeight(
                Math.max( node.getHeight( node.getLeft() ),
                        node.getHeight( node.getRight() ) ) + 1
        );
        rightNode.setHeight(
                Math.max( rightNode.getHeight( rightNode.getRight() ),
                        node.getHeight() ) + 1
        );
        return rightNode;
    }

    private AdaptedAVLNode<Key, Value> doubleRightRotation(AdaptedAVLNode<Key, Value> node ){
        node.setLeft( this.leftRotation( node.getLeft() ) );
        return this.rightRotation( node );
    }

    private AdaptedAVLNode<Key, Value> doubleLeftRotation(AdaptedAVLNode<Key, Value> node ){
        node.setRight( this.rightRotation( node.getRight() ) );
        return this.leftRotation( node );
    }
}
