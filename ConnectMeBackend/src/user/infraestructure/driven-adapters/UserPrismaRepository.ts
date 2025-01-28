import type { PrismaClient } from "@prisma/client";
import { Prisma } from "@src/shared/infaestructure/persistance/PrismaClient";
import type { UserEntity } from "@src/user/domain/entities/user/UserEntity";
import type { UserRepository } from "@src/user/domain/ports/UserRepository";

export class UserPrismaRepository implements UserRepository {
  private readonly prisma: PrismaClient = Prisma.getClient();

  async save(user: UserEntity): Promise<UserEntity> {
    await this.prisma.users.create({
      data: {
        email: user.email.value,
        first_name: user.firstName.value,
        last_name: user.lastName.value,
        password: user.lastName.value,
        isVerify: false
      }
    });
    return user;
  }
}
