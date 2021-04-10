package jmcmusicplayer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import com.opencsv.CSVReader;
import java.awt.Desktop;
import java.io.*;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class MusicPlayerController implements Initializable {
        
    @FXML
    private TableView<Song> table;

    @FXML
    private TableColumn<Song, String> fullpath;

    @FXML
    private TextField TFInfo;

    @FXML
    private TextField TFSong;

    // <editor-fold defaultstate="collapsed" desc="Has dynamic data structures">
    // Binary Tree
    Tree SongTree = new Tree();
    // </editor-fold>
    
    ObservableList<Song> list;
    CSVReader reader = null;

    @FXML
    void handleAddSongsButtonAction(ActionEvent event) throws IOException {
        
        try {
            // <editor-fold defaultstate="collapsed" desc="Used 3rd-party library">
            // parsing CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader("C:\\temp1\\songs.csv"));
            // </editor-fold>
            String[] nextLine;
            boolean isHeader = true;
            
            // reads one line at a time
            while ((nextLine = reader.readNext()) != null) {
                if (!isHeader) {
                    System.out.println(nextLine[0]);
                    list.add(new Song(nextLine[0]));
                    SongTree.add(nextLine[0]);
                }
                isHeader = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleSaveButtonAction(ActionEvent event) {
        String filepath = "C:\\temp1\\songs copy.csv";
        File file = new File(filepath);
        
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            
        }
        
        try {
            FileWriter fw = new FileWriter(filepath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            try {
                CSVReader temp = new CSVReader(new FileReader("C:\\temp1\\songs.csv"));
                String[] nextLine;

                while ((nextLine = temp.readNext()) != null) {
                pw.println(nextLine[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            pw.flush();
            pw.close();
            
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not Saved");
        }
    }
    
    @FXML
    void handleSearchButtonAction(ActionEvent event) throws IOException {
        String localFullpath = SongTree.find(TFSong.getText());
        Song.play(localFullpath, TFInfo);
        
//        table.requestFocus();
//        table.getSelectionModel().select(2);
//        table.getFocusModel().focus(0);
    }

    @FXML
    void handleSortButtonAction(ActionEvent event) {        
        String[] songArray = new String[table.getItems().size()];
        int i = 0;

        for (Song o : table.getItems()) {
            songArray[i] = (String)fullpath.getCellData(o);
            i++;
        }

        Song.sort(songArray);
        System.out.println("afterr: " + Arrays.toString(songArray));
        
        list.clear();
        for (int j = 0; j < songArray.length; j++) {
            list.add(new Song(songArray[j]));
        }
        
        // <editor-fold defaultstate="collapsed" desc="Comments">
        /*
        List<Song> songList = new ArrayList<Song>();

        for (Song o : table.getItems()) {
            songList.add(o);
        }

        mergeSort(songList);
        for (var s : songList) {
            System.out.println(s.fullpath);
        }
        */
        // </editor-fold>
    }
    
    @FXML
    void handleAboutButtonAction(ActionEvent event) {
        String url = "index.html";
        File htmlFile = new File(url);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
            System.out.println("\nHelp File Loaded");
        } catch (IOException ex) {
            System.out.println("IOException occrus: " + ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        
        fullpath.setCellValueFactory(new PropertyValueFactory<Song,String>("fullpath"));
        
        table.setItems(list);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Comments">
        /*
        static void mergeSort(List<Song> a) {
            if (a.size() >= 2) {
                List<Song> left = new ArrayList<>();
                List<Song> right = new ArrayList<>();

                for (int i = 0; i < left.size(); i++) {
                    left.add(a.get(i));
                }
                for (int i = 0; i < right.size(); i++) {
                    right.add(a.get(i + a.size() / 2));
                }

                mergeSort(left);
                mergeSort(right);

                merge(a, left, right);
            }
        }

        static void merge(List<Song> result, List<Song> left, List<Song> right) {
            int i1 = 0;
            int i2 = 0;
            for (int i = 0; i < result.size(); i++) {
                if (i2 >= right.size() || (i1 < left.size() &&
                        left.get(i1).compareTo(right.get(i2)) < 0)) {
                    result.add(left.get(i1));
                    i1++;
                } else {
                     result.add(right.get(i2));
                         i2++;
                }
            }
        }
        */
    // </editor-fold>
}
