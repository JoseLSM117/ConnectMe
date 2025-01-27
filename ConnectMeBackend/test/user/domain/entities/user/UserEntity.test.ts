import { UserEntity } from "@src/user/domain/entities/user/UserEntity"
import { UserEmail, UserFirstName, UserLastName, UserPassword } from "@src/user/domain/entities/user/valueObjects"

describe("UserEntity", () => {
  test("Should create an instance with the create static method", () => {
    const user = UserEntity.create(
      new UserFirstName("John"),
      new UserLastName("Doe"),
      new UserEmail("JhonDoe@gmail.com"),
      new UserPassword("password")
    )
    expect(user).toBeInstanceOf(UserEntity)
  })
  test("Should create an instance with the fromPrimitives static method", () => {
    const userData = {
      firstName: "John",
      lastName: "Doe",
      email: "JhonDoe@gmail.com",
      password: "password"
    }
    const user = UserEntity.fromPrimitives(userData)
    expect(user).toBeInstanceOf(UserEntity)
    expect(user.toPrimitives()).toEqual({
      firstName: "John",
      lastName: "Doe",
      email: "JhonDoe@gmail.com",
      password: "password",
      profilePicture: undefined
    })
  })
  test("Should return the primitive data with the toPrimitives method", () => {
    const userData = {
      firstName: "John",
      lastName: "Doe",
      email: "JhonDoe@gmail.com",
      password: "password",
      profilePicture: "profilePicture.png"
    }
    const user = UserEntity.fromPrimitives(userData)
    expect(user.toPrimitives()).toEqual({
      firstName: "John",
      lastName: "Doe",
      email: "JhonDoe@gmail.com",
      password: "password",
      profilePicture: "profilePicture.png"
    })
  })
})
