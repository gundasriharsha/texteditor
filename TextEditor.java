import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
//    Declaring properties of text editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
//    File menu items
    JMenuItem newFile, openFile, saveFile;
//    EDit menu items
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
//        Initialize frame
        frame=new JFrame();
//        Initialize menu bar
        menuBar=new JMenuBar();
//        Initialize text area
        textArea=new JTextArea();
//      Initialize menus
        file=new JMenu("File");
        edit=new JMenu("Edit");
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open file");
        saveFile=new JMenuItem("save file");
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

//        Add menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
//        Initialize edit menu items
        cut=new JMenuItem("cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("selectAll");
        close=new JMenuItem("close");
//        Adding action listeners to menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

//        Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

//        Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);


//        Set dimensions of frame
        frame.setTitle("Harsha's Text Editor");
        frame.setJMenuBar(menuBar);
//        Create a content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
//        Add text area to the panel
        panel.add(textArea,BorderLayout.CENTER);
//        Create scrollpane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//      add scroll panel
         panel.add(scrollPane);
//         Add panel to frame
        frame.add(panel);
        frame.setBounds(0,0,600,600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(true);
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
//            Perform cut operation
            textArea.cut();

        }
        if(actionEvent.getSource()==copy){
//            Perform copy operation
            textArea.copy();

        }
        if(actionEvent.getSource()==paste){
//            Perform paste operation
            textArea.paste();

        }
        if(actionEvent.getSource()==selectAll){
//            Perform selectAll operation
            textArea.selectAll();

        }
        if(actionEvent.getSource()==close){
//            Perform close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
//            Perform open file operation
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
//            if we have clicked open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
//                Getting selected file
                File file=fileChooser.getSelectedFile();
                String filePath= file.getPath();
                try{
                    FileReader fileReader=new FileReader(filePath);
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="", output="";
//                    Read contents line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
//                    Set the output string to text area
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
//            Initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
//            Get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
//            Check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
//                    Initialize file writer
                    FileWriter fileWriter=new FileWriter(file);
//                    Initialize buffered writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
//                    Write contents to text area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();

        }
        }
    public static void main(String[ ] args) {
        TextEditor textEditor=new TextEditor();
    }
}
