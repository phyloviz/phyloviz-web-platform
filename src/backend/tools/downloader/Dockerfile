# Use the official Python base image
FROM python:3.8-slim


# Copy the required files into the /app folder
COPY downloader/ /app


# Create the AWS config directories
RUN mkdir -p ~/.aws

# Copy the AWS config and credentials files
COPY aws_config ~/.aws/config
COPY aws_credentials ~/.aws/credentials

# Set the working directory to /app
WORKDIR /app

# Install any required packages
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Run the script when the container launches
CMD ["python", "download_s3_object.py"]