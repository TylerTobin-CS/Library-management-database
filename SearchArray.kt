object SearchArray {
    @JvmStatic
    //Class Structure:
    //Take the 2D String array in the main method
    //search the entire matrix with nested for loop that will step through each and every element
    //if the element selected is equal to the search term provided by the user
    //it will add that entire row to a new 2D array by creating a temporary list to store the rows in
    //the 2D array is then returned to the 'Main' class


    fun main(records: Array<Array<String>>, searchTarget: String): Array<Array<String?>> {
        val n = records[0][0]
        val recordsList: MutableList<String> = ArrayList()
        var recordCount = 0
        //sets the variable required to store temporary lists and the counter

        //System.out.println(records[0][0]); this line is used for testing
        for (i in records.indices) {
            //the first for loop is going through every row
            for (j in records[i].indices) {
                //this nested for loop now goes through each element in that row
                //System.out.println(records[i][j]); this line is used for testing
                if (records[i][j] == searchTarget == true) {
                    //if the element in records is equal to the search provided by user the rest of the code is run
                    //System.out.println(records[i][0]+","+records[i][1]+","+records[i][2]+records[i][3]+","+
                    //records[i][4]); These lines are used for testing
                    recordsList.add(
                        records[i][0] + "," + records[i][1] + "," + records[i][2] + "," + records[i][3] + "," +
                                records[i][4]
                    ) // recordsList.add() will add the corresponding row to the temporary list
                    recordCount++
                }
            }
        }

        // the following code just simply converts the temporary list of searched terms in to a 2D array ready,
        //to be sent back to the 'Main' class and displayed in the JTable
        val arrayToReturn = Array(recordCount) { arrayOfNulls<String>(5) }
        var data: Array<String?>
        for (i in 0 until recordCount) {
            data = recordsList[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (j in data.indices) {
                arrayToReturn[i][j] = data[j]
            }
        }
        return arrayToReturn
    }
}