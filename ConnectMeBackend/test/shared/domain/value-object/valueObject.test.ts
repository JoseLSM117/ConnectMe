import { InvalidArgumentError } from "@src/shared/domain/value-object/InvalidArgumentError"
import { ValueObject } from "@src/shared/domain/value-object/ValueObject"

describe("ValueObject", () => {
  class TestValueObject extends ValueObject<string> { }

  test("Should initialize correctly a value", () => {
    class TestValueObjectNull extends ValueObject<string> { }

    const value = "Test"
    const vo = new TestValueObjectNull(value)
    expect(vo.value).toBe(value)
  })
  test("Should throw InvalidArgument error exception", () => {
    expect(() => new (TestValueObject as any)()).toThrow(InvalidArgumentError);
  })
  test("Should compare two value objects correctly", () => {
    const value = "Test"
    const vo1 = new TestValueObject(value)
    const vo2 = new TestValueObject(value)
    const equals = vo1.equals(vo2)
    expect(equals).toBe(true)
  })

  test("Should convert using toString method", () => {
    const value = "Test"
    const vo = new TestValueObject(value)
    const cad = vo.toString()
    expect(cad).toBe(value)
  })
})
