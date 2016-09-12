
/**
 * Write a description of CountInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)

*/

import edu.duke.*;
import java.io.*;

public class CountInversions {
    
    public int[] tempA;
    public int size;
    
    public double sortAndCount(int[] arrayA){
        //this.numbers = arrayA;
        size = arrayA.length;  //n
        this.tempA = new int[size];
        double count = mergesortAndCount(arrayA, tempA, 0, size - 1);
        /*
        for (int i =0; i < number; i++){
            System.out.println(i + " : " + arrayA[i]);
        }
        */
        //System.out.println(" InversionsFinal " + count);
        return count;
    }
    
    public double mergesortAndCount(int[] arr, int[] temp, int low, int high){
        int middle = 0;
        double invCount = 0;
        if (low < high){
            middle = (high+low)/2;
            invCount += mergesortAndCount(arr, temp, low, middle);
            invCount += mergesortAndCount(arr, temp, middle + 1 , high);
            invCount += mergeAndCount(arr, temp, low, middle+1, high);
        }
        return invCount;
    }
    
    public double mergeAndCount(int[] arr, int[] temp, int low, int middle, int high){
        int invCount = 0;
        
        for(int i = low; i <= high; i++){
            temp[i] = arr[i];
            //System.out.println("i : " + i + " tempA : " + tempA[i]);
        }
        
        
        int i = low;
        int j = middle;
        int k = low;
        
        while((i <= middle-1) && (j <= high)){
            if (arr[i] <= arr[j]){
                temp[k] = arr[i];
                //System.out.println("i : " + i);
                i++;
            }
            else{
                temp[k] = arr[j];
                //System.out.println("j : " + j);
                j++;
                
                invCount += (middle - i);
                //System.out.println("invCount : " + invCount);
            }
            //System.out.println("k : " + k);
            k++;
            
        }
        
        while (i <= middle-1){
            temp[k] = arr[i];
            k++;
            i++;
        }
        
        while (j <= high){
            temp[k] = arr[j];
            k++;
            j++;
        }
        
        for (i = low; i <= high; i++){
            arr[i] = temp[i];
        }
        
        //System.out.println("Inversions : " + invCount);
        return invCount;
    }
    
    public void testSortAndCount(){
        FileResource fr = new FileResource();
        StorageResource store = new StorageResource();
        for (String line : fr.lines()){
            store.add(line);
        }
        int len = store.size();
        int[] inputA = new int[len];
        int i = 0;
        for (String elem : store.data()){
            inputA[i] = Integer.parseInt(elem);
            //System.out.println("index: " + i + " elem: " + inputA[i]); 
            i++;
        }
        
        double inversions = sortAndCount(inputA);
        System.out.println("Inversions : " + inversions);
    }
}
