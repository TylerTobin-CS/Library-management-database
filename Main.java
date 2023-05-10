import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/*
JVM COURSEWORK - GROUP 4
TYLER TOBIN - 001105522
JOHN E FARMER - 001110016
NOAM HARTMANN - 001131055

LIBRARY MANAGEMENT SYSTEM
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //Setting the properties of the initial frame
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(720,300);
        frame.setTitle("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        //Grid layout was chosen as it is simple to work with and allows a good
        //structure for placing elements such as buttons and tables
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6, 2));
        panel2.setBackground(Color.GRAY);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 3));
        panel3.setBackground(Color.GRAY);
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new GridLayout(2, 1));
        panelSearch.setBackground(Color.GRAY);
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(2, 3));
        panelButtons.setBackground(Color.GRAY);

        //this String Array contains the header for the table each time it is called as a table model
        String[] columnNames = {"Ttile",
                "Author(s)",
                "Year",
                "Publisher",
                "Subject"};

        //Here is where the new model is created to populate the JTable with the 2D String array that is
        //generated in the class DisplayRecord, the main method returns this array
        DefaultTableModel model = new DefaultTableModel(DisplayRecord.Main(), columnNames);
        JTable table1 = new JTable( model );

        //The table is inserted in to a scrollbar container otherwise the user wouldn't be able to
        //scroll if the csv contains more data than the size of the jtable.
        JScrollPane tableContainer = new JScrollPane(table1);
        JLabel label1 = new JLabel("Title");
        JTextField titleEntry = new JTextField();
        JTextField authorEntry = new JTextField();
        JTextField yearEntry = new JTextField();
        JTextField publisherEntry = new JTextField();
        JTextField subjectEntry = new JTextField();

        //------------------------------------------------------------------------------------------
        //The following block of code simply sets new labels on the left hand panel and sets the
        //entry box next to the corresponding label, the font and colour is also set
        JLabel titleLabel = new JLabel("  Title:");
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel2.add(titleLabel);
        panel2.add(titleEntry);

        JLabel authorLabel = new JLabel("  Author(s):");
        authorLabel.setForeground(Color.ORANGE);
        authorLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel2.add(authorLabel);
        panel2.add(authorEntry);

        JLabel yearLabel = new JLabel("  Year:");
        yearLabel.setForeground(Color.ORANGE);
        yearLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel2.add(yearLabel);
        panel2.add(yearEntry);

        JLabel publisherLabel = new JLabel("  Publisher:");
        publisherLabel.setForeground(Color.ORANGE);
        publisherLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel2.add(publisherLabel);
        panel2.add(publisherEntry);

        JLabel subjectLabel = new JLabel("  Subject:");
        subjectLabel.setForeground(Color.ORANGE);
        subjectLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel2.add(subjectLabel);
        panel2.add(subjectEntry);

        //------------------------------------------------------------------------------------------

        panel.add(panel2);
        panel2.add(panel3);
        panel3.add(panelButtons);

        JButton viewAllButton = new JButton("View All");
        panelButtons.add(viewAllButton);

        //The code below is the listerner for when the button is pressed, for example if the user presses the 'add'
        //button, then the code inside will be executed. The code in the action event will run a condition to check whether
        //any field has been left empty, if it has then a message will appear saying not all fields are complete however,
        //if the condition is passed, the Kotlin class 'SaveRecord' will be run
        //Continue to 'SaveRecord.kt' file
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //as the actions performed by the buttons are all in an object-oriented design they
                //can be called wherever needed in the code for example, if a test of a search feature is needed
                // in another part of the code it can be called by calling intanciating the method in the class

                if (titleEntry.getText().isEmpty() == true || authorEntry.getText().isEmpty() == true || yearEntry.getText().isEmpty() == true || publisherEntry.getText().isEmpty() == true || subjectEntry.getText().isEmpty() == true){
                    JOptionPane.showMessageDialog(frame, "Some Fields are empty!");
                }else {
                    SaveRecord.main(titleEntry.getText(), authorEntry.getText(), yearEntry.getText(),
                            publisherEntry.getText(), subjectEntry.getText());
                    DefaultTableModel modelNew = new DefaultTableModel(DisplayRecord.Main(), columnNames);
                    table1.setModel(modelNew);
                    modelNew.fireTableDataChanged();
                    titleEntry.setText("");
                    authorEntry.setText("");
                    yearEntry.setText("");
                    publisherEntry.setText("");
                    subjectEntry.setText("");
                }
            }
        });

        //Similar to the previous action listener, this will work in the same way however, it
        //will run the Kotlin class 'DeleteRecord' and then update the table model to reflect the changes
        //Continue to 'DeleteRecord.kt' file
        JButton deleteButton = new JButton("Delete");
        panelButtons.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow() + 1;
                System.out.println(row);
                DeleteRecord.deleteRow("records.csv", row);
                DefaultTableModel modelNew = new DefaultTableModel(DisplayRecord.Main(), columnNames);
                table1.setModel(modelNew);
                modelNew.fireTableDataChanged();
            }
        });

        panel2.add(panelSearch);
        JTextField searchEntry = new JTextField();
        panelSearch.add(searchEntry);
        JButton searchButton = new JButton("Search");
        panelSearch.add(searchButton);

        //Similar to the previous action listener, this will work in the same way however, it
        //will run the Kotlin class 'SearchArray' and then update the table model to reflect the changes
        //Continue to 'SearchArray.kt' file
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelNew = new DefaultTableModel(SearchArray.main(DisplayRecord.ReadFileInto2DArray("records.csv",5), searchEntry.getText()), columnNames);
                table1.setModel(modelNew);
                modelNew.fireTableDataChanged();
            }
        });

        //Similar to the previous action listener, this will work in the same way however, it
        //will run the Kotlin class 'DisplayRecord' and then update the table model to reflect the changes
        //Continue to 'DisplayRecord.kt' file
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel modelNew = new DefaultTableModel(DisplayRecord.Main(), columnNames);
                table1.setModel(modelNew);
                modelNew.fireTableDataChanged();

            }
        });

        panelButtons.add(addButton);

        JButton bubbleSortButton = new JButton("Bubble Sort");
        panelButtons.add(bubbleSortButton);

        //Similar to the previous action listener, this will work in the same way however, it
        //will run the Kotlin class 'BubbleSortTest' and then update the table model to reflect the changes
        //Continue to 'BubbleSortTest.kt' file
        //The file was named test as it wasn't functioning properly at first, so it needed to be tested in a
        //separate file, however this test file became the one that works
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //here is the first example of how we timed the different sorting methods
                //the first varible is set to take the current time
                //the method to bubble sort is then run
                long startBubble = System.currentTimeMillis();

                DefaultTableModel modelNew = null;
                try {
                    modelNew = new DefaultTableModel(BubbleSortTest.main(DisplayRecord.ReadFileInto2DArray("records.csv",5)), columnNames);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                table1.setModel(modelNew);
                modelNew.fireTableDataChanged();

                //after the sort has been run and the table updated, the time is taken again
                //it is then calculated by subtracting the start time and outputted to the screen
                long endBubble = System.currentTimeMillis();
                long duration = endBubble - startBubble;
                System.out.println("The Bubble sort took " + duration + " milliseconds");
                JOptionPane.showMessageDialog(panel, "The Bubble sort took " + duration + " milliseconds");
            }
        });

        JButton mergeSortButton = new JButton("Merge Sort");
        panelButtons.add(mergeSortButton);

        //All the same processes for running the sort and timing are the same as the bubble sort, however
        //the timings will be different between the two sorting algorithms
        mergeSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long startMerge = System.currentTimeMillis();
                DefaultTableModel modelNew = new DefaultTableModel(MergeSort.main(DisplayRecord.ReadFileInto2DArray("records.csv",5)), columnNames);
                table1.setModel(modelNew);
                modelNew.fireTableDataChanged();
                long endMerge = System.currentTimeMillis();
                long duration = endMerge - startMerge;
                System.out.println("The Merge sort took " + duration + " milliseconds");
                JOptionPane.showMessageDialog(panel, "The Merge sort took " + duration + " milliseconds");
            }
        });

        JButton radixSortButton = new JButton("Radix Sort");
        panelButtons.add(radixSortButton);
        panel.add(tableContainer);
        frame.getContentPane().add(panel);
        frame.pack();







    }
}
