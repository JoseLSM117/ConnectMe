import type { UserEntity } from "@src/user/domain/entities/user/UserEntity";
import type { UserRepository } from "@src/user/domain/ports/UserRepository";

interface Input {
  email: string,
}
export class FindUser {
  private readonly _userRepository: UserRepository;
  constructor(userRepository: UserRepository) {
    this._userRepository = userRepository;
  }
  async run(input: Input): Promise<UserEntity | undefined> {
    const user = await this._userRepository.findByEmail(input.email);
    return user;
  }
}