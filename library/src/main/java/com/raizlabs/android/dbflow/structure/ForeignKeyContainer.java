package com.raizlabs.android.dbflow.structure;

import java.util.HashMap;

/**
 * Description: Holds data related to a foreign key and will retrieve it on demand, rather than
 * load up the foreign key object right away.
 */
public class ForeignKeyContainer {


    HashMap<String, Object> mValueMap;

    /**
     * Instantiated by a {@link com.raizlabs.android.dbflow.structure.ModelAdapter} this class will
     * hold onto the cursor values of a foreign key object and enable lazy loading of the foreign object.
     * @param columns
     */
    ForeignKeyContainer(String... columns) {
        mValueMap = new HashMap<>(columns.length);
        for(String column: columns) {
            mValueMap.put(column, null);
        }
    }

    /**
     * @param columnName
     * @return The value from the stored container's
     */
    public Object getValue(String columnName) {
        return mValueMap.get(columnName);
    }
}
