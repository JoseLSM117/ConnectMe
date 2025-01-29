
import { PutObjectCommand, S3Client } from "@aws-sdk/client-s3";
import { envs } from "../env/envs";
export abstract class S3Repository {
  private readonly _client: S3Client;

  constructor() {
    this._client = new S3Client({
      region: envs.AWS_REGION,
      credentials: {
        accessKeyId: envs.AWS_ACCESS_KEY_ID,
        secretAccessKey: envs.AWS_SECRET_ACCESS_KEY
      }
    });
  }

  protected abstract bucketSubFolders(): string

  protected client(): S3Client {
    return this._client;
  }

  async s3UploadFile(uploadData: { file: Buffer, fileName: string }): Promise<void> {
    const client = this.client();
    const params = new PutObjectCommand({
      Bucket: envs.AWS_BUCKET_NAME ?? "connectme",
      Key: `${this.bucketSubFolders()}/${uploadData.fileName}`,
      Body: uploadData.file
    });

    await client.send(params);
  }
}
