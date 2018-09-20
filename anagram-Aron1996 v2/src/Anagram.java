import java.util.Arrays;

public class Anagram {
	
    public static boolean anagram(String s1, String s2) {
        s1 = s1.replaceAll("[\\s]", "");
        s2 = s2.replaceAll("[\\s]", "");
        int s1Length = s1.length();
        int s2Length = s2.length();
        char[] word1 = new char[s1Length];
        char[] word2 = new char[s2Length];
        for(int i = 0; i < s1Length; i++){
            word1[i] = s1.charAt(i);
        }
        for(int j = 0; j < s2Length; j++){
            word2[j] = s2.charAt(j);
        }
        Arrays.sort(word1);
        Arrays.sort(word2);
        return Arrays.equals(word1, word2);
    }
}
