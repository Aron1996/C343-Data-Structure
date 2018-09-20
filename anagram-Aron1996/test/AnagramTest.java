import static org.junit.Assert.*;

public class AnagramTest {
    @org.junit.Test
    public void anagram() throws Exception {
        assertTrue(Anagram.anagram("mary", "army"));
        assertTrue(Anagram.anagram("doctor who", "torchwood"));
        assertTrue(Anagram.anagram("steel", "sleet"));
        assertTrue(Anagram.anagram("frost", "forst"));
        assertFalse(Anagram.anagram("doctor who", "doctor"));
        assertFalse(Anagram.anagram("mary", "arm"));
        assertFalse(Anagram.anagram("doctr who", "torchwood"));
    }

}
