import boto3

s3 = boto3.resource('s3')
client = boto3.client('rekognition')

def detecta_faces():
    faces_detectadas = client.index_faces(
        CollectionId='faces',
        DetectionAttributes=['DEFAULT'],
        ExternalImageId='TEMPORARIA',
        Image={
            'S3Object': {
                'Bucket': 'fa-imagenss',
                'Name': '_analise.png',
            },
        },
    )

