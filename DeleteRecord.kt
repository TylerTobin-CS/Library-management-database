import java.io.*
object DeleteRecord {
    //Structure
    //Open CSV file
    //Create a temporary CSV file to store the updated 2D array without the deleted items
    //convert the temporary array back to records.csv by renaming it
    @JvmStatic
    fun deleteRow(filepath: String?, indexTerm: Int) {
        val tempFile = "temp.csv"
        //create temporary file to hold updated 2D array
        val oldFile = File(filepath)
        val newFile = File(tempFile)
        var line = 0
        //create a counter for the current line
        var currentLine: String?
        try {
            //open the temporary file to append to
            val fw = FileWriter(tempFile, true)
            val bw = BufferedWriter(fw)
            val pw = PrintWriter(bw)
            val fr = FileReader(filepath)
            val br = BufferedReader(fr)
            while (br.readLine().also { currentLine = it } != null) {
                //increment the line counter
                line++
                if (indexTerm != line) {
                    //if the index term does not equal the line for deletion
                    //add that line to the temporary file
                    pw.println(currentLine)
                }
            }
            pw.flush()
            pw.close()
            fr.close()
            br.close()
            bw.close()
            fw.close()
            //close all readers and writers
            //delete the original records file
            oldFile.delete()
            val dump = File(filepath)
            //dump is the temporary file that is to be renamed as records.csv
            newFile.renameTo(dump)
        } catch (e: Exception) {
            //if there are any errors the exception will be displayed to the console
            println(e)
        }
    }
}