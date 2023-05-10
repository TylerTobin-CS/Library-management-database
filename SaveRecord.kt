import java.io.*

object SaveRecord {

    //Class Structure:
    //Take the user entry as the method (Not function as it doesn't return) parameters
    //Specify the file to write to
    //Use Java FileWriter to append to the file, will throw exception if file doesn't exist
    //Use printWriter to print a row of each of the parsed elements to the csv file
    //Use try and catch to close the writers and readers, if there is an issue an exception will be thrown

    @JvmStatic
    fun main(Mtitle: String, Mauthor: String, Myear: String, Mpublisher: String, Msubject: String) {
        //method parameters from the entry of each JTextField
        val file = File("records.csv")
        //load CSV file
        var fr: FileWriter? = null
        fr = try {
            FileWriter(file, true)
            //if file exists open it to append
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        val br = BufferedWriter(fr)
        val pr = PrintWriter(br)
        //Write row in to csv file
        pr.println("$Mtitle,$Mauthor,$Myear,$Mpublisher,$Msubject")
        pr.close()
        //close all readers and writers
        try {
            br.close()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        try {
            fr!!.close()
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}