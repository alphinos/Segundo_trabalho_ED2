package sort;

import Estrutura.Generic;

public class Counting extends Sorter {

    @Override
    public void sort(Generic<?, ?>[] vector) {
        this.vector_size = vector.length;
        if ( !( vector[0].getValue() instanceof Integer ) ){
            System.out.println("O vetor deve ser de inteiros");
            return;
        }
        for ( Generic<?, ?> e : vector ){
            if ( (int) e.getValue() < 0 ){
                System.out.println("O vetor deve ser inteiramente positivo");
                return;
            }
        }
        counting( ( Generic<?, Integer>[]) vector );
    }

    @Override
    public void invertedSort(Generic<?, ?>[] vector) {
        this.vector_size = vector.length;
        if ( !( vector[0].getValue() instanceof Integer ) ){
            System.out.println("O vetor deve ser de inteiros");
            return;
        }
        for ( Generic<?, ?> e : vector ){
            if ( (int) e.getValue() < 0 ){
                System.out.println("O vetor deve ser inteiramente positivo");
                return;
            }
        }
        invertedCounting( ( Generic<?, Integer>[]) vector );
    }
    private void counting( Generic<?, Integer >[] vector){
        int n = vector.length;

        // The output character array that will have sorted
        // arr
        int[] output = new int[n];

        Generic< ?, Integer> bigger = vector[0];
        for ( Generic< ?, Integer> i : vector ){
            if ( i.compareTo( bigger ) > 0 ){
                bigger = i;
            }
        }

        // Create a count array to store count of individual
        // characters and initialize count array as 0
        int[] count = new int[bigger.getValue() + 1];
        for (int i = 0; i < bigger.getValue(); ++i){
            count[i] = 0;
        }

        // store count of each character
        for (Generic<?, Integer> integerGenerics : vector) {
            count[integerGenerics.getValue()]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this character in output array
        for (int i = 1; i <= bigger.getValue(); ++i){
            count[i] += count[i - 1];
        }

        // Build the output character array
        // To make it stable we are operating in reverse
        // order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[vector[i].getValue()] - 1] = vector[i].getValue();
            --count[vector[i].getValue()];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i){
            Generic<?, Integer> copy= new Generic<>( vector[i].getKey(), output[i] );
            vector[i] = copy;
        }
    }

    private void invertedCounting(Generic<?, Integer>[] vector){
        int n = vector.length;

        // The output character array that will have sorted
        // arr
        int[] output = new int[n];

        Generic< ?, Integer> bigger = vector[0];
        for ( Generic< ?, Integer> i : vector ){
            if ( bigger.compareTo( i ) > 0 ){
                bigger = i;
            }
        }

        // Create a count array to store count of individual
        // characters and initialize count array as 0
        int[] count = new int[bigger.getValue()];
        for (int i = 0; i < bigger.getValue(); ++i){
            count[i] = 0;
        }

        // store count of each character
        for (Generic<?, Integer> integerGenerics : vector) {
            ++count[integerGenerics.getValue()];
        }

        // Change count[i] so that count[i] now contains
        // actual position of this character in output array
        for (int i = bigger.getValue() - 2; i >= 0; i--){
            count[i] += count[i + 1];
        }

        // Build the output character array
        // To make it stable we are operating in reverse
        // order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[vector[i].getValue()] - 1] = vector[i].getValue();
            --count[vector[i].getValue()];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i){
            Generic<?, Integer> copy= new Generic<>( vector[i].getKey(), output[i] );
            vector[i] = copy;
        }
    }
}
