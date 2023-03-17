package org.phyloviz.pwp.downloader.http.controllers;

import lombok.AllArgsConstructor;
import org.phyloviz.pwp.downloader.service.DownloaderService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Downloader Microservice.
 */
@RestController
@AllArgsConstructor
public class DownloaderController {

    private final DownloaderService downloaderService;
    // TODO: 11/03/2023 To be implemented.
}
