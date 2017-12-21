package scrabble;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionnaire {
    Set<String> words = new HashSet<>();

    private Dictionnaire() {
        try {
            words = readFile();
        } catch ( FileNotFoundException ex ) {
            Logger.getLogger( Dictionary.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public static Dictionnaire getInstance() {
        return DictionnaireClass.INSTANCE;
    }

    private static class DictionnaireClass {

        private static final Dictionnaire INSTANCE = new Dictionnaire();
    }

    private Set<String> readFile() throws FileNotFoundException {
        Set<String> mots = new HashSet<>();
        Scanner sc = new Scanner( new FileReader( "dico.txt" ) );
        while ( sc.hasNext() ) {
            mots.add( sc.nextLine() );
        }
        return mots;
    }

    public boolean contains( String s ) {
        return words.contains( s );
    }

    public boolean contains( Mot mot ) {
        return mot != null ? words.contains( mot.toString() ) : false;
    }

    public boolean allValid( List<Mot> listeMot ) {
        for ( Mot m : listeMot ) {
            if ( !contains( m ) )
                return false;
        }
        return true;
    }
}
