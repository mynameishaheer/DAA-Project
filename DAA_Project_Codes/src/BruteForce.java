//Source: https://github.com/jsedano/examples/blob/main/java-snippets/NQueensBruteForce.java

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

class NQueensBruteForce{

    static String toString(int[] arr){
        String r = "{ ";
        for(int i = 0; i < arr.length - 1; i++){
            r += arr[i] + " , ";
        }
        return  r + arr[arr.length - 1] + " }";
    }

    static int[] copyFromSet(LinkedHashSet<Integer> perm){
        return perm.stream()
            .mapToInt(
                Integer::intValue
            ).toArray();
    }

    static void removeLastElement(LinkedHashSet<Integer> perm){
        Iterator<Integer> i = perm.iterator();
        for(; i.hasNext(); i.next());
        i.remove();
    }

    static void generatePermutations(List <int[]>permList, LinkedHashSet<Integer> perm, int n){
        if(perm.size() == n) {
            permList.add(copyFromSet(perm));
            return;
        }
        for(int i = 0; i < n; i++){
            if(!perm.contains(i)) {
                perm.add(i);
                generatePermutations(permList, perm, n);
                removeLastElement(perm);
            }
        }
    }

    static boolean isSolution(int []perm){
        for(int i = 0; i < perm.length - 1; i++) {
            for(int j = i + 1; j < perm.length; j++){
                if(Math.abs(i - j) == Math.abs(perm[i] - perm[j])){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        final long startTime = System.currentTimeMillis();
        
        int n = 12;
        
        var permutations = new LinkedList<int[]>(); // permutations will be saved here
        
        generatePermutations(permutations, new LinkedHashSet<Integer>(), n);
        
    
        for(int []arr : permutations) {
            if(isSolution(arr)) {
                /* Commented out the print statements to get more accurate time results */
                // System.out.println(toString(arr)); // print valid solutions.
            }
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("Total Execution Time: " + (endTime - startTime));
    }

}