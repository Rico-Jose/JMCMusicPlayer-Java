package jmcmusicplayer;

import java.io.File;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import javafx.scene.control.TextField;

public class Song /*implements Comparable<Song>*/ {
    
    String fullpath;

    public Song(String fullpath) {
        setFullpath(fullpath);
    }

    public String getFullpath() {
        return fullpath;
    }

    private void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }
    
    static void play(String song, TextField tfInfo) {
        try {
            File musicPath = new File(song);
            
            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                tfInfo.setText("Now Playing: " + song);
            } else {
                JOptionPane.showMessageDialog(null, "Can't find file");
                System.out.println("Can't find file");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static String[] sort(String[] songArray) {
        System.out.println("before: " + Arrays.toString(songArray));
        mergeSort(songArray);
        return songArray;
    }

    // <editor-fold defaultstate="collapsed" desc="Has sorting algorithm">
    // Merge Sort
    static void mergeSort(String[] a) {
        if (a.length >= 2) {
            String[] left = new String[a.length / 2];
            String[] right = new String[a.length-a.length / 2];

            for (int i = 0; i < left.length; i++) {
                left[i] = a[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = a[i + a.length / 2];
            }

            mergeSort(left);
            mergeSort(right);

            merge(a, left, right);
        }
    }

    static void merge(String[] result, String[] left, String[] right) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < result.length; i++) {
            if (i2 >= right.length || (i1 < left.length &&
                                 left[i1].compareToIgnoreCase(right[i2])<0)) {
                      result[i] = left[i1];
                      i1++;
            } else {
                      result[i] = right[i2];
                      i2++;
            }
        }
    }
    // </editor-fold>
    
//    @Override
//    public int compareTo(Song other) {
//        int compareInt = this.fullpath.compareTo(other.fullpath);
//        if (compareInt < 0) return -1;
//        if (compareInt > 0) return 1;
//        return 0;
//    }
}
