# Use the official Python base image
FROM python:3.8-slim


# Copy the required files into the /app folder
COPY tree_indexer/tree_indexer.py /app/tree_indexer.py
COPY tree_indexer/requirements.txt /app/requirements.txt

# Set the working directory to /app
WORKDIR /app

ENV MONGO_URI="mongodb://172.17.0.1:28017/?directConnection=true"
ENV PHYLODB_URL="http://172.17.0.1:8081"

# Install any required packages
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Run the script when the container launches
ENTRYPOINT ["python", "tree_indexer.py"]
