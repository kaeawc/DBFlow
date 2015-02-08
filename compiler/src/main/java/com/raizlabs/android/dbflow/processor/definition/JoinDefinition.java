package com.raizlabs.android.dbflow.processor.definition;

import com.google.common.collect.Sets;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelJoin;
import com.raizlabs.android.dbflow.processor.Classes;
import com.raizlabs.android.dbflow.processor.ProcessorUtils;
import com.raizlabs.android.dbflow.processor.model.ProcessorManager;
import com.raizlabs.android.dbflow.processor.utils.ModelUtils;
import com.raizlabs.android.dbflow.processor.utils.WriterUtils;
import com.raizlabs.android.dbflow.processor.writer.FlowWriter;
import com.raizlabs.android.dbflow.processor.writer.LoadCursorWriter;
import com.squareup.javawriter.JavaWriter;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

/**
 * Description:
 */
public class JoinDefinition extends BaseTableDefinition implements FlowWriter {

    static final String DBFLOW_JOIN_TAG = "$Join";

    public String databaseName;

    FlowWriter[] mMethodWriters;

    public JoinDefinition(Element typeElement, ProcessorManager processorManager) {
        super(typeElement, processorManager);
        setDefinitionClassName(DBFLOW_JOIN_TAG);

        ModelJoin join = typeElement.getAnnotation(ModelJoin.class);
        databaseName = ModelUtils.getDatabaseName(join.databaseName());

        boolean implementsLoadFromCursorListener = ProcessorUtils.implementsClass(manager.getProcessingEnvironment(),
                Classes.LOAD_FROM_CURSOR_LISTENER, (TypeElement) element);

        mMethodWriters = new FlowWriter[] {
                new LoadCursorWriter(this, false, implementsLoadFromCursorListener)
        };

        createColumnDefinitions((TypeElement) typeElement);
    }

    @Override
    protected String getExtendsClass() {
        return Classes.MODEL_JOIN_ADAPTER + "<" + element.getSimpleName() + ">";
    }

    @Override
    protected String[] getImports() {
        return new String[] {
            Classes.CURSOR
        };
    }

    @Override
    public void onWriteDefinition(JavaWriter javaWriter) throws IOException {

        WriterUtils.emitOverriddenMethod(javaWriter, new FlowWriter() {
            @Override
            public void write(JavaWriter javaWriter) throws IOException {
                javaWriter.emitStatement("return new %1s()", getQualifiedModelClassName());
            }
        }, getQualifiedModelClassName(), "newInstance", Sets.newHashSet(Modifier.PUBLIC, Modifier.FINAL));

        for(FlowWriter flowWriter: mMethodWriters) {
            flowWriter.write(javaWriter);
        }
    }

    public String getQualifiedModelClassName() {
        return packageName + "." + getModelClassName();
    }

    @Override
    protected void createColumnDefinitions(TypeElement typeElement) {
        List<? extends Element> variableElements = manager.getElements().getAllMembers(typeElement);
        for(Element variableElement: variableElements) {
            if(variableElement.getAnnotation(Column.class) != null) {
                ColumnDefinition columnDefinition = new ColumnDefinition(manager, (VariableElement) variableElement);
                columnDefinitions.add(columnDefinition);

                if(columnDefinition.columnType == Column.PRIMARY_KEY || columnDefinition.columnType == Column.FOREIGN_KEY) {
                    manager.getMessager().printMessage(Diagnostic.Kind.ERROR, "Joins cannot have primary or foreign keys");
                }
            }
        }
    }

    @Override
    public List<ColumnDefinition> getPrimaryColumnDefinitions() {
        return getColumnDefinitions();
    }

    @Override
    public String getTableSourceClassName() {
        return definitionClassName;
    }
}
