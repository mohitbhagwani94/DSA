import java.util.*;

public class SingerRangeCheck {
    static List<Character> PITCHS = Arrays.asList('C','D','E','F','G','A','B');
    static int PITCH_LEN = PITCHS.size();

    // Convert a note like "C4" or "C10" into an integer rank
    private static int noteToRank(String note) {
        int ans = 0 ;
        int pitch = PITCHS.indexOf(note.charAt(0));
        int octave = Integer.parseInt(note.substring(1));
       return octave * PITCH_LEN + pitch;
    }

    // Main function to check if the singer can sing the song
    public static boolean singable(List<String> song, String lowest, String highest) {
        int lowRank = noteToRank(lowest);
        int highRank  = noteToRank(highest);

        for(String s : song){
            int currSrank = noteToRank(s);
            if(currSrank < lowRank || currSrank > highRank){
                return false;
            }
        }
        return true;
    }

    // Test
    public static void main(String[] args) {
        List<String> song1 = Arrays.asList("F4", "B4", "C5");
        List<String> song2 = Arrays.asList("C3", "E3", "G3", "C4", "E4", "G4", "C5");
        List<String> song3 = Arrays.asList("B4", "F5", "B5");
        List<String> song4 = Arrays.asList("B4", "E4", "G4", "G4", "A4", "B4", "E4",
                "B4", "E4", "G4", "G4", "A4", "C5", "B4",
                "E5", "G4", "G4", "A4", "B4", "C5", "D5",
                "C5", "B4", "C5", "E5", "D5", "C5", "C5",
                "B4", "B4", "E5", "E4", "G4", "G4", "A4",
                "B4", "B4", "B4", "C5", "E5", "A5", "E5",
                "C5", "A4", "E5", "D5", "C5", "B4");
        List<String> song5 = Arrays.asList("F4");

        // Original test cases
        System.out.println(singable(song1, "F4", "C5")); // true
        System.out.println(singable(song1, "A4", "C5")); // false
        System.out.println(singable(song2, "B2", "C5")); // true
        System.out.println(singable(song2, "C3", "B4")); // false
        System.out.println(singable(song3, "B4", "B5")); // true
        System.out.println(singable(song3, "B4", "C5")); // false
        System.out.println(singable(song4, "D4", "A5")); // true
        System.out.println(singable(song4, "D4", "G5")); // false
        System.out.println(singable(song4, "D4", "C6")); // true
        System.out.println(singable(song4, "F4", "C6")); // false
        System.out.println(singable(song5, "D4", "E4")); // false

        // Extended test: multi-digit octave
        List<String> bigSong = Arrays.asList("C10", "E10", "G10", "B10");
        System.out.println(singable(bigSong, "C9", "B10")); // true
        System.out.println(singable(bigSong, "D10", "C11")); // false (C10 is below D10)
    }
}
