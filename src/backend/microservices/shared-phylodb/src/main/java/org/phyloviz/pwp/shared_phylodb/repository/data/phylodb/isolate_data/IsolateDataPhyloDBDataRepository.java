package org.phyloviz.pwp.shared_phylodb.repository.data.phylodb.isolate_data;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataPhyloDBDataRepositorySpecificData;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.IsolateDataRow;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import pt.ist.meic.phylodb.typing.isolate.IsolateRepository;
import pt.ist.meic.phylodb.typing.isolate.model.Ancillary;
import pt.ist.meic.phylodb.typing.isolate.model.Isolate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IsolateDataPhyloDBDataRepository implements IsolateDataDataRepository {
    private final IsolateRepository isolateRepository;

    @Override
    public IsolateDataDataRepositorySpecificData uploadIsolateData(String projectId, String isolateDataId, MultipartFile multipartFile) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String downloadIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData, int limit, int offset) {
        IsolateDataPhyloDBDataRepositorySpecificData repositorySpecificData =
                (IsolateDataPhyloDBDataRepositorySpecificData) isolateDataDataRepositorySpecificData;

        List<Isolate> isolates = isolateRepository.findAll(
                offset,
                limit,
                repositorySpecificData.getProjectId(),
                repositorySpecificData.getDatasetIds().get(0)
        ).orElseThrow(() -> new RuntimeException("Isolates not found in PhyloDB"));

        return new GetIsolateDataRowsOutput(
                isolates.stream()
                        .filter(isolate -> isolate.getProfile() != null)
                        .map(isolate -> new IsolateDataRow(
                                        isolate.getPrimaryKey().getId(),
                                        isolate.getProfile().getPrimaryKey().getId(),
                                        Arrays.stream(isolate.getAncillaries()).collect(
                                                Collectors.toMap(Ancillary::getKey, Ancillary::getValue)
                                        )
                                )
                        ).toList(),
                isolates.size()
        );
    }

    @Override
    public void deleteIsolateData(IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData) {
//        throw new UnsupportedOperationException("Not implemented yet."); TODO: implement
    }
}
