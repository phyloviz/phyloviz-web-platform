package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.library;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.AccessDetailsTemplate;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.LibraryAccessDetails;
import org.springframework.data.convert.ValueConverter;

@Data
@Builder
@AllArgsConstructor
public class LibraryAccessDetailsTemplate implements AccessDetailsTemplate {

    private final String address;

    private final String dockerUrl;

    private final String dockerImage;

    private final String dockerContainer;

    private final DockerAutoRemoveTemplate dockerAutoRemove;

    private final String dockerNetworkMode;

    private final String dockerApiVersion;

    private final List<DockerVolumeTemplate> dockerVolumes;

    public LibraryAccessDetails build(ToolTemplateData data) {

        return LibraryAccessDetails.builder()
                .address(address)
                .dockerUrl(dockerUrl)
                .dockerImage(dockerImage)
                .dockerContainer(dockerContainer)
                .dockerAutoRemove(dockerAutoRemove.build())
                .dockerNetworkMode(dockerNetworkMode)
                .dockerApiVersion(dockerApiVersion)
                .dockerVolumes(dockerVolumes.stream().map(dockerVolumeTemplate -> dockerVolumeTemplate.buildDockerVolume(data)).toList())
                .build();
    }

    @Override
    public LibraryAccessDetails build() {
        return LibraryAccessDetails.builder()
                .address(address)
                .dockerUrl(dockerUrl)
                .dockerImage(dockerImage)
                .dockerContainer(dockerContainer)
                .dockerAutoRemove(dockerAutoRemove.build())
                .dockerNetworkMode(dockerNetworkMode)
                .dockerApiVersion(dockerApiVersion)
                .dockerVolumes(dockerVolumes.stream().map(DockerVolumeTemplate::buildDockerVolume).toList())
                .build();
    }


}
