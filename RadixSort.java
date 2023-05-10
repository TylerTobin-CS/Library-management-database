/*
import java.util.ArrayList;
import java.util.List;

//NEEDS TO BE CONVERTED TO SCALA
//NEEDS TO FOLLOW THE SAME METHOD OF CONVERTING TO
//1D ARRAY AS THE BUBBLE SORT DOES

public class RadixSort {

    public static String[][] main(String[][] records){
        //conversion to 1D array needed
        //String[] arr = {"e", "a", "c", "b"}
        //radixSort(arr)
    }

    //THIS IS THE RADIX SORT
    //Test 1D array
    //String[] arr = {"e", "a", "c", "b"}
    public static String[] radixSort(String[] arr) {
        //create a temporary empty array
        List<String>[] lists = new List[Character.MAX_VALUE + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (String s : arr) {

            for (int i = s.length() - 1; i >= 0; i--) {
                List<String> list = lists[s.charAt(i)];

                //append the current string to the end of the list
                list.add(s);
            }
        }



        //add the lists in the array of listsstart with most significant character
        //to form the sorted array
        String[] sorted = new String[arr.length];
        int index = 0;
        for (List<String> list : lists) {
            for (String s : list) {
                sorted[index++] = s;
            }
        }



        return sorted;
    }
}
*/