export const notFoundResponse = (errorMessageResponse: string) => {
    return genericMessageResponse(404, {error: errorMessageResponse});
}

const genericMessageResponse = (statusCode: number, objectResponse: any) => {
    return {
      statusCode,
      headers: {
        'Access-Control-Allow-Origin': '*'
      },
      body: JSON.stringify(objectResponse)
    }
}