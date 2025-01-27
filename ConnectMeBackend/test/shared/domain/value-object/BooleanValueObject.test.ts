import { BooleanValueObject } from "@src/shared/domain/value-object/BooleanValueObject"

describe("BooleanValueObject", () => {
  class TestBooleanValueObject extends BooleanValueObject {}
  test("Should create a valid boolean value object", () => {
    const booleanValueObject = new TestBooleanValueObject(true)
    expect(booleanValueObject.value).toBe(true)
  })
  test("Should compare two boolean value objects", () => {
    const booleanValueObject1 = new TestBooleanValueObject(true)
    const booleanValueObject2 = new TestBooleanValueObject(true)
    expect(booleanValueObject1.equals(booleanValueObject2)).toBe(true)
  })
})
