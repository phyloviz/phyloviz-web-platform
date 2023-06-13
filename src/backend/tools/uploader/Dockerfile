# Use the official Python base image
FROM python:3.8-slim


# Copy the required files into the /app folder
COPY uploader/uploader.py /app/uploader.py
COPY uploader/requirements.txt /app/requirements.txt

# Create the AWS config directories
RUN mkdir -p /root/.aws

# Copy the AWS config and credentials files
COPY aws_config /root/.aws/config
COPY aws_credentials /root/.aws/credentials


# Set the working directory to /app
WORKDIR /app

ENV MONGO_URI="mongodb://host.docker.internal:28017/?directConnection=true"

# Install any required packages
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Run the script when the container launches
ENTRYPOINT ["python", "uploader.py"]