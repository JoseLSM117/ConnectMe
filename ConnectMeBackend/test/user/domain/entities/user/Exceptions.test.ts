import { UserEntity } from "@src/user/domain/entities/user/UserEntity"
import { UserCountry, UserEmail, UserFirstName, UserLastName, UserPassword, UserVerify } from "@src/user/domain/entities/user/valueObjects"

describe("Exceptions in User Entity", () => {
  test("Should throw InvalidFirstNameOrLastName exception", () => {
    expect(() => {
      return new UserEntity({
        userFirstName: new UserFirstName(""),
        userLastName: new UserLastName(""),
        userEmail: new UserEmail(""),
        userPassword: new UserPassword(""),
        userId: null,
        userIsVerify: new UserVerify(false),
        userProfilePicture: null,
        userCountry: null,
        userGender: null,
        userPhoneId: null,
        userRtId: null,
        userStatusId: null
      })
    }).toThrow("The firstname should be between 2 and 50 characters")
  })

  test("Should throw InvalidEmail exception", () => {
    expect(() => {
      return new UserEntity({
        userFirstName: new UserFirstName("John"),
        userLastName: new UserLastName("Doe"),
        userEmail: new UserEmail(""),
        userPassword: new UserPassword(""),
        userId: null,
        userIsVerify: new UserVerify(false),
        userProfilePicture: null,
        userCountry: null,
        userGender: null,
        userPhoneId: null,
        userRtId: null,
        userStatusId: null
      })
    }).toThrow("Invalid email")
  })

  test("Should throw InvalidPassword exception", () => {
    expect(() => {
      return new UserEntity({
        userFirstName: new UserFirstName("John"),
        userLastName: new UserLastName("Doe"),
        userEmail: new UserEmail("Jhon@gmail.com"),
        userPassword: new UserPassword(""),
        userId: null,
        userIsVerify: new UserVerify(false),
        userProfilePicture: null,
        userCountry: null,
        userGender: null,
        userPhoneId: null,
        userRtId: null,
        userStatusId: null
      })
    }).toThrow("The password should be between 6 and 60 characters")
  })
})
