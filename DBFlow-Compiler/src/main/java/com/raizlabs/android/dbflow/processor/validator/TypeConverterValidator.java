package com.raizlabs.android.dbflow.processor.validator;

import com.raizlabs.android.dbflow.processor.definition.TypeConverterDefinition;
import com.raizlabs.android.dbflow.processor.model.ProcessorManager;

/**
 * Description:
 */
public class TypeConverterValidator implements Validator<TypeConverterDefinition>  {
    @Override
    public boolean validate(ProcessorManager processorManager, TypeConverterDefinition typeConverterDefinition) {
        boolean success = true;

        if(typeConverterDefinition.getModelElement() == null) {
            processorManager.logError("TypeConverter: " + typeConverterDefinition.getClassElement().getSimpleName() +
                " uses an unsupported Model Element parameter. If it has type parameters, you must remove them or subclass it" +
                    "for proper usage.");
            success = false;
        } else if(typeConverterDefinition.getDbElement() == null) {
            processorManager.logError("TypeConverter: " + typeConverterDefinition.getClassElement().getSimpleName() +
                    " uses an unsupported DB Element parameter. If it has type parameters, you must remove them or subclass it " +
                    "for proper usage.");
            success = false;
        }

        return success;
    }
}
