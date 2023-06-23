# Use the official Python base image
FROM rclone/rclone:1.62

# Create the rclone config directories
RUN mkdir -p /root/.config
RUN mkdir -p /root/.config/rclone

COPY rclone/rclone.conf /root/.config/rclone/rclone.conf