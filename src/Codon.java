/**
 * The Codons used in genomics, containing all 22 amino acid abbreviations,
 * and base pair conversions. Stop codons are included
 * This enum was created using
 * https://uiowa.instructure.com/courses/66104/pages/s9-multithreadingdna?module_item_id=1514980
 *
 * @author Andrew Sommer
 * @version 1.0.0 06 February 2018
 */
public enum Codon {
    UUU("Phe", 'F'), UCU("Ser", 'S'), UAU("Tyr", 'Y'), UGU("Cys", 'C'),
    UUC("Phe", 'F'), UCC("Ser", 'S'), UAC("Tyr", 'Y'), UGC("Cys", 'C'),
    UUA("Leu", 'L'), UCA("Ser", 'S'), UAA("Stop", '*'), UGA("Stop", '*'),
    UUG("Leu", 'L'), UCG("Ser", 'S'), UAG("Stop", '*'), UGG("Trp", 'W'),

    CUU("Leu", 'L'), CCU("Pro", 'P'), CAU("His", 'H'), CGU("Arg", 'R'),
    CUC("Leu", 'L'), CCC("Pro", 'P'), CAC("His", 'H'), CGC("Arg", 'R'),
    CUA("Leu", 'L'), CCA("Pro", 'P'), CAA("Gln", 'Q'), CGA("Arg", 'R'),
    CUG("Leu", 'L'), CCG("Pro", 'P'), CAG("Gln", 'Q'), CGG("Arg", 'R'),

    AUU("Ile", 'I'), ACU("Thr", 'T'), AAU("Asn", 'N'), AGU("Ser", 'S'),
    AUC("Ile", 'I'), ACC("Thr", 'T'), AAC("Asn", 'N'), AGC("Ser", 'S'),
    AUA("Ile", 'I'), ACA("Thr", 'T'), AAA("Lys", 'K'), AGA("Arg", 'R'),
    AUG("Met", 'M'), ACG("Thr", 'T'), AAG("Lys", 'K'), AGG("Arg", 'R'),

    GUU("Val", 'V'), GCU("Ala", 'A'), GAU("Asp", 'D'), GGU("Gly", 'G'),
    GUC("Val", 'V'), GCC("Ala", 'A'), GAC("Asp", 'D'), GGC("Gly", 'G'),
    GUA("Val", 'V'), GCA("Ala", 'A'), GAA("Glu", 'E'), GGA("Gly", 'G'),
    GUG("Val", 'V'), GCG("Ala", 'A'), GAG("Glu", 'E'), GGG("Gly", 'G');

    /**
     * The 3 character length string of basePairs from the sequence
     */
    private String basePairs;

    /**
     * The 1 character abbreviation for the amino acid
     */
    private char aminoAcid;

    /**
     * Constructor for the enum
     *
     * @param basePairs 3 character string to be converted to an amino acid
     * @param aminoAcid 1 character abbreviation for the amino acid
     */
    Codon(String basePairs, char aminoAcid) {
        this.basePairs = basePairs;
        this.aminoAcid = aminoAcid;
    }

    /**
     * Public method that converts the 3 letter base pair string into a codon
     * this function also takes care of replacing thymine to uracil
     *
     * @param codon 3 character string to be converted to an amino acid
     * @return the Codon conversion of the string
     */
    public static Codon getCodon(String codon) {
        codon = codon.toUpperCase();
        codon = codon.replace('T', 'U');
        return valueOf(codon);
    }

    /**
     * public getter method to return the amino acid character of a given codon
     *
     * @return character of the amino acid
     */
    public char getAminoAcid() {
        return aminoAcid;
    }

}

