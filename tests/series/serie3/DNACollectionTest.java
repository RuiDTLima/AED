package series.serie3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by rui_l on 23/12/2016.
 */
public class DNACollectionTest {
    @Test
    public void TestWithThreeFrag() throws Exception {
        DNACollection dna = new DNACollection();
        dna.add("CTTGA");
        dna.add("CAGT");
        dna.add("CTTGT");
        assertEquals(3,dna.prefixCount("C"));
    }

    @Test
    public void TestNoSuchFrag() throws Exception {
        DNACollection dna = new DNACollection();
        dna.add("CTTGA");
        dna.add("CAGTCA");
        dna.add("CTTGT");
        assertEquals(0,dna.prefixCount("G"));
    }

    @Test
    public void TestOnlyOneFrag() throws Exception {
        DNACollection dna = new DNACollection();
        dna.add("CTTGA");
        dna.add("CAGTCA");
        dna.add("CTTGT");
        dna.add("TTGACCA");
        dna.add("TTCAGGA");
        assertEquals(1,dna.prefixCount("TTG"));
    }

    @Test
    public void TestNoElements() throws Exception {
        DNACollection dna = new DNACollection();
        assertEquals(0,dna.prefixCount(""));
    }


    @Test
    public void TestInvalidPrefix() throws Exception {
        DNACollection dna = new DNACollection();
        dna.add("CTTGA");
        dna.add("CAGTCA");
        dna.add("CTTGT");
        dna.add("TTGACCA");
        dna.add("TTCAGGA");
        assertEquals(0,dna.prefixCount("TTW"));
    }
}
