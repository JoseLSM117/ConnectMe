import { EntityRoot } from "../EntityRoot"
import { UserEmail, UserFirstName, UserLastName, UserPassword, UserProfilePicture } from "./valueObjects"

interface PrimitiveData {
  firstName: string
  lastName: string
  email: string
  password: string
  profilePicture?: string
}
export class UserEntity extends EntityRoot<UserEntity, PrimitiveData> {
  readonly firstName: UserFirstName
  readonly lastName: UserLastName
  readonly email: UserEmail
  readonly password: UserPassword
  readonly profilePicture?: UserProfilePicture

  constructor({ firstName, lastName, email, password, profilePicture }: { firstName: UserFirstName, lastName: UserLastName, email: UserEmail, password: UserPassword, profilePicture?: UserProfilePicture }) {
    super()
    this.firstName = firstName
    this.lastName = lastName
    this.email = email
    this.password = password
    this.profilePicture = profilePicture
  }

  static create(firstName: UserFirstName, lastName: UserLastName, email: UserEmail, password: UserPassword, profilePicture?: UserProfilePicture): UserEntity {
    return new UserEntity({ firstName, lastName, email, password, profilePicture })
  }

  static fromPrimitives(plainData: { firstName: string, lastName: string, email: string, password: string, profilePicture?: string }): UserEntity {
    return new UserEntity({
      firstName: new UserFirstName(plainData.firstName),
      lastName: new UserLastName(plainData.lastName),
      email: new UserEmail(plainData.email),
      password: new UserPassword(plainData.password),
      profilePicture: plainData.profilePicture ? new UserProfilePicture(plainData.profilePicture) : undefined
    })
  }

  toPrimitives(): PrimitiveData {
    return {
      firstName: this.firstName.value,
      lastName: this.lastName.value,
      email: this.email.value,
      password: this.password.value,
      profilePicture: this.profilePicture?.value
    }
  }
}
