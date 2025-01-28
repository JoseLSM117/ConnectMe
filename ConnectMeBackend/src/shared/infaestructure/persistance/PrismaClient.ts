import { PrismaClient } from "@prisma/client";
// eslint-disable-next-line @typescript-eslint/no-extraneous-class
export class Prisma {
  private static prismaClient: PrismaClient;

  private constructor() { }

  private static createClient(): PrismaClient {
    return new PrismaClient();
  }

  public static getClient(): PrismaClient {
    const client = Prisma.prismaClient;
    if (client === null) {
      Prisma.prismaClient = this.createClient();
    }

    return Prisma.prismaClient;
  }
}
