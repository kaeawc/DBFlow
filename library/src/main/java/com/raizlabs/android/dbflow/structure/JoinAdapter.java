package com.raizlabs.android.dbflow.structure;

import android.database.Cursor;

/**
 * Description:
 */
public abstract class JoinAdapter<JoinClass extends BaseModelJoin> {

    /**
     * Loads the cursor into a new model view object
     *
     * @param cursor         The cursor to query
     * @param modelViewClass The model view to assign the cursor data to
     */
    public abstract void loadFromCursor(Cursor cursor, JoinClass modelViewClass);

    /**
     * Creates a new {@link JoinClass} and loads the cursor into it.
     *
     * @param cursor The cursor to query
     * @return The new model view with the cursor data in it.
     */
    public JoinClass loadFromCursor(Cursor cursor) {
        JoinClass modelViewClass = newInstance();
        loadFromCursor(cursor, modelViewClass);
        return modelViewClass;
    }

    /**
     * @return A new instance of the {@link JoinClass} must have a default constructor.
     */
    protected abstract JoinClass newInstance();


}
