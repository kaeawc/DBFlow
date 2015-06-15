package com.raizlabs.android.dbflow.converter;

/**
 * Author: andrewgrosner
 * Description: This class is responsible for converting the stored database value into the field value in
 * a Model.
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public abstract class TypeConverter<DataClass, ModelClass> {

    /**
     * Converts the ModelClass into a DataClass
     *
     * @param model this will be called upon syncing
     * @return The DataClass value that converts into a SQLite type
     */
    public abstract DataClass getDBValue(ModelClass model);

    /**
     * Converts a DataClass from the DB into a ModelClass
     *
     * @param data This will be called when the model is loaded from the DB
     * @return The ModelClass value that gets set in a Model that holds the data class.
     */
    public abstract ModelClass getModelValue(DataClass data);
}
