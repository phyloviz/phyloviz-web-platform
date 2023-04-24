package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.arguments;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
public class WorkflowTemplateArguments implements Iterable<Map.Entry<String, WorkflowTemplateArgumentProperties>> {

    private final Map<String, WorkflowTemplateArgumentProperties> arguments;

    public boolean containsArgument(String argumentName) {
        return arguments.containsKey(argumentName);
    }

    @Override
    public Iterator<Map.Entry<String, WorkflowTemplateArgumentProperties>> iterator() {
        return arguments.entrySet().iterator();
    }

    public void forEach(BiConsumer<? super String, ? super WorkflowTemplateArgumentProperties> action) {
        arguments.forEach(action);
    }
}
