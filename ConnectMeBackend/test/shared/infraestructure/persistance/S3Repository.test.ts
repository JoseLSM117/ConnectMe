import { S3Client, PutObjectCommand } from "@aws-sdk/client-s3"
import { envs } from "../../../../src/shared/infaestructure/env/envs"
import { S3Repository } from "@src/shared/infaestructure/persistance/S3Repository"

const mockSend = jest.fn().mockResolvedValue({})

jest.mock("@aws-sdk/client-s3", () => ({
  S3Client: jest.fn(() => ({
    send: mockSend
  })),
  PutObjectCommand: jest.fn().mockImplementation((params) => ({
    ...params,
    constructor: { name: "PutObjectCommand" }
  }))
}))
jest.mock("../../../../src/shared/infaestructure/env/envs", () => ({
  envs: {
    AWS_BUCKET_NAME: "test-bucket",
    AWS_REGION: "test-region",
    AWS_ACCESS_KEY_ID: "test-key-id",
    AWS_SECRET_ACCESS_KEY: "test-secret"
  }
}))
class TestS3Repository extends S3Repository {
  protected bucketSubFolders(): string {
    return "test-folder"
  }
}

describe("S3Repository", () => {
  beforeEach(() => {
    jest.clearAllMocks()
  })
  test("Should initialize S3Client with correct configuration", () => {
    // eslint-disable-next-line no-new
    new TestS3Repository()

    expect(S3Client).toHaveBeenCalledWith({
      region: "test-region",
      credentials: {
        accessKeyId: "test-key-id",
        secretAccessKey: "test-secret"
      }
    })
  })
  test("Should upload file with correct parameters", async () => {
    const repository = new TestS3Repository()
    const mockFile = Buffer.from("contenido")
    const fileName = "archivo.txt"

    await repository.s3UploadFile({ file: mockFile, fileName })

    expect(PutObjectCommand).toHaveBeenCalledWith({
      Bucket: "test-bucket",
      Key: "test-folder/archivo.txt",
      Body: mockFile
    })

    const expectedCommand = (PutObjectCommand as unknown as jest.Mock).mock.results[0].value
    expect(mockSend).toHaveBeenCalledWith(expectedCommand)
  })

  test("Should use the default bucket name if not provided", async () => {
    envs.AWS_BUCKET_NAME = undefined
    const repository = new TestS3Repository()

    await repository.s3UploadFile({ file: Buffer.from("contenido"), fileName: "archivo.txt" })

    expect(PutObjectCommand).toHaveBeenCalledWith(
      expect.objectContaining({ Bucket: "connectme" })
    )
  })
})
