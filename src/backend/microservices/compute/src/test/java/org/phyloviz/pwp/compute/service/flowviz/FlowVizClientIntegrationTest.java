package org.phyloviz.pwp.compute.service.flowviz;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowVizClientIntegrationTest {

    // TODO: Fix this test
    //    FlowVizClient flowVizClient = FlowVizClient
    //            .builder()
    //            .baseUrl("http://localhost:3000/flowapi")
    //            .credentials("admin", "admin")
    //            .authenticate();
    //
    //    Gson gson = new GsonBuilder()
    //            .registerTypeAdapter(Access.class, new AccessSerializer())
    //            .registerTypeAdapter(Access.class, new AccessDeserializer())
    //            .create();

    //    @Test
    //    void toolService() {
    //
    //        Tool tool = Tool.builder()
    //                .general(General.builder()
    //                        .name("Phylolib")
    //                        .description("The phylolib library")
    //                        .build())
    //                .access(Access.builder()
    //                        .type(AccessType.LIBRARY)
    //                        .details(LibraryAccess.builder()
    //                                .address("localhost")
    //                                .dockerUrl("unix://var/run/docker.sock")
    //                                .dockerImage("luanab/phylolib")
    //                                .dockerContainer("phylolib")
    //                                .dockerAutoRemove(DockerAutoRemove.NEVER)
    //                                .dockerNetworkMode("bridge")
    //                                .dockerApiVersion("auto")
    //                                .dockerVolumes(Collections.singletonList(DockerVolume.builder()
    //                                        .source("/opt/.phylolibVol")
    //                                        .target("/phylolib")
    //                                        .type(VolumeType.BIND)
    //                                        .build()))
    //                                .build())
    //                        .build())
    //                .library(Arrays.asList(
    //                        Library.builder()
    //                                .name("Arguments")
    //                                .order(0)
    //                                .invocation("-args")
    //                                .allowCommandRep(false)
    //                                .commands(List.of(
    //                                        Command.builder().name("help").invocation(List.of("help")).build(),
    //                                        Command.builder().name("distance").invocation(List.of("distance"))
    //                                                .allowedValues(List.of("hamming", "grapetree", "kimura"))
    //                                                .allowedCommandSets(List.of("Options"))
    //                                                .build(),
    //                                        Command.builder().name("correction").invocation(List.of("correction"))
    //                                                .allowedValues(List.of("jukescantor"))
    //                                                .allowedCommandSets(List.of("Options"))
    //                                                .build(),
    //                                        Command.builder().name("algorithm").invocation(List.of("algorithm"))
    //                                                .allowedValues(List.of(
    //                                                        "goeburst", "edmonds", "sl", "cl", "upgma", "upgmc",
    //                                                        "wpgma", "wpgmc", "saitounei", "studierkepler", "unj"))
    //                                                .allowedCommandSets(List.of("Options"))
    //                                                .build(),
    //                                        Command.builder().name("optimization").invocation(List.of("optimization"))
    //                                                .allowedValues(List.of("lbr"))
    //                                                .allowedCommandSets(List.of("Options"))
    //                                                .build()
    //                                ))
    //                                .build(),
    //                        Library.builder()
    //                                .name("Options")
    //                                .order(1)
    //                                .allowCommandRep(true)
    //                                .commands(List.of(
    //                                        Command.builder().name("File Output")
    //                                                .description("Output file as <format>:<location> with format being (asymmetric|symmetric|newick|nexus)")
    //                                                .invocation(List.of("-o", "--out"))
    //                                                .allowedValues(List.of("file"))
    //                                                .build(),
    //                                        Command.builder().name("Dataset Input")
    //                                                .description("Input dataset file as <format>:<location> with format being (fasta|ml|snp)")
    //                                                .invocation(List.of("-d", "--dataset"))
    //                                                .allowedValues(List.of("file"))
    //                                                .build(),
    //                                        Command.builder().name("Distance Matrix Input")
    //                                                .description("Input distance matrix file as <format>:<location> with format being (asymmetric|symmetric)")
    //                                                .invocation(List.of("-m", "--matrix"))
    //                                                .allowedValues(List.of("file"))
    //                                                .build(),
    //                                        Command.builder().name("Phylogenetic Tree Input")
    //                                                .description("Input phylogenetic tree file as <format>:<location> with format being (newick|nexus)")
    //                                                .invocation(List.of("-t", "--tree"))
    //                                                .allowedValues(List.of("file"))
    //                                                .build(),
    //                                        Command.builder().name("Limit of focus variants")
    //                                                .description("Limit of locus variants to consider using goeBURST algorithm [default: 3]")
    //                                                .invocation(List.of("-l", "--lvs"))
    //                                                .allowedValues(List.of("file"))
    //                                                .build()
    //                                ))
    //                                .build()
    //                ))
    //                .build();
    //
    //        flowVizClient.toolService().postTool(tool);
    //    }
    //
    //    @Test
    //    void workflowService() {
    //        Workflow workflow = Workflow.builder()
    //                .name("PhylolibWorkflow")
    //                .description("A test workflow for Phylolib")
    //                .startDate(OffsetDateTime.parse("2022-11-29T19:45:00.000Z").toLocalDateTime())
    //                .tasks(Arrays.asList(
    //                        Task.builder()
    //                                .id("hamming")
    //                                .tool("Phylolib")
    //                                .action(Action.builder()
    //                                        .command("distance hamming --dataset=ml:/phylolib/data/datasets/10.txt --out=symmetric:/phylolib/out.txt")
    //                                        .build())
    //                                .children(Collections.singletonList("upgma"))
    //                                .build(),
    //                        Task.builder()
    //                                .id("upgma")
    //                                .tool("Phylolib")
    //                                .action(Action.builder()
    //                                        .command("algorithm upgma --out=newick:/phylolib/tree.txt --matrix=symmetric:/phylolib/out.txt")
    //                                        .build())
    //                                .children(Collections.emptyList())
    //                                .build()
    //                ))
    //                .build();
    //
    //        this.flowVizClient.workflowService().postWorkflow(workflow);
    //    }
}