import argparse
import os
from urllib.parse import urlparse
import configparser
import boto3
from botocore.exceptions import NoCredentialsError, ClientError


def download_s3_object(s3_url, out):
    # Parse the S3 URL
    parsed_url = urlparse(s3_url)
    bucket_name = parsed_url.netloc
    object_key = parsed_url.path.lstrip('/')

    # Obtain endpoint url from ~/.aws/config
    # Read the custom endpoint URL from the config file
    config = configparser.ConfigParser()
    config.read(os.path.expanduser('~/.aws/config'))

    custom_endpoint_url = config.get('default', 'endpoint_url')

    # Create an S3 client
    s3 = boto3.client('s3', endpoint_url=custom_endpoint_url)

    try:
        s3.download_file(bucket_name, object_key, out)
        print(f'Successfully downloaded {s3_url} to {out}')
    except NoCredentialsError:
        print('Error: AWS credentials not found')
    except ClientError as e:
        print(f'Error downloading the file: {e}')


if __name__ == '__main__':
    # Make it so the script always starts with download  argument
    parser = argparse.ArgumentParser(description='Download an S3 object to a specific folder')
    parser.add_argument('--url', help='The S3 object URL to download', required=True)
    parser.add_argument('--out', help='The output file path', required=True)

    args = parser.parse_args()

    download_s3_object(args.s3_url, args.out)
