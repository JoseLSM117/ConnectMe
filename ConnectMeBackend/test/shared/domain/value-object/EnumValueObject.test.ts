import { EnumValueObject } from "@src/shared/domain/value-object/EnumValue.Object"

describe("EnumValueObject", () => {
  class TestEnumVo extends EnumValueObject<string> {
    protected throwErrorForInvalidValue(value: string): void {
      throw new Error(`Invalid value: ${value}`)
    }
  }
  test("Should initialize an EnumValueObject", () => {
    const value = "test"
    const values = ["test", "test2", "test3"]
    const testEnumVo = new TestEnumVo(value, values)
    expect(testEnumVo.value).toBe(value)
    expect(testEnumVo.validValues).toEqual(values)
  })
  test("Should throw an error if the value is not valid", () => {
    const value = "test1"
    const values = ["test", "test2", "test3"]
    expect(() => new TestEnumVo(value, values)).toThrow(`Invalid value: ${value}`)
  })
})
