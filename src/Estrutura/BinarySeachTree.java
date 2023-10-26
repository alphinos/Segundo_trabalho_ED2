package Estrutura;

class tNode{
    private Generic<?,?> data;

    private tNode father;
    private tNode left;

    private tNode right;

    public tNode( Generic<?,?> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public tNode( Object key, Object value ){
        this.data = new Generic<>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public Generic<?, ?> getData() {
        return data;
    }

    public tNode getFather() {
        return father;
    }

    public tNode getLeft() {
        return left;
    }

    public tNode getRight() {
        return right;
    }

    public void setData(Generic<?, ?> data) {
        this.data = data;
    }

    public void setFather(tNode father) {
        this.father = father;
    }

    public void setLeft(tNode left) {
        this.left = left;
    }

    public void setRight(tNode right) {
        this.right = right;
    }
}

public class BinarySeachTree {
    tNode root;

    public BinarySeachTree( Object key, Object value ){
        this.root = new tNode( key, value );
    }

    public BinarySeachTree( Generic<?, ?> data ){
        this.root = new tNode( data );
    }

    public tNode getRoot() {
        return root;
    }

    public void setRoot(tNode root) {
        this.root = root;
    }

    public void insert( Object key, Object value ){
        Generic<?, ?> data = new Generic<>( key, value );
        tNode current = this.root;
        while ( current != null ){
            if ( data.compareTo( current.getData() ) <= 0 ){
                if ( current.getLeft() == null )
                    break;
                current = current.getLeft();
            } else {
                if ( current.getRight() == null )
                    break;
                current = current.getRight();
            }
        }

        tNode newnode = new tNode( data );

        if ( current == null ) {
            this.root = newnode;
        } else {
            if ( data.compareTo( current.getData() ) <= 0 )
                current.setLeft( newnode );
            else
                current.setRight( newnode );
            newnode.setFather( current );
        }
    }

    public Generic<?, ?> query( Object key ){
        Generic<?, ?> data = new Generic<>( key, null );
        tNode current = this.root;
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
        tNode current = this.root;
        tNode father;
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

    public Generic<?, ?> removeGreater( tNode t ){
        tNode current = t;
        tNode aux;
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
}
