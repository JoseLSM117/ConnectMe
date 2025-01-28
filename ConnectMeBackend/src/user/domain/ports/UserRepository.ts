import type { UserEntity } from "../entities/user/UserEntity";

export interface UserRepository {
  save: (user: UserEntity) => Promise<UserEntity>
}
