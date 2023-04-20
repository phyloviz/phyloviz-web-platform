#!/bin/bash

find . -type f -name "upload_to_registry.sh" -not -samefile "$(basename $0)" -exec bash {} \;
