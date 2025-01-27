import { Uuid } from "@src/shared/domain/value-object/Uuid"

describe("Uuid", () => {
  test("should create a valid Uuid", () => {
    const uuid = Uuid.random()
    expect(uuid).toBeInstanceOf(Uuid)
  })

  test("Should throw an error if the uuid is not valid", () => {
    const uuid = "invalid-uuid"
    expect(() => new Uuid(uuid)).toThrow()
  })
})
