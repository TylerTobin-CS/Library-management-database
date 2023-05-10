import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DisplayRecord {
    //Class Structure:
    //Main method returns 2D array of all items in the csv file
    //ReadFileInto2DArray takes the csv, reads through each element
    //appends to a temporary list called 'data'
    //takes the list 'data' and inserts each row in to the 2D array
    //This class is used many times throughout the project and is a good example of object-oriented design

    public static String[][] Main(){
        String file = "records.csv";

        String[][] data = ReadFileInto2DArray(file, 5);

        //Call ReadFileInto2DArray with the file name

        for (int i = 0; i < data.length; i++){
            //System.out.println(String.join(",", data[i]));
        }
        return data;
    }
    public static String[][] ReadFileInto2DArray(String filepath, int amountOfFields){
        List<String> recordsList = new ArrayList<String>();
        String delimiter = ",";
        String currentLine;
        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                recordsList.add(currentLine);
            }
            int recordCount = recordsList.size();
            String[][] arrayToReturn = new String[recordCount][amountOfFields];
            String[] data;

            for(int i = 0; i < recordCount; i++){
                data = recordsList.get(i).split(delimiter);
                for(int j = 0; j < data.length; j++){
                    arrayToReturn[i][j] = data[j];
                }
            }
            return arrayToReturn;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
