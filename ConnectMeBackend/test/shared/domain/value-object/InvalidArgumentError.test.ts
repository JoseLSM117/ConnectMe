import { InvalidArgumentError } from "@src/shared/domain/value-object/InvalidArgumentError"

describe("InvalidArgumentError", () => {
  test("Should create an error", () => {
    const error = new InvalidArgumentError("error")
    expect(error).toBeInstanceOf(Error)
  })
  test("Should create an error with a message", () => {
    const error = new InvalidArgumentError("error")
    expect(error.message).toBe("error")
  })
})
