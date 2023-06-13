# Use the official Python base image
FROM python:3.8-slim


# Copy the required files into the /app folder
COPY compute_tree_view/compute_tree_view.py /app/compute_tree_view.py
COPY compute_tree_view/requirements.txt /app/requirements.txt

# Set the working directory to /app
WORKDIR /app

ENV MONGO_URI="mongodb://host.docker.internal:28017/?directConnection=true"
ENV PHYLODB_URL="http://host.docker.internal:8081"

# Install any required packages
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Run the script when the container launches
ENTRYPOINT ["python", "compute_tree_view.py"]