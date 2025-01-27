import { StringValueObject } from "@src/shared/domain/value-object/StringValueObject"

describe("StringValueObject", () => {
  class TestStringValueObject extends StringValueObject { }

  test("Should initialize correctly a value object", () => {
    const value = "test"
    const vo = new TestStringValueObject(value)
    expect(vo.value).toBe(value)
  })

  test("Should throw an error if value is empty", () => {
    expect(() => new TestStringValueObject(null)).toThrow()
  })
  test("Should compare two StringValueObject", () => {
    const value = "test"
    const vo1 = new TestStringValueObject(value)
    const vo2 = new TestStringValueObject(value)
    const equals = vo1.equals(vo2)
    expect(equals).toBeTruthy()
  })
})
