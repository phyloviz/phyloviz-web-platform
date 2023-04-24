package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_template.documents.arguments;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
public class ObtainExtra implements Iterable<Map.Entry<String, ObtainExtraArgumentProperties>> {

    private final Map<String, ObtainExtraArgumentProperties> arguments;

    @Override
    public Iterator<Map.Entry<String, ObtainExtraArgumentProperties>> iterator() {
        return arguments.entrySet().iterator();
    }

    public void forEach(BiConsumer<? super String, ? super ObtainExtraArgumentProperties> action) {
        arguments.forEach(action);
    }
}
