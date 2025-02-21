import { UserEntity } from "@src/user/domain/entities/user/UserEntity"
import { UserEmail, UserFirstName, UserLastName, UserPassword, UserProfilePicture, UserVerify } from "@src/user/domain/entities/user/valueObjects"

describe("UserEntity", () => {
  test("Should create an instance with the create static method", () => {
    /* new UserFirstName("John"),
      new UserLastName("Doe"),
      new UserEmail("JhonDoe@gmail.com"),
      new UserPassword("password") */
    const user = UserEntity.create(
      {
        userFirstName: new UserFirstName("John"),
        userLastName: new UserLastName("Doe"),
        userEmail: new UserEmail("JhonDoe@gmail.com"),
        userPassword: new UserPassword("password"),
        userId: null,
        userIsVerify: new UserVerify(false),
        userProfilePicture: null,
        userCountry: null,
        userGender: null,
        userPhoneId: null,
        userRtId: null,
        userStatusId: null
      }
    )
    expect(user).toBeInstanceOf(UserEntity)
  })
  test("Should create an instance with the fromPrimitives static method", () => {
    const userData = {
      userFirstName: "John",
      userLastName: "Doe",
      userEmail: "JhonDoe@gmail.com",
      userPassword: "password",
      userIsVerify: false,
      userId: null,
      userProfilePicture: null,
      userCountry: null,
      userGender: null,
      userPhoneId: null,
      userRtId: null,
      userStatusId: null
    }
    const user = UserEntity.fromPrimitives(userData)
    expect(user).toBeInstanceOf(UserEntity)
    expect(user.toPrimitives()).toEqual(userData)
  })
  test("Should return the primitive data with the toPrimitives method", () => {
    const userData = {
      userFirstName: "John",
      userLastName: "Doe",
      userEmail: "JhonDoe@gmail.com",
      userPassword: "password",
      userIsVerify: false,
      userId: null,
      userProfilePicture: "profilePicture.png",
      userCountry: null,
      userGender: null,
      userPhoneId: null,
      userRtId: null,
      userStatusId: null
    }
    const user = UserEntity.fromPrimitives(userData)
    expect(user.toPrimitives()).toEqual(userData)
  })
})
