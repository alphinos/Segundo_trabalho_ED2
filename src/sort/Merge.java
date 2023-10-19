package sort;

import Estrutura.Generic;

public class Merge extends Sorter {

    public Merge(){
        super();
        this.name = "Merge Sort";
    }

    @Override
    public void sort(Generic<?, ?>[] vector) {
        this.vector_size = vector.length;

        Generic<?, ?>[] temp = new Generic<?, ?>[ vector.length ]; this.movements++;
        this.MergeMain( vector, temp, 0, vector.length - 1 );
    }

    @Override
    public void invertedSort(Generic<?, ?>[] vector) {
        this.vector_size = vector.length;

        Generic<?, ?>[] temp = new Generic<?, ?>[ vector.length ]; this.movements++;
        this.InvertedMergeMain( vector, temp, 0, vector.length - 1 );
    }

    public void MergeMain( Generic<?, ?>[] vector, Generic<?, ?>[] temp, int left, int right ){
        int middle;

        if ( left < right ){
            middle = ( left + right ) / 2; this.movements++;

            this.MergeMain( vector, temp, left, middle);
            this.MergeMain( vector, temp, middle + 1, right);
            this.merge( vector, temp, left, middle + 1, right );
        }
    }

    public void merge( Generic<?, ?>[] vector, Generic<?, ?>[] temp, int leftPosition, int rightPosition, int rightEnd ){
        int leftEnd = rightPosition - 1; this.movements++;
        int tempPosition = leftPosition; this.movements++;
        int numberElements = rightEnd - leftPosition + 1; this.movements++;

        while ( leftPosition <= leftEnd && rightPosition <= rightEnd ){ this.comparisons++; this.comparisons++;
            if ( vector[ leftPosition ].compareTo( vector[ rightPosition ] ) <= 0 ){
                temp[ tempPosition++ ] = vector[ leftPosition++ ]; this.movements++;
            } else {
                temp[ tempPosition++ ] = vector[ rightPosition++ ]; this.movements++;
            }
        }

        while ( leftPosition <= leftEnd ){ this.comparisons++;
            temp[ tempPosition++ ] = vector[ leftPosition++ ]; this.movements++;
        }

        while ( rightPosition <= rightEnd ){ this.comparisons++;
            temp[ tempPosition++ ] = vector[ rightPosition++ ]; this.movements++;
        }

        for ( int i = 0; i < numberElements; i++, rightEnd-- ){ this.movements++; this.comparisons++; this.movements++; this.movements++;
            vector[ rightEnd ] = temp[ rightEnd ]; this.movements++;
        }
    }

    public void InvertedMergeMain( Generic<?, ?>[] vector, Generic<?, ?>[] temp, int left, int right ){
        int middle;

        if ( left < right ){
            middle = ( left + right ) / 2; this.movements++;

            this.MergeMain( vector, temp, left, middle);
            this.MergeMain( vector, temp, middle + 1, right);
            this.InvertedMerge( vector, temp, left, middle + 1, right );
        }
    }

    public void InvertedMerge( Generic<?, ?>[] vector, Generic<?, ?>[] temp, int leftPosition, int rightPosition, int rightEnd ){
        int leftEnd = rightPosition - 1; this.movements++;
        int tempPosition = leftPosition; this.movements++;
        int numberElements = rightEnd - leftPosition + 1; this.movements++;

        while ( leftPosition <= leftEnd && rightPosition <= rightEnd ){ this.comparisons++; this.comparisons++;
            if ( vector[ leftPosition ].compareTo( vector[ rightPosition ] ) > 0 ){
                temp[ tempPosition++ ] = vector[ leftPosition++ ]; this.movements++;
            } else {
                temp[ tempPosition++ ] = vector[ rightPosition++ ]; this.movements++;
            }
        }

        while ( leftPosition <= leftEnd ){ this.comparisons++;
            temp[ tempPosition++ ] = vector[ leftPosition++ ]; this.movements++;
        }

        while ( rightPosition <= rightEnd ){ this.comparisons++;
            temp[ tempPosition++ ] = vector[ rightPosition++ ]; this.movements++;
        }

        for ( int i = 0; i < numberElements; i++, rightEnd-- ){ this.movements++; this.comparisons++; this.movements++; this.movements++;
            vector[ rightEnd ] = temp[ rightEnd ]; this.movements++;
        }
    }
}
