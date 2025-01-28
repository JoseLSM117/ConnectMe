import { UserEntity } from "@src/user/domain/entities/user/UserEntity";
import type { UserRepository } from "@src/user/domain/ports/UserRepository";
interface Input {
  email: string
  firstName: string
  lastName: string
  password: string
}
export class CreateUser {
  private readonly _userRepository: UserRepository;
  constructor(userRepository: UserRepository) {
    this._userRepository = userRepository;
  }

  async run(input: Input): Promise<UserEntity> {
    const user = UserEntity.fromPrimitives(input);
    return await this._userRepository.save(user);
  }
}
