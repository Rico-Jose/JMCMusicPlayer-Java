package jmcmusicplayer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SongTest {
    
    public SongTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetFullpath() {
    }

    @Test
    public void testPlay() {
    }

    @Test
    public void testSort() {
        String[] unsortedSongArray = {
            "C:\\\\temp1\\\\Aubrey.wav",
            "C:\\\\temp1\\\\Everything I Own.wav",
            "C:\\\\temp1\\\\Diary.wav",
            "C:\\\\temp1\\\\Baby I'm A Want You.wav"
        };
        Song.sort(unsortedSongArray);
        String[] sortedSongArray = {
            "C:\\\\temp1\\\\Aubrey.wav",
            "C:\\\\temp1\\\\Baby I'm A Want You.wav",
            "C:\\\\temp1\\\\Diary.wav",
            "C:\\\\temp1\\\\Everything I Own.wav"
        };
        assertEquals(unsortedSongArray, sortedSongArray);
    }

    @Test
    public void testMergeSort() {
    }

    @Test
    public void testMerge() {
    }
    
}