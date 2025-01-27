import { PrismaClient } from "@prisma/client"
import { Prisma } from "@src/shared/infaestructure/persistance/PrismaClient"

jest.mock("@prisma/client")

describe("Prisma class", () => {
  beforeEach(() => {
    (Prisma as any).prismaClient = null
  })

  test("Should create a new PrismaClient when getClient is called for the first time", () => {
    const client = Prisma.getClient()
    expect(client).toBeInstanceOf(PrismaClient)
    expect(PrismaClient).toHaveBeenCalledTimes(1)
  })

  test("Should return the same PrismaClient when getClient is called multiple times", () => {
    const firstCall = Prisma.getClient()
    const secondCall = Prisma.getClient()
    expect(firstCall).toEqual(secondCall)
  })
})
