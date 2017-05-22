package grp1.docs;

import grp1.docs.model.CityDocumentModel;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by admin on 23.05.2017.
 */
public class DocumentTypes {
    public static HashMap<String, DocumentModel> getTypes() {
        return types;
    }

    static HashMap<String, DocumentModel> types = new HashMap<String, DocumentModel>();

    static
    {
        types.put("cities", new CityDocumentModel());
    }


}
