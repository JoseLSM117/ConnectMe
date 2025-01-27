import { PrismaClient } from "@prisma/client"

// eslint-disable-next-line @typescript-eslint/no-extraneous-class
export class Prisma {
  private static prismaClient: Prisma | null = null

  private constructor() { }

  private static createClient(): Prisma {
    return new PrismaClient()
  }

  public static getClient(): Prisma | null {
    const client = Prisma.prismaClient
    if (client === null) {
      Prisma.prismaClient = this.createClient()
    }

    return Prisma.prismaClient
  }
}
