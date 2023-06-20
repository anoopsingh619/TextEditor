import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of the textEditor
    JFrame frame;
    JMenuBar menuBar;
    JTextArea textArea;
    JMenu file , edit;
    JMenuItem newFile,openFile,saveFile,cut,copy,paste,selectAll,close;

    TextEditor(){

        // Initialize the frame
        frame = new JFrame("Text Editor");

        // Initialize menuBar and textArea
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize MenuItems
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // adding action listeners
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding menuItems to the menus
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // adding menu to the menuBar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        // Initializing the panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // adding textArea to panel
        panel.add(textArea,BorderLayout.CENTER);
        // Initializing scrollPane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        // set dimensions of frame
        frame.setBounds(300,300,500,300);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("The Notorious");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if fileChooser selects open file
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate  = "" , output = "";
                    while((intermediate=bufferedReader.readLine())!=null){
                        output += intermediate + "\n";
                    }
                    // set the output string to text Area
                    textArea.setText(output);
                }
                // Exception case
                catch(IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("theNotorious");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+"txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();

    }
}