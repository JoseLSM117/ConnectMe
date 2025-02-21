import type { PrismaClient } from "@prisma/client";
import { Prisma } from "@src/shared/infaestructure/persistance/PrismaClient";
import { UserEntity } from "@src/user/domain/entities/user/UserEntity";
import type { UserRepository } from "@src/user/domain/ports/UserRepository";

export class UserPrismaRepository implements UserRepository {
  private readonly prisma: PrismaClient = Prisma.getClient();

  async save(user: UserEntity): Promise<UserEntity> {
    const userCreated = await this.prisma.users.create({
      data: {
        user_email: user.userEmail.value,
        user_first_name: user.userFirstName.value,
        user_last_name: user.userLastName.value,
        user_password: user.userLastName.value,
        user_is_verify: false,
      }
    });
    return UserEntity.fromPrimitives({
      userEmail: userCreated.user_email,
      userFirstName: userCreated.user_first_name,
      userLastName: userCreated.user_last_name,
      userPassword: userCreated.user_password,
      userProfilePicture: userCreated.user_profile_picture,
      userId: userCreated.user_id,
      userIsVerify: userCreated.user_is_verify,
      userCountry: userCreated.user_country,
      userPhoneId: userCreated.user_phone_id,
      userGender: userCreated.user_gender,
      userRtId: userCreated.user_rt_id,
      userStatusId: userCreated.user_status_id
    });
  }

  async findByEmail(email: string): Promise<UserEntity | undefined> {
    const userFinded = await this.prisma.users.findFirstOrThrow({
      where: {
        user_email: email
      }
    });
    return userFinded ? UserEntity.fromPrimitives({
      userEmail: userFinded.user_email,
      userFirstName: userFinded.user_first_name,
      userLastName: userFinded.user_last_name,
      userPassword: userFinded.user_password,
      userProfilePicture: userFinded.user_profile_picture,
      userId: userFinded.user_id,
      userIsVerify: userFinded.user_is_verify,
      userCountry: userFinded.user_country,
      userPhoneId: userFinded.user_phone_id,
      userGender: userFinded.user_gender,
      userRtId: userFinded.user_rt_id,
      userStatusId: userFinded.user_status_id
    }) : undefined;
  }
}
