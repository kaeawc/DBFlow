package com.raizlabs.android.dbflow.processor.handler;

import com.raizlabs.android.dbflow.annotation.ModelJoin;
import com.raizlabs.android.dbflow.processor.definition.JoinDefinition;
import com.raizlabs.android.dbflow.processor.model.ProcessorManager;
import com.squareup.javawriter.JavaWriter;

import java.io.IOException;

import javax.lang.model.element.Element;

/**
 * Description:
 */
public class ModelJoinHandler extends BaseContainerHandler<ModelJoin> {
    @Override
    protected Class<ModelJoin> getAnnotationClass() {
        return ModelJoin.class;
    }

    @Override
    protected void onProcessElement(ProcessorManager processorManager, Element element) {
        try {
            JoinDefinition joinDefinition = new JoinDefinition(element, processorManager);
            //if(definitionValidator.validate(processorManager, joinDefinition)) {
                JavaWriter javaWriter = new JavaWriter(processorManager.getProcessingEnvironment().getFiler().createSourceFile(joinDefinition.getSourceFileName()).openWriter());
                joinDefinition.write(javaWriter);
                javaWriter.close();

                processorManager.addJoinDefinition(joinDefinition);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
