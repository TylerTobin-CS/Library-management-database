object MergeSort {
    @JvmStatic

    //Structure of class:
    //Similar to bubble sort, just the method has changed to a merge sort
    //Take 2D array as parameter
    //Turn the 2D array in to a String Array of the title of each book for each row
    //Merge sort that new array in alphabetical order
    //match the new sorted array back up to it's row in the 2D array from the csv, using a similar approach to 'SearchArray.kt'
    //Return sorted 2D string array.

    //Steps for 'mergeSort':
    //convert 2d array col[0] in to its own 1D array
    //Check if the given 1D array has more than one element
    //if more than one element:
    //  split array in half
    //call merge sort on each sub array created by splitting.

    //Steps for 'merge':
    //When the split sub arrays are returned, they are then merged by comparing the elements in each sub array
    //if they are in the correct order, add them to the final array in the correct order.



    fun main(records: Array<Array<String>>): Array<out Array<String?>?> {
        //The main method is responsible for converting the 2D array to a 1D array of the column from the 2D
        val firstColumn = arrayOfNulls<String>(records.size)
        //It takes the size of the 2D array
        for (i in records.indices) {
            //for each row it will perform the codee below
            firstColumn[i] = records[i][0]
            //the element in firstColumn is appended to by the element in records[x row][column 0 as it is the title]
        }
        //merge sort is then called on the array firstColumn
        //mergeSort(firstColumn) This line is used for testing
        return mergeSort(firstColumn)
    }

    fun mergeSort(arr: Array<String?>): Array<out Array<String?>?> {
        if (arr.size <= 1) {
            //check if the array is larger than one element
            return arrayOfNulls(0)
        }
        //if it is larger the array will be split in half and stored in each variable respectively
        val left = arrayOfNulls<String>(arr.size / 2)
        val right = arrayOfNulls<String>(arr.size - left.size)
        for (i in left.indices) {
            left[i] = arr[i]
        }
        for (i in right.indices) {
            right[i] = arr[i + left.size]
        }
        //the mergesort is now called recursively on each sub array left and right
        mergeSort(left)
        mergeSort(right)
        return merge(DisplayRecord.Main(), arr, left, right)
    }

    fun merge(
        records: Array<Array<String>>,
        arr: Array<String?>,
        left: Array<String?>,
        right: Array<String?>
    ): Array<Array<String?>> {
        //val arry = records
        var leftIndex = 0
        var rightIndex = 0
        var arrIndex = 0
        //the following code will run while the current element is less than the size of each sub array
        while (leftIndex < left.size && rightIndex < right.size) {
            // !! states explicitly that the variable left.index is not null
            //it will return a non-null value, if not, it will throw a null pointer exception
            if (left[leftIndex]!!.compareTo(right[rightIndex]!!) < 0) {
                arr[arrIndex] = left[leftIndex]
                leftIndex++
            } else {
                arr[arrIndex] = right[rightIndex]
                rightIndex++
            }
            arrIndex++
        }
        //The following while loops will merge the two arrays together when they are in the correct order
        while (leftIndex < left.size) {
            arr[arrIndex] = left[leftIndex]
            //Each index is incremented
            leftIndex++
            arrIndex++
        }

        while (rightIndex < right.size) {
            arr[arrIndex] = right[rightIndex]
            rightIndex++
            arrIndex++
            //Each index is incremented
        }

        //This block of code is responsible for converting the 1 dimensional array of sorted column names
        //to a 2D array by applying the same method as in the bubble sort,
        //it takes each element of the 1D array and searches the entire 2D array for it, once it is found,
        //the corresponding row is put in to a temporary list to then be added to a new 2D array this
        //new 2D array now contains the full list of rows of merge sorted values alphabetically by book title
        val recordsList: MutableList<String> = ArrayList()
        var recordCount = 0
        for (s in arr) {
            //System.out.println(s);
            for (i in records.indices) {
                if (s == records[i][0]) {
                    //System.out.println(records[i][0]+","+records[i][1]+","+records[i][2]+","+records[i][3]+","+records[i][4]);
                    recordsList.add(records[i][0] + "," + records[i][1] + "," + records[i][2] + "," + records[i][3] + "," + records[i][4])
                    recordCount++
                }
            }
        }
        var data: Array<String?>
        val arrayToReturn = Array(recordCount) { arrayOfNulls<String>(5) }
        for (i in 0 until recordCount) {
            data = recordsList[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (j in data.indices) {
                arrayToReturn[i][j] = data[j]
            }
        }
        return arrayToReturn
    }
}