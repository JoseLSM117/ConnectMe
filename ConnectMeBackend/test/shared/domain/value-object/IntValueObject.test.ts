import { NumberValueObject } from "@src/shared/domain/value-object/IntValueObject"

describe("IntValueObject", () => {
  class TestNumberValueObject extends NumberValueObject { }
  test("Should initialize an IntValueObject", () => {
    const value = 1
    const vo = new TestNumberValueObject(value)
    expect(vo.value).toBe(value)
  })
  test("Should compare two IntValueObject and return if on is bigger than the other", () => {
    const value1 = 1
    const value2 = 2
    const vo = new TestNumberValueObject(value1)
    const vo2 = new TestNumberValueObject(value2)
    const isBigger = vo.isBiggerThan(vo2)
    expect(isBigger).toBeFalsy()
  })
})
