package org.phyloviz.pwp.config;


import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryRegistryImpl;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryRegistryImpl;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryRegistryImpl;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryRegistryImpl;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryRegistry;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryRegistryImpl;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.TreeS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewS3DataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataS3DataRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataS3DataRepositorySpecificData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Configuration class for the Data Repositories.
 */
@Configuration
public class DataRepositoryConfig {

    @Bean
    public TypingDataDataRepositoryRegistry typingDataDataRepositoryRegistry(ApplicationContext context) {
        return new TypingDataDataRepositoryRegistryImpl(context, Map.of(
                TypingDataDataRepositoryId.S3, TypingDataS3DataRepository.class
        ), Map.of(
                TypingDataDataRepositoryId.S3, TypingDataS3DataRepositorySpecificData.class
        ));
    }

    @Bean
    public IsolateDataDataRepositoryRegistry isolateDataDataRepositoryRegistry(ApplicationContext context) {
        return new IsolateDataDataRepositoryRegistryImpl(context, Map.of(
                IsolateDataDataRepositoryId.S3, IsolateDataS3DataRepository.class

        ), Map.of(
                IsolateDataDataRepositoryId.S3, IsolateDataS3DataRepositorySpecificData.class
        ));
    }

    @Bean
    public DistanceMatrixDataRepositoryRegistry distanceMatrixDataRepositoryRegistry(ApplicationContext context) {
        return new DistanceMatrixDataRepositoryRegistryImpl(context, Map.of(
                DistanceMatrixDataRepositoryId.S3, DistanceMatrixS3DataRepository.class
        ), Map.of(
                DistanceMatrixDataRepositoryId.S3, DistanceMatrixS3DataRepositorySpecificData.class
        ));
    }

    @Bean
    public TreeDataRepositoryRegistry treeDataRepositoryRegistry(ApplicationContext context) {
        return new TreeDataRepositoryRegistryImpl(context, Map.of(
                TreeDataRepositoryId.S3, TreeS3DataRepository.class
        ), Map.of(
                TreeDataRepositoryId.S3, TreeS3DataRepositorySpecificData.class
        ));
    }

    @Bean
    public TreeViewDataRepositoryRegistry treeViewDataRepositoryRegistry(ApplicationContext context) {
        return new TreeViewDataRepositoryRegistryImpl(context, Map.of(
                TreeViewDataRepositoryId.S3, TreeViewS3DataRepository.class
        ), Map.of(
                TreeViewDataRepositoryId.S3, TreeViewS3DataRepositorySpecificData.class
        ));
    }
}
