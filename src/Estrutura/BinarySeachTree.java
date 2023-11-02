package Estrutura;

class tNode{
    private Pair<?,?> data;

    private tNode father;
    private tNode left;

    private tNode right;

    public tNode( Pair<?,?> data ){
        this.data = data;
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public tNode( Object key, Object value ){
        this.data = new Pair<>( key, value );
        this.father = null;
        this.left = null;
        this.right = null;
    }

    public Pair<?, ?> getData() {
        return this.data;
    }

    public tNode getFather() {
        return this.father;
    }

    public tNode getLeft() {
        return this.left;
    }

    public tNode getRight() {
        return this.right;
    }

    public void setData(Pair<?, ?> data) {
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
    private tNode root;

    public BinarySeachTree( Object key, Object value ){
        this.root = new tNode( key, value );
    }

    public BinarySeachTree( Pair<?, ?> data ){
        this.root = new tNode( data );
    }

    public tNode getRoot() {
        return root;
    }

    public void setRoot(tNode root) {
        this.root = root;
    }

    public void insert( Object key, Object value ){
        Pair<?, ?> data = new Pair<>( key, value );
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

    public Pair<?, ?> query(Object key ){
        Pair<?, ?> data = new Pair<>( key, null );
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

    public Pair<?, ?> remove(Object key ){
        Pair<?, ?> dataKey = new Pair<>( key, null );
        tNode current = this.root;
        tNode father;
        int cmp;

        Pair<?, ?> data;

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

    public Pair<?, ?> removeGreater(  ){
        return this.removeGreater( this.root );
    }

    public Pair<?, ?> removeGreater(tNode t ){
        tNode current = t;
        tNode aux;
        Pair<?, ?> data;
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
