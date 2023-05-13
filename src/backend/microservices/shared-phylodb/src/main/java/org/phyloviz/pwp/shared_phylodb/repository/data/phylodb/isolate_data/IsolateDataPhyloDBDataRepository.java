package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.isolate_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataRow;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pt.ist.meic.phylodb.typing.isolate.IsolateRepository;
import pt.ist.meic.phylodb.typing.isolate.model.Ancillary;
import pt.ist.meic.phylodb.typing.isolate.model.Isolate;
import pt.ist.meic.phylodb.typing.profile.ProfileRepository;
import pt.ist.meic.phylodb.typing.profile.model.Profile;
import pt.ist.meic.phylodb.typing.schema.SchemaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IsolateDataPhyloDBDataRepository implements IsolateDataDataRepository {
    private final ProfileRepository profileRepository;
    private final IsolateRepository isolateRepository;
    private final SchemaRepository schemaRepository;

    @Override
    public IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public GetIsolateDataSchemaOutput getIsolateDataSchema(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        /*isolateDataPhyloDBDataRepositorySpecificData repositorySpecificData =
                (isolateDataPhyloDBDataRepositorySpecificData) isolateDataDataRepositorySpecificData;

        Schema schema = schemaRepository.find(new Dataset.PrimaryKey(
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetIds().get(0))
        ).orElseThrow(() -> new RuntimeException("Schema not found in PhyloDB"));

        return new GetisolateDataSchemaOutput(
                schema.getType().getName(),
                schema.getLociIds()
        );*/
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData, int limit, int offset) {
        IsolateDataPhyloDBDataRepositorySpecificData repositorySpecificData =
                (IsolateDataPhyloDBDataRepositorySpecificData) isolateDataDataRepositorySpecificData;

        List<Profile> profiles = profileRepository.findAll(
                offset,
                limit,
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetIds().get(0)
        ).orElseThrow(() -> new RuntimeException("Profiles not found in PhyloDB"));

        List<Isolate> isolates = isolateRepository.findAll(
                offset,
                limit,
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetIds().get(0)
        ).orElseThrow(() -> new RuntimeException("Isolates not found in PhyloDB"));

        return new GetIsolateDataRowsOutput(
                isolates.stream().map(isolate -> new IsolateDataRow(
                                isolate.getPrimaryKey().getId(), // TODO Missing profile id?
                                Arrays.stream(isolate.getAncillaries()).collect(
                                        Collectors.toMap(
                                                Ancillary::getKey,
                                                Ancillary::getValue
                                        )
                                )
                        )
                ).toList(),
                profiles.size()
        );
    }

    @Override
    public void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
