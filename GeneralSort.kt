import java.util.*

object GeneralSort {
    //Structure:
    //This file is the supporting class for the bubble sort
    //it provides a general sort of the rows in the 2D array by author alphabettically
    //this allows the bubble sort to work properly
    //Use arrays.sort (Tim sort) to sort the data in the author column
    //add the sorted array(row) to the 2D array
    fun main(): Array<Array<String?>>? {
        val file = "records.csv"
        //load correct file
        //run the function sortAuthor using the 2D array from DisplayRecord
        val data6 = sortAuthor(DisplayRecord.Main())

        for (i in data6!!.indices) {
            //System.out.println(String.join(",", data6[i]));
            //this for loop is used for testing
        }
        return data6
    }
    fun sortAuthor(data: Array<Array<String>>): Array<Array<String?>>? {
        //val startTime = System.currentTimeMillis() used for testing
        //lambda function used to  sort the data using Arrays.sort
        //The lambda function compares the first entry to the next by the [1] column which is 'author'
        Arrays.sort(data) { entry1, entry2 ->
            val time1 = entry1[1]
            val time2 = entry2[1]
            time1.compareTo(time2)
        }
        //The following code is similar to other parts of the project where
        //the sorted array is added to a temporary list
        val recordsList: MutableList<String> = ArrayList()
        for (s in data) {
            //for each record in the sorted list, add the entire row to recordsList

            //System.out.println(s[0]+","+s[1]+","+s[2]+","+s[3]+","+s[4]); This line is used for testing
            recordsList.add(s[0] + "," + s[1] + "," + s[2] + "," + s[3] + "," + s[4])
        }
        //return the sorted 2D array by converting it from recordsList to arrayToReturn
        return try {
            val recordCount = recordsList.size
            val arrayToReturn =
                Array(recordCount) { arrayOfNulls<String>(5) }
            //System.out.println(arrayToReturn);  This line is used for testing
            var data4: Array<String?>
            for (i in 0 until recordCount) {
                data4 = recordsList[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (j in data4.indices) {
                    arrayToReturn[i][j] = data4[j]
                }
            }
            arrayToReturn
        } catch (e: Exception) {
            println(e)
            null
        }
    }
}