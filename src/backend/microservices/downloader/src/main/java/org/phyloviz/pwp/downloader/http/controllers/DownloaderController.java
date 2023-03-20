package org.phyloviz.pwp.downloader.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.downloader.service.DownloaderService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Downloader Microservice.
 */
@RestController
@RequiredArgsConstructor
public class DownloaderController {

    private final DownloaderService downloaderService;
    // TODO: 11/03/2023 To be implemented.
}
