import java.io.FileNotFoundException
import java.util.*

object BubbleSortTest {
    private val x: Scanner? = null
    @JvmStatic
    @Throws(FileNotFoundException::class)

    //Structure of class:
    //Call GeneralSort.main() on the entire 2D array to get the array sorted by author
    //so that the bubble sort is ready to sort by book title
    //Take 2D array as parameter
    //Turn the 2D array in to a String Array of the title of each book for each row
    //Bubble sort that new array
    //match the new sorted array back up to it's row in the 2D array from the csv, using a similar approach to 'SearchArray.kt'
    //Return sorted 2D string array
    fun main(records: Array<Array<String>>): Array<Array<String?>> {
        val myList: MutableList<String> = ArrayList() //An empty Array is created

        // Add elements from the 1st[0] column (book title) of the 2D array to the temporary list "myList"
        for (i in records.indices) {
            myList.add(records[i][0])
        }
        //Another temporary String array is created
        val recordsList: MutableList<String> = ArrayList()
        //a record count is created to be able to know how big the list is so
        //when the new 2D array is generated the dimensions of the matrix can be specified
        var recordCount = 0

        //THIS IS THE BUBBLE SORT:
        for (i in 0 until myList.size - 1) {
            //loop through each element
            for (j in 0 until myList.size - 1 - i) {
                //Check if the elements provided are in order
                if (myList[j].compareTo(myList[j + 1]) > 0) {
                    //the two elements are compared using an if statement
                    //Swap the elements if they are out of order
                    val temp = myList[j]
                    myList[j] = myList[j + 1]
                    myList[j + 1] = temp
                }
            }
        }
        //For testing purposes the sorted list can be printed here:
        //System.out.println(myList);
        for (s in myList) {
            //this for loop will iterate over each element in the sorted 1D array
            //System.out.println(s);
            for (i in records.indices) {
                //the nested for loop will iterate over each element in the 2D array
                if (s == records[i][0]) {
                    //if the iteration in the bubblesort 1D array is equal to the iteration in the main 2D arrays column[0]
                    //it will add the corresponding row to another temporary list 'recordsList'
                    //System.out.println(records[i][0]+","+records[i][1]+","+records[i][2]+","+records[i][3]+","+records[i][4]);
                    recordsList.add(records[i][0] + "," + records[i][1] + "," + records[i][2] + "," + records[i][3] + "," + records[i][4])
                    recordCount++
                }
            }
        }
        var data: Array<String?>
        //arrayT0Return is the final 2D array of the lists of sorted rows, we already know it is 5 columns so that can be specified
        //however, the rows must be specified by the recordCount
        val arrayToReturn = Array(recordCount) { arrayOfNulls<String>(5) }
        for (i in 0 until recordCount) {
            //this for loop will take each record in recordsList and save it to a temporary list 'data'
            data = recordsList[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            //for each element in data it is appended to the 2D arrayToReturn
            for (j in data.indices) {
                arrayToReturn[i][j] = data[j]
            }
        }
        return arrayToReturn
        //The below code is used for testing
        //System.out.println(records[0][2]);

    }
}