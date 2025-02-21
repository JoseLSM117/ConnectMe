import { UserEmail, UserFirstName, UserGender, UserLastName, UserPassword } from "@src/user/domain/entities/user/valueObjects"

describe("valueObject in UserEntity", () => {
  test("Should create a valid UserFirstName instance", () => {
    const firstName = new UserFirstName("John")
    expect(firstName).toBeInstanceOf(UserFirstName)
    expect(firstName.value).toBe("John")
  })

  test("Should throw InvalidFirstNameOrLastName when creating a UserFirstName instance", () => {
    expect(() => new UserFirstName("J")).toThrow("The firstname should be between 2 and 50 characters")
  })

  test("Should create a valid UserLastName instance", () => {
    const lastName = new UserLastName("Doe")
    expect(lastName).toBeInstanceOf(UserLastName)
    expect(lastName.value).toBe("Doe")
  })

  test("Should throw InvalidFirstNameOrLastName when creating a UserLastName instance", () => {
    expect(() => new UserLastName("D")).toThrow("The firstname should be between 2 and 50 characters")
  })

  test("Should create a valid UserEmail instance", () => {
    const userEmail = new UserEmail("Jhon@gmail.com")
    expect(userEmail).toBeInstanceOf(UserEmail)
    expect(userEmail.value).toBe("Jhon@gmail.com")
  })

  test("Should throw InvalidEmail when creating a UserEmail instance", () => {
    expect(() => new UserEmail("Jhon")).toThrow("Invalid email")
  })

  test("Should create a valid UserPassword instance", () => {
    const userPassword = new UserPassword("123456")
    expect(userPassword).toBeInstanceOf(UserPassword)
    expect(userPassword.value).toBe("123456")
  })

  test("Should throw InvalidPassword when creating a UserPassword instance", () => {
    expect(() => new UserPassword("123")).toThrow("The password should be between 6 and 60 characters")
  })

  test("Should create a valid UserGender instance", () => {
    const userPassword = new UserGender("Male")
    expect(userPassword).toBeInstanceOf(UserGender)
    expect(userPassword.value).toBe("Male")
  })

  test("Should throw InvalidGender when creating a UserPassword instance", () => {
    expect(() => new UserGender("123")).toThrow("This gender not exist")
  })
})
