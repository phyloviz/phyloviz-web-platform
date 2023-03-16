package org.phyloviz.pwp.downloader.http.controllers;

import org.phyloviz.pwp.downloader.service.DownloaderService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Downloader Microservice.
 */
@RestController
public class DownloaderController {

    private final DownloaderService downloaderService;

    public DownloaderController(DownloaderService downloaderService) {
        this.downloaderService = downloaderService;
    }

    // TODO: 11/03/2023 To be implemented.
}
