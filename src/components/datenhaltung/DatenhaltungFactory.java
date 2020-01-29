package components.datenhaltung;

import concepts.IDatenhaltung;
import concepts.SqliteDatenhaltung;

import java.security.InvalidParameterException;

public class DatenhaltungFactory {
    public static IDatenhaltung createDatenhaltung(DatenhaltungsTyp datenhaltungsTyp) throws InvalidParameterException {
        switch (datenhaltungsTyp) {
            case Sqlite:
                return new SqliteDatenhaltung();
            case Json:
                return new Datenhaltung2();
        }

        throw new InvalidParameterException("Could not find DatenhaltungsTyp");
    }
}
