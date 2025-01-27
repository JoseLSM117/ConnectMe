import { UserEntity } from "@src/user/domain/entities/user/UserEntity"
import { UserEmail, UserFirstName, UserLastName, UserPassword } from "@src/user/domain/entities/user/valueObjects"

describe("Exceptions in User Entity", () => {
  test("Should throw InvalidFirstNameOrLastName exception", () => {
    expect(() => {
      return new UserEntity({
        firstName: new UserFirstName(""),
        lastName: new UserLastName(""),
        email: new UserEmail(""),
        password: new UserPassword("")
      })
    }).toThrow("The firstname should be between 2 and 50 characters")
  })

  test("Should throw InvalidEmail exception", () => {
    expect(() => {
      return new UserEntity({
        firstName: new UserFirstName("John"),
        lastName: new UserLastName("Doe"),
        email: new UserEmail(""),
        password: new UserPassword("")
      })
    }).toThrow("Invalid email")
  })

  test("Should throw InvalidPassword exception", () => {
    expect(() => {
      return new UserEntity({
        firstName: new UserFirstName("John"),
        lastName: new UserLastName("Doe"),
        email: new UserEmail("Jhon@gmail.com"),
        password: new UserPassword("")
      })
    }).toThrow("The password should be between 6 and 60 characters")
  })
})
