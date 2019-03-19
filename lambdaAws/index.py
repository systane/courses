import boto3

s3 = boto3.resource('s3')
client = boto3.client('rekognition')

def lista_imagens():
    imagens=[]
    bucket = s3.Bucket('fa-imagenss')

    for imagem in bucket.objects.all():
        imagens.append(imagem.key)

    return imagens

def indexa_colecao(imagens):
    for i in imagens:
        response=client.index_faces(
            #antes de executar e necesário criar a collection no rekognition: aws rekognition create-collection --collection-id nome_collection
            CollectionId='faces',
            DetectionAttributes=[
            ],
            ExternalImageId=i[:-4],
            Image={
                'S3Object': {
                    'Bucket': 'fa-imagenss',
                    'Name': i,
                },
            },
        )


imagens = lista_imagens()
indexa_colecao(imagens)